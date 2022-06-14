package com.example.mychallenge.presentation.new_challenge.name

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
import com.example.mychallenge.util.UiEvent

@Composable
fun NameScreen(
    scaffoldState: ScaffoldState,
    onNextClick: () -> Unit,
    viewModel: NameViewModel = hiltViewModel(),
) {
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
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
                text = "What will you challenge?"
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            BasicTextField(
                value = viewModel.name,
                onValueChange = viewModel::onNameEnter,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
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