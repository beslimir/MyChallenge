package com.example.mychallenge.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.mychallenge.ui.theme.HeaderColor
import com.example.mychallenge.ui.theme.BackgroundColor
import java.time.LocalDate
import java.time.temporal.ChronoUnit

//@Preview
@SuppressLint("NewApi")
@ExperimentalCoilApi
@Composable
fun ChallengeItem(
    challenge: Challenge,
    onRemoveClick: () -> Unit,
    onClick: () -> Unit
) {
    val endDate = challenge.date.plusDays(challenge.duration.toLong())
    val daysLeft = if (endDate > LocalDate.now()) {
        ChronoUnit.DAYS.between(LocalDate.now(), endDate)
    } else {
        0
    }
    val imageHeight = 200.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundColor)
            .clickable(onClick = onClick)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 5.dp)
                .clip(RoundedCornerShape(topStart = 20f, topEnd = 20f))
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(topStart = 20f, topEnd = 20f)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val painter = rememberImagePainter(data = challenge.type.url)
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
                    } else if (painterState is ImagePainter.State.Empty) {
                        //TODO: Empty state
                    } else {
                        //TODO: Error state
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp) //put .padding() before .clip()
                .clip(RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f))
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(bottomStart = 20f, bottomEnd = 20f)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HeaderColor),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                if (challenge.info.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
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
                                .weight(1f)
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
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
        Spacer(modifier = Modifier.height(10.dp))
    }
}