package com.example.mychallenge.data.repository

import com.example.mychallenge.data.local.ChallengeDatabase
import com.example.mychallenge.domain.repository.ChallengeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeRepositoryImpl @Inject constructor(
    private val db: ChallengeDatabase
): ChallengeRepository {

    private val dao = db.dao

}