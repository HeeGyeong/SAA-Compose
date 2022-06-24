package com.example.singleactivitysample.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.singleactivitysample.view.cal.CalUI
import com.example.singleactivitysample.view.intro.IntroUI
import com.example.singleactivitysample.view.main.MainUI
import com.example.singleactivitysample.view.movie.MovieUI
import com.example.singleactivitysample.view.progress.ProgressUI
import com.example.singleactivitysample.view.sub.SubUI

@ExperimentalAnimationApi
@Composable
fun MainNaviHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Intro.route
    ) {
        composable(Screen.Intro.route) { IntroUI(navController) }
        composable(Screen.Main.route) { MainUI(navController) }
        composable(Screen.Sub.route) { SubUI(navController) }
        composable(Screen.Progress.route) { ProgressUI(navController) }
        composable(Screen.Movie.route) { MovieUI(navController) }
        composable(Screen.Cal.route) { CalUI(navController) }
        composable(Screen.Scope.route) { ScopeNaviHost(navController) }
    }
}