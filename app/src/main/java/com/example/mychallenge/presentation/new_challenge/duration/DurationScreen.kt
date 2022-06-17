package com.example.mychallenge.presentation.new_challenge.duration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.presentation.LocalSpacing
import com.example.mychallenge.presentation.destinations.InfoScreenDestination
import com.example.mychallenge.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class DurationScreenNavArgs(
    val challengeName: String
)

@Composable
@Destination(navArgsDelegate = DurationScreenNavArgs::class)
fun DurationScreen(
    navArgs: DurationScreenNavArgs,
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState,
    viewModel: DurationViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navigator.navigate(
                    InfoScreenDestination(
                        challengeName = navArgs.challengeName,
                        challengeDuration = viewModel.duration.toInt()
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
                text = "For how long can you do it?"
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    BasicTextField(
                        value = viewModel.duration,
                        onValueChange = viewModel::onDurationEnter,
                        modifier = Modifier
                            .width(IntrinsicSize.Min)
                    )
                }
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                Text(
                    text = "days",
                    modifier = Modifier
                        .width(IntrinsicSize.Min)
                        .weight(1f)
                )
            }
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