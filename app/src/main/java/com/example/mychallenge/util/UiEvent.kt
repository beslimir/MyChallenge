package com.example.mychallenge.util

sealed class UiEvent {
    object Success: UiEvent()
    data class Failure(val message: String): UiEvent()
    object SaveChallenge: UiEvent()
    object DismissChallenge: UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}
