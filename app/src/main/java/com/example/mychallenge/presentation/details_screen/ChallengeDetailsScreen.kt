package com.example.mychallenge.presentation.details_screen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.mychallenge.data.local.ChallengeEntity
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination(
    navArgsDelegate = ChallengeEntity::class
)
fun ChallengeDetailsScreen(
    challenge: ChallengeEntity
) {
    Text(text = challenge.name)

}