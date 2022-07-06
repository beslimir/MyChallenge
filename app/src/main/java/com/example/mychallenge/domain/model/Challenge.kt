package com.example.mychallenge.domain.model

import java.time.LocalDate

data class Challenge(
    val type: ChallengeType,
    val name: String,
    val duration: Int,
    val info: String,
    val date: LocalDate,
    val id: Int? = null
)
