package com.practica_s06.repository

import androidx.lifecycle.LiveData
import com.practica_s06.data.EstadoDao
import com.practica_s06.model.Estado

class EstadoRepository(private val estadoDao: EstadoDao) {

    suspend fun saveEstado(estado: Estado) {
        if(estado.id==0){//se crea un nuevo lugar
            estadoDao.addEstado(estado)
        }
        else{// El id es diferente a 0
            estadoDao.updateEstado(estado)
        }
    }

    suspend fun deleteEstado(estado: Estado){
        estadoDao.deleteEstado(estado)
    }

    var getEstado : LiveData<List<Estado>> = estadoDao.getEstado()

}