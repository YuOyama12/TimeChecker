package com.yuoyama12.timechecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuoyama12.timechecker.ui.main.MainScreen
import com.yuoyama12.timechecker.ui.resultlist.ResultListScreen
import com.yuoyama12.timechecker.ui.theme.TimeCheckerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeCheckerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Main.route
                    ) {
                        composable(Screen.Main.route) {
                            MainScreen(
                                navigateToResultListScreen = {
                                    if (it.lifecycle.currentState == Lifecycle.State.RESUMED) {
                                        navController.navigate(Screen.ResultList.route)
                                    }
                                }
                            )
                        }
                        composable(Screen.ResultList.route) {
                            ResultListScreen(
                                onBackArrowClick = { navController.popBackStack() }
                            )
                        }
                    }

                }
            }
        }
    }
}