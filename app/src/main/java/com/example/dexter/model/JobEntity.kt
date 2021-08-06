package com.example.dexter.model

import com.google.gson.annotations.SerializedName

data class JobEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: String,
    @SerializedName("company_logo_url")
    val image: String,
    @SerializedName("company_name")
    val companyName: String,
    @SerializedName("job_type")
    val type: String,
    @SerializedName("publication_date")
    val date: String,
    @SerializedName("salary")
    val salary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)