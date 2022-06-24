package com.example.singleactivitysample.navi

sealed class Screen(val route: String) {
    object Intro: Screen(route = "intro")
    object Main: Screen(route = "main")
    object Sub: Screen(route = "sub")
    object Progress: Screen(route = "progress")
    object Movie: Screen(route = "movie")
    object Cal: Screen(route = "cal")
    object Scope: Screen(route = "scope")
}