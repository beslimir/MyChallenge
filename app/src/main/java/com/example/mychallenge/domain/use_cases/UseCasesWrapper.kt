package com.example.mychallenge.domain.use_cases

data class UseCasesWrapper(
    val getChallengesUseCase: GetChallengesUseCase,
    val insertNewChallengeUseCase: InsertNewChallengeUseCase
)