package com.example.mychallenge.presentation.new_challenge.type

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeTypeViewModel @Inject constructor(): ViewModel() {

    var type by mutableStateOf("")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick() {
        viewModelScope.launch {
            val isTypeDefined = type.isEmpty()
            if (!isTypeDefined) {
                _uiEvent.send(
                    UiEvent.ShowSnackBar("Please, choose a type for your challenge!")
                )
                return@launch
            }
            _uiEvent.send(UiEvent.Success)
        }
    }

}