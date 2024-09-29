package com.example.radiopostinterview3ds.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RadioStationEntity::class], version = 2)  // Updated version to 2
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
                )
                    .fallbackToDestructiveMigration()  // Wipes and rebuilds instead of migrating
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
