package com.example.mychallenge.presentation.new_challenge.info

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.mychallenge.domain.model.Challenge
import com.example.mychallenge.presentation.LocalSpacing
import com.example.mychallenge.presentation.destinations.HomeScreenDestination
import com.example.mychallenge.presentation.new_challenge.components.ChallengeInputField
import com.example.mychallenge.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate

@SuppressLint("NewApi")
@ExperimentalCoilApi
@Composable
@Destination
fun InfoScreen(
    challengeName: String,
    challengeDuration: Int,
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState,
    viewModel: InfoViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                    viewModel.saveChallengeToDb(
                        Challenge(
                            name = challengeName,
                            duration = challengeDuration,
                            info = viewModel.info,
                            date = LocalDate.now()
                        )
                    )
                }
                is UiEvent.Failure -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is UiEvent.SaveChallenge -> {
                    navigator.navigate(
                        HomeScreenDestination()
                    ) {
                        popUpTo(route = HomeScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                is UiEvent.DismissChallenge -> {
                    navigator.navigate(
                        HomeScreenDestination()
                    ) {
                        popUpTo(route = HomeScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                else -> Unit
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Do you have something to say before you're knocked out?",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            ChallengeInputField(
                value = viewModel.info,
                onValueChange = viewModel::onInfoEnter
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = viewModel::onDismissClick,
                enabled = true,
                shape = RoundedCornerShape(80.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                ),
            ) {
                Text(
                    text = "Dismiss",
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = viewModel::onFinishClick,
                enabled = true,
                shape = RoundedCornerShape(80.dp)
            ) {
                Text(
                    text = "Finish",
                    modifier = Modifier.padding(spacing.spaceSmall)
                )
            }
        }
    }
}