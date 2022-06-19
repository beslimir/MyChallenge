package com.example.mychallenge.presentation.new_challenge.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ChallengeInputField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(
        color = MaterialTheme.colors.primaryVariant,
        fontSize = 20.sp
    ),
    modifier: Modifier = Modifier.fillMaxWidth(),
    hintText: String = "Type here...",
    hintStyle: TextStyle = TextStyle(
        color = Color.LightGray,
        fontSize = 20.sp
    ),
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = textStyle,
        decorationBox = { hint ->
            Row(modifier = modifier) {
                if (value.isEmpty()) {
                    Text(
                        text = hintText,
                        style = hintStyle,
                        modifier = modifier
                    )
                }
                hint()
            }
        }
    )
}