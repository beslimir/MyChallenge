package com.example.mychallenge.domain.use_cases

import android.annotation.SuppressLint
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.repository.ChallengeRepository
import java.time.LocalDate

class InsertNewChallengeUseCase(
    private val repository: ChallengeRepository
) {
    @SuppressLint("NewApi")
    suspend operator fun invoke(challenge: Challenge) {
        repository.insertNewChallenge(
            Challenge(
                type = challenge.type,
                name = challenge.name,
                duration = challenge.duration,
                info = challenge.info,
                date = LocalDate.now()
            )
        )
    }
}