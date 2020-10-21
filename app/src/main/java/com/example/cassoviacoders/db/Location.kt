package com.example.cassoviacoders.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class Location(

    @PrimaryKey(autoGenerate = true)
    val locId: Long,

    val title: String,

    val lat: String,
    val lon: String,
    val cityId: Int,
    val state: String = "Slovakia"

) {
    fun getFormattedTitle() = """$title, $state"""
}