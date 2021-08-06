package com.example.dexter.model

import com.google.gson.annotations.SerializedName

data class JobResponse(
    @SerializedName("jobs")
    val jobResponse: List<JobEntity>
)