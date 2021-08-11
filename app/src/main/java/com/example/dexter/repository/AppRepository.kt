package com.example.dexter.repository

import com.example.dexter.api.ApiInterface
import com.example.dexter.model.JobResponse
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun getJobList(limit: String): Call<JobResponse> = apiInterface.getResults(limit)

}