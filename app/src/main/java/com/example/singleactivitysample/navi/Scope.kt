package com.example.singleactivitysample.navi

sealed class Scope(val route: String) {
    object Main: Screen(route = "main")
    object Launched: Screen(route = "launched")
    object Produce: Screen(route = "produce")
    object Remember: Screen(route = "remember")
}