package com.example.singleactivitysample.navi

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.singleactivitysample.view.scope.LaunchedUI
import com.example.singleactivitysample.view.scope.ProduceUI
import com.example.singleactivitysample.view.scope.RememberUI
import com.example.singleactivitysample.view.scope.ScopeMainUI

@ExperimentalAnimationApi
@Composable
fun ScopeNaviHost(composeNavigator: NavController) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Scope.Main.route
    ) {
        composable(Scope.Main.route) { ScopeMainUI(navController, composeNavigator) }
        composable(Scope.Launched.route) { LaunchedUI(composeNavigator) }
        composable(Scope.Produce.route) { ProduceUI(composeNavigator) }
        composable(Scope.Remember.route) { RememberUI(composeNavigator) }
    }
}