package com.example.mychallenge.data.local

import androidx.room.*
import com.example.mychallenge.domain.model.Challenge
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDAO {

    @Query("SELECT * FROM ChallengeEntity")
    fun getChallenges(): Flow<List<ChallengeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewChallenge(challenge: ChallengeEntity)

    @Delete
    suspend fun removeChallenge(challenge: ChallengeEntity)

}