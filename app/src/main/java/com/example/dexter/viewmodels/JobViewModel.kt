package com.example.dexter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dexter.model.JobEntity
import com.example.dexter.model.JobResponse
import com.example.dexter.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JobViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private var jobList = MutableLiveData<List<JobEntity>>()

    fun getJobList() {
        repository.getJobList("25").enqueue(object : Callback<JobResponse> {

            override fun onResponse(call: Call<JobResponse>, response: Response<JobResponse>) {
                if (response.isSuccessful && response.code() == 200) jobList.postValue(response.body()?.jobResponse)
                else jobList.postValue(null)
            }

            override fun onFailure(call: Call<JobResponse>, t: Throwable) = jobList.postValue(null)
        })
    }

    fun observeList(): LiveData<List<JobEntity>> = jobList

}