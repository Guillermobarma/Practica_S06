package com.practica_s06.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practica_s06.model.Estado

@Dao
interface EstadoDao {
    @Query("SELECT * FROM ESTADO")
    fun getAllData(): LiveData<List<Estado>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEstado(estado: Estado)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateEstado(estado: Estado)

    @Delete
    suspend fun deleteEstado(estado: Estado)

    @Query("select * from ESTADO")
    fun getEstado() : LiveData<List<Estado>>
}