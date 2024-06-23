package com.example.news_reader

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "news")
data class News (
    @PrimaryKey val id: String,
        val title: String?,
        val description: String?,
        val link: String?,
        val pubDate: String?
) : Parcelable