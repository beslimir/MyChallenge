package com.example.mychallenge.domain.repository

import com.example.mychallenge.domain.model.Challenge

interface ChallengeRepository {

    suspend fun insertNewChallenge(challenge: Challenge)

}