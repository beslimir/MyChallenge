package com.example.mychallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ChallengeEntity::class],
    version = 3
)
abstract class ChallengeDatabase: RoomDatabase() {

    abstract val dao: ChallengeDAO

}