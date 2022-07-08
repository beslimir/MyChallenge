package com.example.mychallenge.presentation.new_challenge.type

import com.example.mychallenge.domain.model.ChallengeType

data class ChallengeTypeState(
    val isPopupVisible: Boolean = false,
    val isTextShownAsHint: Boolean = true,
    val challengeTypeSelected: ChallengeType? = null
)
