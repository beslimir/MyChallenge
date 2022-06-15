package com.example.mychallenge.presentation.new_challenge.duration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DurationViewModel @Inject constructor(): ViewModel() {

    var duration by mutableStateOf("1")
        private set

    fun onDurationEnter(duration: String) {
        this.duration = duration.filter { it.isDigit() }
    }



}