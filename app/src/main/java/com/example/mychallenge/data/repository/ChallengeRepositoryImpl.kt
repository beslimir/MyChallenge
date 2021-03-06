package com.example.mychallenge.data.repository

import com.example.mychallenge.data.local.ChallengeDAO
import com.example.mychallenge.data.local.ChallengeDatabase
import com.example.mychallenge.data.mapper.toChallenge
import com.example.mychallenge.data.mapper.toChallengeEntity
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.repository.ChallengeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

class ChallengeRepositoryImpl(
    private val dao: ChallengeDAO
): ChallengeRepository {

    override fun getChallenges(): Flow<List<Challenge>> {
        return dao.getChallenges()
            .map { entities ->
                entities.map {
                    it.toChallenge()
                }
            }
    }

    override suspend fun insertNewChallenge(challenge: Challenge) {
        dao.insertNewChallenge(challenge.toChallengeEntity())
    }

    override suspend fun removeChallenge(challenge: Challenge) {
        dao.removeChallenge(challenge.toChallengeEntity())
    }
}