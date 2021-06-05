package com.example.appventafarmacia

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [medicina::class],version = 1)
abstract class AppDB : RoomDatabase(){

    abstract fun medicinas():medicinaDao
    companion object {
        @Volatile
        private  var INSTANCE: AppDB? = null
        fun getDatabase(context: Context): AppDB {
            val tempInstance = INSTANCE

            if (tempInstance !=null){
                return  tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "app_database"
                ).build()
                INSTANCE=instance
                return instance
            }


        }

    }

}