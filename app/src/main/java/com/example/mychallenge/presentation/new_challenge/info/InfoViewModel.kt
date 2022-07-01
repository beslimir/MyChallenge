package com.example.mychallenge.presentation.new_challenge.info

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.use_cases.UseCasesWrapper
import com.example.mychallenge.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val useCases: UseCasesWrapper,
) : ViewModel() {

    var info by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onInfoEnter(info: String) {
        this.info = info
    }

    fun onFinishClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }

    fun onDismissClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.DismissChallenge)
        }
    }

    @SuppressLint("NewApi")
    fun saveChallengeToDb(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCases.insertNewChallengeUseCase(
                    Challenge(
                        type = challenge.type,
                        name = challenge.name,
                        duration = challenge.duration,
                        info = challenge.info,
                        date = challenge.date
                    )
                )
                _uiEvent.send(UiEvent.SaveChallenge)
            } catch (e: IOException) {
                e.printStackTrace()
                _uiEvent.send(UiEvent.Failure("IOException: ${e.localizedMessage}"))
            } catch (e: Exception) {
                e.printStackTrace()
                _uiEvent.send(UiEvent.Failure("Exception: ${e.localizedMessage}"))
            }
        }
    }
}