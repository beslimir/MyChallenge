package com.example.mychallenge.presentation.details_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mychallenge.domain.model.Challenge
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class ChallengeDetailsViewModel @Inject constructor(

) : ViewModel() {

    var state by mutableStateOf(ChallengeDetailsState())
        private set

    fun changeStateValue(challenge: Challenge) {
        state = state.copy(
            passedDays = getNumOfPassedDays(challenge = challenge),
            remainingDays = getNumOfRemainingDays(challenge = challenge)
        )
    }


    private fun getNumOfPassedDays(challenge: Challenge): Int {
        return ChronoUnit.DAYS.between(challenge.date, LocalDate.now()).toInt()
    }

    private fun getNumOfRemainingDays(challenge: Challenge): Int {
        val endDate = challenge.date.plusDays(challenge.duration.toLong())
        return ChronoUnit.DAYS.between(LocalDate.now(), endDate).toInt()
    }


}