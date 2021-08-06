package com.example.dexter.listeners

import com.example.dexter.model.JobEntity

interface ItemClickListener {
    fun onItemClicked(jobEntity: JobEntity)
    fun onCheckboxClicked(jobEntity: JobEntity)
    fun onDelete(jobEntity: JobEntity)
}