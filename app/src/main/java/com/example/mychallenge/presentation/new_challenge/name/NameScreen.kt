package com.example.mychallenge.presentation.new_challenge.name

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.presentation.LocalSpacing
import com.example.mychallenge.presentation.destinations.DurationScreenDestination
import com.example.mychallenge.presentation.new_challenge.components.ChallengeInputField
import com.example.mychallenge.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun NameScreen(
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState,
    viewModel: NameViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navigator.navigate(
                    DurationScreenDestination(
                        challengeName = viewModel.name
                    )
                )
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
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
                text = "What will you challenge?",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            ChallengeInputField(
                value = viewModel.name,
                onValueChange = viewModel::onNameEnter
            )
        }
        Button(
            onClick = viewModel::onNextClick,
            modifier = Modifier
                .align(Alignment.BottomEnd),
            enabled = true,
            shape = RoundedCornerShape(80.dp)
        ) {
            Text(
                text = "Next",
                modifier = Modifier.padding(spacing.spaceSmall)
            )
        }
    }
}