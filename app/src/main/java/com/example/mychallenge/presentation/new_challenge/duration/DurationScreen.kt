package com.example.mychallenge.presentation.new_challenge.duration

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.presentation.LocalSpacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun DurationScreen(
    nameID: String,
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState,
    viewModel: DurationViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current

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
    }

}