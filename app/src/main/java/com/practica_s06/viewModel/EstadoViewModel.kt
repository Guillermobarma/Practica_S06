package com.practica_s06.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.practica_s06.data.EstadoDatabase
import com.practica_s06.repository.EstadoRepository
import kotlinx.coroutines.launch
import com.practica_s06.model.Estado

class EstadoViewModel (application: Application) : AndroidViewModel(application) {

    val getEstado : LiveData<List<Estado>>
    private val repository : EstadoRepository

    init {
        val estadoDao = EstadoDatabase.getDatabase(application).estadoDao()
        repository = EstadoRepository(estadoDao)
        getEstado = repository.getEstado
    }

    fun saveEstado(lugar: Estado) {
        viewModelScope.launch { repository.saveEstado(lugar) }
    }


    fun deleteLugar(lugar: Estado){
        viewModelScope.launch { repository.deleteEstado(lugar) }
    }
}