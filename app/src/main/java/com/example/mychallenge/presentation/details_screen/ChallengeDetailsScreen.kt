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
                        text = challenge.info,
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
                    Text(
                        text = animatedPassedDays.value.toString()
                    )
                    Text(
                        text = challenge.duration.toString()
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Red)
            ) {

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(Color.Blue)
            ) {

            }

        }


    }

}