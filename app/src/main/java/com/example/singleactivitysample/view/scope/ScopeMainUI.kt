package com.example.singleactivitysample.view.scope

import android.text.format.DateFormat
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.singleactivitysample.navi.Scope
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.util.*

@ExperimentalAnimationApi
@Composable
fun ScopeMainUI(composeNavigator: NavController, mainNavigator: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Scope UI", scaffoldState, scope, mainNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            GoSubActivity(composeNavigator)
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, composeNavigator)
        },
        drawerGesturesEnabled = false
    )
}

@ExperimentalAnimationApi
@Composable
fun GoSubActivity(composeNavigator: NavController) {

    fun getTime(): String {
        return DateFormat.format("hh:mm:ss",
            Date(System.currentTimeMillis())).toString()
    }

    val timerFlow = flow {
        delay(1000)
        emit(getTime())
    }

    val currentTime = timerFlow.collectAsState(initial = getTime())

    Column {
        Text(
            text = currentTime.value,
            style = TextStyle(fontSize = 40.sp)
        )

        Button(
            onClick = {
                composeNavigator.navigate(Scope.Remember.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text("Go RememberCoroutine Activity")
        }

        Button(
            onClick = {
                composeNavigator.navigate(Scope.Launched.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text("Go LaunchedEffect Activity")
        }

        Button(
            onClick = {
                composeNavigator.navigate(Scope.Produce.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
        ) {
            Text("Go ProduceState Activity")
        }
    }
}