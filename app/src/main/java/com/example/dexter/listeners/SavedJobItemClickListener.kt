package com.example.dexter.listeners

import com.example.dexter.model.JobEntity

interface SavedJobItemClickListener {
    fun onDeleteJob(jobEntity: JobEntity)
    fun onItemClickListener(jobEntity: JobEntity)
}