package com.example.dexter.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dexter.model.JobEntity

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(jobEntity: JobEntity)

    @Delete
    suspend fun deleteSavedJob(jobEntity: JobEntity)

    @Query("SELECT * FROM DEXTER_DATABASE")
    fun getSavedJob(): LiveData<List<JobEntity>>

}