package com.example.mychallenge.presentation.details_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mychallenge.data.local.ChallengeEntity
import com.example.mychallenge.data.mapper.toChallenge
import com.ramcosta.composedestinations.annotation.Destination

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Destination(
    navArgsDelegate = ChallengeEntity::class
)
fun ChallengeDetailsScreen(
    challenge: ChallengeEntity,
    viewModel: ChallengeDetailsViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    val animatedPassedDays = animateIntAsState(targetValue = state.passedDays)
    val progressRatio = remember {
        Animatable(0f)
    }
    val infoTextStyle = TextStyle(
        color = Color.Gray,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        fontSize = 16.sp
    )

    LaunchedEffect(key1 = true) {
        viewModel.changeStateValue(challenge.toChallenge())
    }

    LaunchedEffect(key1 = state) {
        progressRatio.animateTo(
            targetValue = state.passedDays / challenge.duration.toFloat()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        //Header Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(20f))
                .border(
                    width = 1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(20f)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Green)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = challenge.name,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Days remaining: ${state.remainingDays}",
                        fontSize = 38.sp,
                        modifier = Modifier.padding(start = 16.dp),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = challenge.type,
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
        }

        //ProgressBar Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Canvas(modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                ) {
                    val progressWidth = progressRatio.value * size.width
                    drawRoundRect(
                        color = Color.White,
                        size = size,
                        cornerRadius = CornerRadius(100f)
                    )
                    drawRoundRect(
                        color = Color.Green,
                        size = Size(
                            width = progressWidth,
                            height = size.height
                        ),
                        cornerRadius = CornerRadius(100f)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Current:",
                            style = infoTextStyle,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${animatedPassedDays.value} days",
                            style = infoTextStyle,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    Column {
                        Text(
                            text = "Total:",
                            style = infoTextStyle,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${challenge.duration} days",
                            style = infoTextStyle,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }

        //Info Section
        if (challenge.info.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = challenge.info,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(10.dp, bottom = 0.dp),
                    style = infoTextStyle,
                    color = Color.White
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                color = Color.Gray
            )
            Canvas(modifier = Modifier) {
                drawCircle(
                    color = Color.Gray,
                    radius = 8f
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                color = Color.Gray
            )
        }


        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "Start:",
                    style = infoTextStyle
                )
                Text(
                    text = "End:",
                    style = infoTextStyle
                )
                Text(
                    text = "Duration:",
                    style = infoTextStyle
                )
                Text(
                    text = "Try:",
                    style = infoTextStyle
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                horizontalAlignment = Alignment.Start
            ) {
                val endDate = challenge.toChallenge().date.plusDays(challenge.duration.toLong())

                Text(
                    text = challenge.toChallenge().date.toString(),
                    style = infoTextStyle
                )
                Text(
                    text = "$endDate",
                    style = infoTextStyle
                )
                Text(
                    text = "${challenge.duration} days",
                    style = infoTextStyle
                )
                Text(
                    text = "1",
                    style = infoTextStyle
                )
            }

        }


    }

}