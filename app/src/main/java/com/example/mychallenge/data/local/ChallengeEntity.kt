package com.example.mychallenge.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mychallenge.domain.model.ChallengeType

@Entity
data class ChallengeEntity(
    val type: String,
    val name: String,
    val duration: Int,
    val info: String,
    val dayOfMonth: Int,
    val month: Int,
    val year: Int,
    @PrimaryKey val id: Int? = null
)
