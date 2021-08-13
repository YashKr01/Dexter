package com.example.dexter.repository

import com.example.dexter.database.JobDao
import com.example.dexter.model.JobEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val jobDao: JobDao) {

    suspend fun deleteSavedJob(jobEntity: JobEntity) = jobDao.deleteSavedJob(jobEntity)

    suspend fun insertJob(jobEntity: JobEntity) = jobDao.insertJob(jobEntity)

    fun getSavedJobs() = jobDao.getSavedJob()

}