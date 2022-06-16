package com.example.mychallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface ChallengeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewChallenge(
        challenge: ChallengeEntity
    )

}