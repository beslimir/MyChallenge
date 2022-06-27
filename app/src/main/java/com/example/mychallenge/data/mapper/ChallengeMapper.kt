package com.example.mychallenge.data.mapper

import com.example.mychallenge.data.local.ChallengeEntity
import com.example.mychallenge.domain.model.Challenge

fun ChallengeEntity.toChallenge(): Challenge {
    return Challenge(
        name = name,
        duration = duration,
        info = info,
        id = id
    )
}

fun Challenge.toChallengeEntity(): ChallengeEntity {
    return ChallengeEntity(
        name = name,
        duration = duration,
        info = info,
        id = id
    )
}