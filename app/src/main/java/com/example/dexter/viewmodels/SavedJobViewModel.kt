package com.example.dexter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dexter.model.JobEntity
import com.example.dexter.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedJobViewModel @Inject constructor(private val repository: DatabaseRepository) :
    ViewModel() {

    fun insertJob(jobEntity: JobEntity) = viewModelScope.launch { repository.insertJob(jobEntity) }

    fun deleteJob(jobEntity: JobEntity) = viewModelScope.launch { repository.deleteSavedJob(jobEntity) }

    fun getSavedJobs(): LiveData<List<JobEntity>> = repository.getSavedJobs()

}