package com.example.appventafarmacia

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "medicinas")
class medicina (
    val nombre:String,
    val precio:Double,
    val receta:String,
    val imagen:Int,

    @PrimaryKey(autoGenerate = true)
    var idmedicina: Int=0
): Serializable