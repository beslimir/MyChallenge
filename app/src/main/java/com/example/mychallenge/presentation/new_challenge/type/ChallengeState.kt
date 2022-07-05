package com.example.mychallenge.presentation.new_challenge.type

import com.example.mychallenge.domain.model.ChallengeType

data class ChallengeState(
    val isPopupVisible: Boolean = false,
    val challengeTypeSelected: ChallengeType = ChallengeType.Other
)
