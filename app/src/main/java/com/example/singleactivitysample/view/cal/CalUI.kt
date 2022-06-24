package com.example.singleactivitysample.view.cal

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar

@ExperimentalAnimationApi
@Composable
fun CalUI(composeNavigator: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Cal UI", scaffoldState, scope, composeNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            AddCounter()
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, composeNavigator)
        },
        drawerGesturesEnabled = false
    )
}

@Composable
fun AddCounter(viewModel: CalViewModel = viewModel()) {
    val counter = viewModel.counter.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "${counter.value}", fontSize = 40.sp)
        Button(onClick = { viewModel.addCounter() }) {
            Text(text = "Add Counter")
        }
        Button(onClick = { viewModel.minusCounter() }) {
            Text(text = "Minus Counter")
        }
        Button(onClick = { viewModel.multiCounter() }) {
            Text(text = "*2 Counter")
        }
        Button(onClick = { viewModel.divCounter() }) {
            Text(text = "/2 Counter")
        }
    }
}