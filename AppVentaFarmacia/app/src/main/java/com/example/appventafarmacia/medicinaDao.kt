package com.example.appventafarmacia

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface medicinaDao {

    @Query("SELECT * FROM medicinas")
    fun getAll(): LiveData<List<medicina>>

    @Query("SELECT * FROM medicinas WHERE idmedicina = :id")
    fun get(id:Int): LiveData<medicina>


    @Insert
    fun insertAll(vararg medicinas:medicina)

    @Update
    fun update (Medicina: medicina)

    /*  falta @delate*/
    @Delete
    fun delete(Medicina : medicina)


}