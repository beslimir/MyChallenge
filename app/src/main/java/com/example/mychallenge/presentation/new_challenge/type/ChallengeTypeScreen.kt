package com.example.mychallenge.presentation.new_challenge.type

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.domain.model.ChallengeType
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
                    //Null won't happen because of Next button functionality
                    NameScreenDestination(
                        challengeType = state.challengeTypeSelected?.name ?: "Sports"
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
                IconButton(
                    onClick = {
                        viewModel.changePopup()
                    },
                    modifier = Modifier
                        .then(Modifier.size(32.dp))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "challenge_type",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
                Text(
                    text = state.challengeTypeSelected?.name ?: "Click on icon to choose...",
                    fontSize = 20.sp,
                    color = if (state.isTextShownAsHint) {
                        Color.LightGray
                    } else {
                        Color.Black
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            AnimatedVisibility(
                visible = state.isPopupVisible,
                enter = fadeIn() + slideInHorizontally(),
                exit = fadeOut() + slideOutHorizontally()
            ) {
                OrderSection(
                    challengeType = state.challengeTypeSelected,
                    onChallengeTypeChange = {
                        viewModel.onChallengeTypeSelected(it)
                    }
                )
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
fun OrderSection(
    challengeType: ChallengeType?,
    onChallengeTypeChange: (ChallengeType) -> Unit,
) {
    Column {
        for (i in challengeTypeList.indices step 2) {
            DefaultRadioButton(
                position = i,
                challengeType = challengeType,
                onSelect = {
                    onChallengeTypeChange(it)
                }
            )
        }
    }
}

@Composable
fun DefaultRadioButton(
    position: Int,
    challengeType: ChallengeType?,
    onSelect: (ChallengeType) -> Unit,
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
                selected = challengeTypeList[position] == challengeType,
                onClick = {
                    onSelect(challengeTypeList[position])
                },
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

        var pos = position
        if (position + 1 < challengeTypeList.size) {
            pos += 1
        }

        if (pos % 2 == 1) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = challengeTypeList[pos] == challengeType,
                    onClick = {
                        onSelect(challengeTypeList[pos])
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colors.primary,
                        unselectedColor = MaterialTheme.colors.onBackground
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = challengeTypeList[pos].name,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}