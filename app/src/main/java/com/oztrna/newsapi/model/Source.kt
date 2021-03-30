package com.oztrna.newsapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.orm.SugarRecord
import java.io.Serializable

@Entity
data class Source(
    @PrimaryKey
    val id: String,
    val name: String
): Serializable