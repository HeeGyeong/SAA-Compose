package com.example.singleactivitysample.view.scope

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun ProduceUI(mainNavigator: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Produce UI", scaffoldState, scope, mainNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            ProduceStateScreen()
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, mainNavigator)
        },
        drawerGesturesEnabled = false
    )
}

@Composable
fun ProduceStateScreen(scope: CoroutineScope = rememberCoroutineScope()) {
    var isTimer by remember { mutableStateOf(false) }

    val timer by produceState(initialValue = 0, isTimer) {
        var job: Job? = null
        Log.d("composeLog", "ProduceState .. $value")
        if (isTimer) {
            job = scope.launch {
                while (true) {
                    delay(1000)
                    value++
                }
            }
        }

        // 종료 시 호출
        awaitDispose {
            Log.d("composeLog", "ProduceState in awaitDispose .. $value")
            job?.cancel()
        }
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Time $timer")
            Button(onClick = {
                isTimer = !isTimer
            }) {
                Text(if (isTimer) "Stop" else "Start")
            }
        }
    }
}