package com.example.singleactivitysample.view.intro

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.singleactivitysample.navi.Screen


@Composable
fun IntroUI(composeNavigator: NavController) {
    val result = remember { mutableStateOf("IntroUI") }

    Text(text = result.value)

    LaunchedEffect(result) {
        Thread.sleep(2000)
        composeNavigator.navigate(Screen.Main.route) {
            launchSingleTop = true
            popUpTo("intro")
        }
    }
}