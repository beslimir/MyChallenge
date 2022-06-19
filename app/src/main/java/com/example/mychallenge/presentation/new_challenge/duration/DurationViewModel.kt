package com.example.mychallenge.presentation.new_challenge.duration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DurationViewModel @Inject constructor(): ViewModel() {

    //length of the string which represents max number of days days
    companion object {
        private const val MAX_LEN = 3
    }

    var duration by mutableStateOf("")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onDurationEnter(duration: String) {
        this.duration = duration.filter {
            it.isDigit()
        }.take(MAX_LEN)
    }

    fun onNextClick() {
        viewModelScope.launch {
            if (duration.isEmpty() || duration.isBlank()) {
                _uiEvent.send(
                    UiEvent.ShowSnackBar("You must have a time frame...")
                )
                return@launch
            }
            _uiEvent.send(UiEvent.Success)
        }
    }


}