package com.example.mychallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import com.example.mychallenge.data.mapper.toChallenge
import com.example.mychallenge.presentation.NavGraphs
import com.example.mychallenge.presentation.destinations.*
import com.example.mychallenge.presentation.details_screen.ChallengeDetailsScreen
import com.example.mychallenge.presentation.new_challenge.duration.DurationScreen
import com.example.mychallenge.presentation.new_challenge.info.InfoScreen
import com.example.mychallenge.presentation.new_challenge.name.NameScreen
import com.example.mychallenge.presentation.new_challenge.type.ChallengeTypeScreen
import com.example.mychallenge.ui.theme.MyChallengeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyChallengeTheme {
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    DestinationsNavHost(navGraph = NavGraphs.root) {
                        composable(ChallengeTypeScreenDestination) {
                            ChallengeTypeScreen(
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(NameScreenDestination) {
                            NameScreen(
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState,
                                challengeType = navArgs.challengeType
                            )
                        }
                        composable(DurationScreenDestination) {
                            DurationScreen(
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState,
                                challengeType = navArgs.challengeType,
                                challengeName = navArgs.challengeName
                            )
                        }
                        composable(InfoScreenDestination) {
                            InfoScreen(
                                challengeType = navArgs.challengeType,
                                challengeName = navArgs.challengeName,
                                challengeDuration = navArgs.challengeDuration,
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(ChallengeDetailsScreenDestination) {
                            ChallengeDetailsScreen(
                                challenge = navArgs
                            )
                        }
                    }
                }
            }
        }
    }
}