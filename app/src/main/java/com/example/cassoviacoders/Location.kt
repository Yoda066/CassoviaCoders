package com.example.cassoviacoders

data class Location(
    val id: Int,
    val title: String,
    val state: String = "Slovakia"
) {
    fun getFormatedTitle() = """$title, $state"""
}