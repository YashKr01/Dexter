package com.example.dexter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.dexter.R
import com.example.dexter.databinding.ItemSavedJobBinding
import com.example.dexter.listeners.SavedJobItemClickListener
import com.example.dexter.model.JobEntity

class SavedJobAdapter(
    private val context: Context,
    private val list: ArrayList<JobEntity>,
    private val listener: SavedJobItemClickListener
) :
    ListAdapter<JobEntity, SavedJobAdapter.ItemViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            ItemSavedJobBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ItemViewHolder(private val binding: ItemSavedJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: JobEntity) {
            binding.apply {

                root.setOnClickListener { listener.onItemClickListener(job) }
                imageDelete.setOnClickListener { listener.onDeleteJob(job) }
                itemSavedJobTitle.text = job.title
                itemSavedJobCompanyName.text = job.companyName
                itemJobSavedDate.text = job.date
                itemJobSavedSalary.text = job.salary

                Glide.with(context)
                    .load(job.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemSavedJobLogo)

                itemJobSavedType.isAllCaps = true
                if (job.type == "full_time") {
                    itemJobSavedType.text = job.type
                    itemJobSavedType.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorGreenDark
                        )
                    )
                    itemJobSavedType.background =
                        ContextCompat.getDrawable(context, R.drawable.text_background_green)
                } else {
                    itemJobSavedType.text = job.type
                    itemJobSavedType.setTextColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorYellowDark
                        )
                    )
                    itemJobSavedType.background =
                        ContextCompat.getDrawable(context, R.drawable.text_background_yellow)
                }
            }
        }

    }


}