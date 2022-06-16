package com.example.mychallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import com.example.mychallenge.presentation.NavGraphs
import com.example.mychallenge.presentation.destinations.DurationScreenDestination
import com.example.mychallenge.presentation.destinations.NameScreenDestination
import com.example.mychallenge.presentation.new_challenge.duration.DurationScreen
import com.example.mychallenge.presentation.new_challenge.duration.DurationScreenNavArgs
import com.example.mychallenge.presentation.new_challenge.name.NameScreen
import com.example.mychallenge.ui.theme.MyChallengeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

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
                        composable(NameScreenDestination) {
                            NameScreen(
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(DurationScreenDestination) {
                            DurationScreen(
                                navArgs = DurationScreenNavArgs(""),
                                navigator = destinationsNavigator,
                                scaffoldState = scaffoldState
                            )
                        }

                    }
                }
            }
        }
    }
}