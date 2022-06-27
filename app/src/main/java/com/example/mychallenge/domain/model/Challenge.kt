package com.example.mychallenge.domain.model

data class Challenge(
    val name: String,
    val duration: Int,
    val info: String?,
    val id: Int? = null
)
