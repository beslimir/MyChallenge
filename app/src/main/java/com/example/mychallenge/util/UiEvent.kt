package com.example.mychallenge.util

sealed class UiEvent {
    object Success: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
