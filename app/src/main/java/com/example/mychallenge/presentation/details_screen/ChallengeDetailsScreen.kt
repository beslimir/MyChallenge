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
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
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
import com.example.mychallenge.presentation.components.CustomButton
import com.example.mychallenge.presentation.components.InfoScreenSection
import com.example.mychallenge.presentation.components.VerticalDivider
import com.example.mychallenge.ui.theme.*
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
        color = InfoTextColor,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        fontSize = 18.sp
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
            .background(BackgroundColor)
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
            val gradient = Brush.verticalGradient(
                colors = listOf(HeaderColor, HeaderColor2),
                startY = 180f,
                endY = 180f / 3
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(gradient)
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
                            color = HeaderTextColor
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
                            color = HeaderTextColor,
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
                        text = "Type: ${challenge.type}",
                        fontSize = 16.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                        style = TextStyle(
                            color = HeaderTextColor
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
                        color = ProgressBarColor,
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
                            color = ProgressBarTextColor,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${animatedPassedDays.value} days",
                            style = infoTextStyle,
                            color = ProgressBarTextColor,
                            fontSize = 18.sp
                        )
                    }
                    Column {
                        Text(
                            text = "Total:",
                            style = infoTextStyle,
                            color = ProgressBarTextColor,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "${challenge.duration} days",
                            style = infoTextStyle,
                            color = ProgressBarTextColor,
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
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "info",
                    tint = IconColor1
                )
                Text(
                    text = challenge.info,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(10.dp, bottom = 0.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = IconColor1
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
                color = DividerColor
            )
            Canvas(modifier = Modifier) {
                drawCircle(
                    color = DividerColor,
                    radius = 8f
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .weight(1f),
                color = DividerColor
            )
        }

        VerticalDivider(
            dividerColor = DividerColor,
            height = 20.dp
        )

        InfoScreenSection(
            topLeftText = "Starting",
            topRightText = "Duration",
            bottomLeftText = challenge.toChallenge().date.toString(),
            bottomRightText = "${challenge.duration} days",
            image = Icons.Filled.Notifications,
            imageContent = "Start"
        )

        VerticalDivider(
            dividerColor = DividerColor,
            height = 20.dp
        )

        InfoScreenSection(
            topLeftText = "Ending",
            topRightText = "Remaining",
            bottomLeftText = "${viewModel.getEndDate(challenge.toChallenge())}",
            bottomRightText = "${viewModel.getNumOfRemainingDays(challenge.toChallenge())} days",
            image = Icons.Filled.Star,
            imageContent = "End"
        )

        //TODO: Move to viewModel (State) and finish implementation
        //Bottom Line
        Spacer(modifier = Modifier.weight(1f))

        var textColor1 by remember {
            mutableStateOf(ButtonColorFailed)
        }
        var textColor2 by remember {
            mutableStateOf(ButtonColorSucceeded)
        }
        var backgroundColor1 by remember {
            mutableStateOf(BackgroundColor)
        }
        var backgroundColor2 by remember {
            mutableStateOf(BackgroundColor)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomButton(
                text = "Give up!",
                borderColor = ButtonColorFailed,
                textColor = textColor1,
                backgroundColor = backgroundColor1,
                onClick = {
                    if (backgroundColor1 == BackgroundColor) {
                        backgroundColor1 = ButtonColorFailed
                        textColor1 = BackgroundColor
                    } else {
                        backgroundColor1 = BackgroundColor
                        textColor1 = ButtonColorFailed
                    }
                }
            )
            CustomButton(
                text = "In progress...",
                borderColor = ButtonColorSucceeded,
                textColor = textColor2,
                backgroundColor = backgroundColor2,
                onClick = {
                    if (backgroundColor2 == BackgroundColor) {
                        backgroundColor2 = ButtonColorSucceeded
                        textColor2 = BackgroundColor
                    } else {
                        backgroundColor2 = BackgroundColor
                        textColor2 = ButtonColorSucceeded
                    }
                }
            )
        }


    }

}