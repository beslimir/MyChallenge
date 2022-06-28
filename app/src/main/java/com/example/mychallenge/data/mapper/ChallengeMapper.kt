package com.example.mychallenge.data.mapper

import android.annotation.SuppressLint
import com.example.mychallenge.data.local.ChallengeEntity
import com.example.mychallenge.domain.model.Challenge
import java.time.LocalDate

@SuppressLint("NewApi")
fun ChallengeEntity.toChallenge(): Challenge {
    return Challenge(
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
        name = name,
        duration = duration,
        info = info,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        id = id
    )
}