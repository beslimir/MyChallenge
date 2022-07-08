package com.example.mychallenge.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mychallenge.ui.theme.BackgroundColor

@Composable
fun CustomButton(
    text: String,
    buttonColor: Color,
) {

    var bColor by remember {
        mutableStateOf(buttonColor)
    }
    var bgrdColor by remember {
        mutableStateOf(BackgroundColor)
    }

    Box(
        modifier = Modifier
            .height(40.dp)
            .width(120.dp)
            .clickable {
                bColor = if (bColor == BackgroundColor) {
                    buttonColor
                } else {
                    BackgroundColor
                }
                bgrdColor = if (bgrdColor == BackgroundColor) {
                    buttonColor
                } else {
                    BackgroundColor
                }
            }
            .clip(RoundedCornerShape(20f))
            .border(
                width = 2.dp,
                color = bColor,
                shape = RoundedCornerShape(20f)
            )
            .background(bgrdColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = bColor,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
    }

}