package com.example.dexter.api

import com.example.dexter.model.JobResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("remote-jobs")
    fun getResults(@Query("limit") limit: String): List<JobResponse>

}