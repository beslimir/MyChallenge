package com.example.mychallenge.domain.use_cases

import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.repository.ChallengeRepository

class RemoveChallengeUseCase(
    private val repository: ChallengeRepository
) {
    suspend operator fun invoke(challenge: Challenge) {
        repository.removeChallenge(challenge)
    }
}