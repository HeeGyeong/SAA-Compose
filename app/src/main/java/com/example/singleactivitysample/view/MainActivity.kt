package com.example.singleactivitysample.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.singleactivitysample.navi.MainNaviHost

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainNaviHost()
        }
    }
}