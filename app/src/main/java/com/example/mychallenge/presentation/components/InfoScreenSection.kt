package com.example.mychallenge.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mychallenge.data.mapper.toChallenge
import com.example.mychallenge.ui.theme.BlackTextColor
import com.example.mychallenge.ui.theme.IconColor2
import com.example.mychallenge.ui.theme.InfoTextColor
import com.example.mychallenge.ui.theme.LightGrayTextColor

@Composable
fun InfoScreenSection(
    topLeftText: String,
    topRightText: String,
    bottomLeftText: String,
    bottomRightText: String,
    image: ImageVector,
    imageContent: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = topLeftText,
                color = InfoTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = bottomLeftText,
                color = LightGrayTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
        Column {
            Icon(
                imageVector = image,
                contentDescription = imageContent,
                tint = IconColor2,
                modifier = Modifier
                    .size(60.dp)
                    .padding(start = 10.dp, end = 10.dp)
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = topRightText,
                color = InfoTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            Text(
                text = bottomRightText,
                color = BlackTextColor,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }
    }
}