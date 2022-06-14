package com.example.mychallenge.presentation.new_challenge.name

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
class NameViewModel @Inject constructor(): ViewModel() {

    var name by mutableStateOf("NoName")
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNameEnter(name: String) {
        this.name = name
    }

    fun onNextClick() {
        viewModelScope.launch {
            val isNameEmpty = name.isEmpty()
            if (isNameEmpty) {
                _uiEvent.send(
                    UiEvent.ShowSnackBar("Your challenge should have a name...")
                )
                return@launch
            }
            _uiEvent.send(UiEvent.Success)
        }
    }
}