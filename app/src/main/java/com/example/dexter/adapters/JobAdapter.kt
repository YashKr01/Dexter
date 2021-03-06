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
import com.example.dexter.databinding.ItemJobBinding
import com.example.dexter.listeners.JobItemClickListener
import com.example.dexter.model.JobEntity

class JobAdapter(
    private val context: Context,
    private val list: ArrayList<JobEntity>,
    private val listener: JobItemClickListener,
    var databaseList: ArrayList<JobEntity>
) :
    ListAdapter<JobEntity, JobAdapter.ItemViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(ItemJobBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.view.itemCheckBox.isChecked = databaseList.contains(item)
        holder.bind(item)
    }


    inner class ItemViewHolder(val view: ItemJobBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(job: JobEntity) {
            view.apply {

                root.setOnClickListener { listener.onJobItemClick(job) }
                itemCheckBox.setOnClickListener {
                    if (itemCheckBox.isChecked) listener.onCheckboxCheckedListener(job)
                    else listener.onCheckboxUncheckedListener(job)
                }

                textJobTitle.text = job.title
                textJobCompanyName.text = job.companyName
                textJobDate.text = job.date
                itemJobSalary.text = job.salary

                Glide.with(context)
                    .load(job.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemJobLogo)

                textJobType.isAllCaps = true

                if (job.type == "full_time") {
                    textJobType.text = job.type

                    textJobType
                        .setTextColor(ContextCompat.getColor(context, R.color.colorGreenDark))

                    textJobType.background =
                        ContextCompat.getDrawable(context, R.drawable.text_background_green)
                } else {
                    textJobType.text = job.type

                    textJobType
                        .setTextColor(ContextCompat.getColor(context, R.color.colorYellowDark))

                    textJobType.background =
                        ContextCompat.getDrawable(context, R.drawable.text_background_yellow)
                }


            }
        }

    }

}