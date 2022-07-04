package com.example.mychallenge.presentation.new_challenge.type

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.domain.model.ChallengeType.Companion.challengeTypeList
import com.example.mychallenge.presentation.LocalSpacing
import com.example.mychallenge.presentation.destinations.NameScreenDestination
import com.example.mychallenge.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun ChallengeTypeScreen(
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState,
    viewModel: ChallengeTypeViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> navigator.navigate(
                    NameScreenDestination(
                        challengeType = "sports"
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Which type of challenge should it be this time?",
                fontSize = 30.sp
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    viewModel.changePopup()
                }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "challenge_type",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
                Text(
                    text = "Sports",
                    fontSize = 20.sp
                )
            }
            AnimatedVisibility(
                visible = state.isPopupVisible,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                OrderSection()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Bottom
            ) {
                Button(
                    onClick = viewModel::onNextClick,
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
    }
}

@Composable
fun OrderSection() {
    Column {
        for (i in challengeTypeList.indices step 2) {
            DefaultRadioButton(
                selected = i == 0,
                position = i
            )
        }
    }
}

@Composable
fun DefaultRadioButton(
    selected: Boolean,
    position: Int,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selected,
                onClick = { },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colors.primary,
                    unselectedColor = MaterialTheme.colors.onBackground
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = challengeTypeList[position].name,
                style = MaterialTheme.typography.body1
            )
        }

        if ((position + 1) % 2 == 1 && position + 1 < challengeTypeList.size) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected,
                    onClick = { },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary,
                        unselectedColor = MaterialTheme.colors.onBackground
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = challengeTypeList[position + 1].name,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}