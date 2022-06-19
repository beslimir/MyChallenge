package com.example.mychallenge.presentation.main_screen

import com.example.mychallenge.domain.model.Challenge

data class HomeState(
    val isListVisible: Boolean = false,
    val challenges: List<Challenge> = emptyList(),
    val isLoading: Boolean = false
)