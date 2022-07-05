package com.example.mychallenge.presentation.new_challenge.type

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.domain.model.ChallengeType
import com.example.mychallenge.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengeTypeViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(ChallengeState())
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun changePopup() {
        state = state.copy(
            isPopupVisible = !state.isPopupVisible
        )
    }

    fun onChallengeTypeSelected(challengeType: ChallengeType) {
        state = state.copy(
            challengeTypeSelected = challengeType,
            isTextShownAsHint = false
        )
    }

    fun onNextClick() {
        viewModelScope.launch {
            val isTypeDefined = state.challengeTypeSelected?.let {
                it.name.let {
                    true
                }
            } ?: false

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