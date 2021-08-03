package com.example.dexter.model

data class JobEntity(
    val id: Int,
    val category: String,
    val company_logo_url: String,
    val company_name: String,
    val job_type: String,
    val publication_date: String,
    val salary: String,
    val title: String,
    val url: String
)