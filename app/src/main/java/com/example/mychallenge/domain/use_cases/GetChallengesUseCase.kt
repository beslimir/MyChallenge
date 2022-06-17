package com.example.mychallenge.domain.use_cases

import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow

class GetChallengesUseCase(
    private val repository: ChallengeRepository
) {

    operator fun invoke(): Flow<List<Challenge>> {
        return repository.getChallenges()
    }


}