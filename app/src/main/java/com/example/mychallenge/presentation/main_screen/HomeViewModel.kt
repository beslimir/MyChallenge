package com.example.mychallenge.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mychallenge.domain.use_cases.InsertNewChallengeUseCase
import com.example.mychallenge.domain.use_cases.UseCasesWrapper
import com.example.mychallenge.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCasesWrapper
): ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getChallengesJob: Job? = null

    init {
        getChallenges()
    }

    private fun getChallenges() {
        getChallengesJob?.cancel()
        getChallengesJob = useCases.getChallengesUseCase().onEach { challenges ->
            state = state.copy(
                isListVisible = challenges.isNotEmpty(),
                challenges = challenges
            )
        }.launchIn(viewModelScope)
    }


}