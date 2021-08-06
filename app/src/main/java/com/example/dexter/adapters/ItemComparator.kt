package com.example.dexter.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.dexter.model.JobEntity

class ItemComparator : DiffUtil.ItemCallback<JobEntity>() {

    override fun areItemsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: JobEntity, newItem: JobEntity): Boolean =
        oldItem == newItem

}