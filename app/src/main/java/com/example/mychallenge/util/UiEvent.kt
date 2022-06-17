package com.example.mychallenge.util

sealed class UiEvent {
    object Success: UiEvent()
    object Failure: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
