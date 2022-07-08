package com.example.mychallenge.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mychallenge.ui.theme.DividerColor

@Composable
fun VerticalDivider(
    dividerColor: Color,
    height: Dp
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Divider(
            modifier = Modifier
                .height(height)
                .width(1.dp),
            color = dividerColor
        )
    }
}