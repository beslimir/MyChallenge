package com.example.mychallenge.domain.repository

import com.example.mychallenge.domain.model.Challenge
import kotlinx.coroutines.flow.Flow

interface ChallengeRepository {

    fun getChallenges(): Flow<List<Challenge>>
    suspend fun insertNewChallenge(challenge: Challenge)

}