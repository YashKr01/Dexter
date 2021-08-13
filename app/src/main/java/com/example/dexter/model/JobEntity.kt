package com.example.dexter.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dexter.utils.Constants.Companion.DATABASE_NAME
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = DATABASE_NAME)
data class JobEntity(
    @PrimaryKey(autoGenerate = false)
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
) : Parcelable