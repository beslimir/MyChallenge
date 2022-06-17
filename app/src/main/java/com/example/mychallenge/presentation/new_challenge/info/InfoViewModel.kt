package com.example.mychallenge.presentation.new_challenge.info

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.domain.use_cases.InsertNewChallengeUseCase
import com.example.mychallenge.domain.use_cases.UseCasesWrapper
import com.example.mychallenge.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val useCases: UseCasesWrapper
): ViewModel() {

    var info by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onInfoEnter(info: String) {
        this.info = info
    }

    //TODO: Implement saving to DB: Check if we need Failure (InfoScreen)
    fun onNextClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }

    fun onDismissClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Failure)
        }
    }

    //TODO: Do proper handling
    fun saveChallengeToDb(challenge: Challenge) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                useCases.insertNewChallengeUseCase(
                    Challenge(
                        name = challenge.name,
                        duration = challenge.duration,
                        info = challenge.info
                    )
                )
                Log.d("aaaa", "saveChallengeToDb: saved")
            } catch (e: IOException) {
                e.printStackTrace()
                Log.d("aaaa", "saveChallengeToDb: IOException")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("aaaa", "saveChallengeToDb: Exception")
            }
        }
    }
}