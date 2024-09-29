package com.example.radiopostinterview3ds.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RadioStationEntity::class], version = 1)
abstract class RadioStationDatabase : RoomDatabase() {

    abstract fun radioStationDao(): RadioStationDao

    companion object {
        @Volatile
        private var INSTANCE: RadioStationDatabase? = null

        fun getDatabase(context: Context): RadioStationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RadioStationDatabase::class.java,
                    "radio_station_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
