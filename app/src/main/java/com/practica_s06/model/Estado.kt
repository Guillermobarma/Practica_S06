package com.practica_s06.model

import android.os.Parcelable
import androidx.room.*
//import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName= "estado")
data class Estado(
    @PrimaryKey (autoGenerate = true)
    val id: Int,
    @ColumnInfo(name="nombre")
    var nombre: String,
    @ColumnInfo(name="capital")
    var capital: String,
    @ColumnInfo(name="poblacion")
    var poblacion: Int,
    @ColumnInfo(name="costas")
    var costas: Int
) : Parcelable
