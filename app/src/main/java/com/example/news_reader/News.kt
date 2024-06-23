package com.example.news_reader

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

data class NewsResponse(
    val items: List<News>
)

@Parcelize
@Entity(tableName = "news")
data class News (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String?,
    val originallink: String?,
    val link: String?,
    val description: String?,
    val pubDate: String?
) : Parcelable