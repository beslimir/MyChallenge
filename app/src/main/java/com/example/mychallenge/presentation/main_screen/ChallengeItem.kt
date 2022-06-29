package com.example.mychallenge.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.mychallenge.domain.model.Challenge
import java.time.LocalDate
import java.time.temporal.ChronoUnit

//@Preview
@SuppressLint("NewApi")
@ExperimentalCoilApi
@Composable
fun ChallengeItem(
    challenge: Challenge,
    onRemoveClick: () -> Unit,
) {
    val endDate = challenge.date.plusDays(challenge.duration.toLong())
    val daysLeft = if (endDate > LocalDate.now()) {
        ChronoUnit.DAYS.between(LocalDate.now(), endDate)
    } else {
        0
    }
    val imageSize = 200.dp
    val isInfoVisible = challenge.info.isNotEmpty()
    val columnHeight = if (isInfoVisible) {
        100.dp
    } else {
        55.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(imageSize + columnHeight)
            .background(Color.Gray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageSize)
                .padding(10.dp)
                .background(Color.LightGray)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                val painter = rememberImagePainter(
                    data = "https://i.ytimg.com/vi/aZihG8ysDss/maxresdefault.jpg"
                )
                val painterState = painter.state
                var sizeImage by remember { mutableStateOf(IntSize.Zero) }
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = sizeImage.height.toFloat(),
                    endY = sizeImage.height.toFloat() / 10
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painter,
                        contentDescription = "My Challenge",
                        modifier = Modifier
                            .onGloballyPositioned {
                                sizeImage = it.size
                            }
                            .align(Center)
//                            .alpha(0.8f)
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    if (painterState is ImagePainter.State.Loading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .align(Center)
                        )
                    } else if (painterState is ImagePainter.State.Success) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(gradient)
                        )
                        Text(
                            text = challenge.name,
                            color = Color.White,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif,
                                fontSize = 20.sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 10.dp, start = 10.dp)
                                .align(TopStart)
                        )
                        IconButton(
                            onClick = onRemoveClick,
                            modifier = Modifier.align(BottomEnd)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "remove challenge",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(columnHeight)
                .padding(10.dp)
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            if (isInfoVisible) {
                Text(
                    text = challenge.info,
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .weight(1f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Challenge for ${challenge.duration} days.",
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Fighting $daysLeft more days.",
                    color = Color.White,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}