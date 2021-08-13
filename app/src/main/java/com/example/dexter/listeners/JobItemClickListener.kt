package com.example.dexter.listeners

import com.example.dexter.model.JobEntity

interface JobItemClickListener {
    fun onCheckboxCheckedListener(jobEntity: JobEntity)
    fun onCheckboxUncheckedListener(jobEntity: JobEntity)
    fun onJobItemClick(jobEntity: JobEntity)
}