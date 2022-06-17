package com.example.mychallenge.di

import android.app.Application
import androidx.room.Room
import com.example.mychallenge.data.local.ChallengeDatabase
import com.example.mychallenge.data.repository.ChallengeRepositoryImpl
import com.example.mychallenge.domain.repository.ChallengeRepository
import com.example.mychallenge.domain.use_cases.GetChallengesUseCase
import com.example.mychallenge.domain.use_cases.InsertNewChallengeUseCase
import com.example.mychallenge.domain.use_cases.UseCasesWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideChallengeDatabase(app: Application) = Room.databaseBuilder(
        app,
        ChallengeDatabase::class.java,
        "challenge_database"
    ).build()

    @Singleton
    @Provides
    fun provideRepository(db: ChallengeDatabase): ChallengeRepository = ChallengeRepositoryImpl(db.dao)

    @Singleton
    @Provides
    fun provideUseCasesWrapper(repository: ChallengeRepository): UseCasesWrapper {
        return UseCasesWrapper(
            getChallengesUseCase = GetChallengesUseCase(repository),
            insertNewChallengeUseCase = InsertNewChallengeUseCase(repository)
        )
    }

}