package com.example.mychallenge.data.mapper

import android.annotation.SuppressLint
import com.example.mychallenge.data.local.ChallengeEntity
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.model.ChallengeType
import java.time.LocalDate

@SuppressLint("NewApi")
fun ChallengeEntity.toChallenge(): Challenge {
    return Challenge(
        type = type,
        name = name,
        duration = duration,
        info = info,
        date = LocalDate.of(year, month, dayOfMonth),
        id = id
    )
}

@SuppressLint("NewApi")
fun Challenge.toChallengeEntity(): ChallengeEntity {
    return ChallengeEntity(
        type = type,
        name = name,
        duration = duration,
        info = info,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        id = id
    )
}