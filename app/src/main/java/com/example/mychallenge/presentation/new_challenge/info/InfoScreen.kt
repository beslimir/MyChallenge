package com.example.mychallenge.presentation.new_challenge.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.presentation.LocalSpacing
import com.example.mychallenge.presentation.destinations.HomeScreenDestination
import com.example.mychallenge.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun InfoScreen(
    nameID: String,
    navigator: DestinationsNavigator,
    viewModel: InfoViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> {
                    navigator.navigate(
                        HomeScreenDestination()
                    ) {
                        popUpTo(route = HomeScreenDestination.route) {
                            inclusive = true
                        }
                    }
                }
                is UiEvent.Failure -> {
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
                text = "Do you have something to say before you're knocked out?"
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicTextField(
                value = viewModel.info,
                onValueChange = viewModel::onInfoEnter,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
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
                onClick = viewModel::onNextClick,
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