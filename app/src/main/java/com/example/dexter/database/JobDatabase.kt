package com.example.dexter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dexter.model.JobEntity

@Database(entities = [JobEntity::class], version = 1, exportSchema = false)
abstract class JobDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao
}