package com.example.singleactivitysample.view.scope

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar
import java.util.concurrent.CancellationException

@ExperimentalAnimationApi
@Composable
fun LaunchedUI(mainNavigator: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val viewModel = viewModel<LaunchedEffectViewModel>()

    // implementation "androidx.compose.runtime:runtime-livedata:$compose_version"
    val isGo by viewModel.isGo.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Launched UI", scaffoldState, scope, mainNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            if (isGo == true) {
                // LaunchedEffect 사용 시, 최초 실행을 제외하고 param 값이 변경될 때 취소되고 재 시작 된다.
                // param 의 갯수는 제한되어 있지 않다.
                LaunchedEffect(isGo) {
                    try {
                        scaffoldState.snackbarHostState
                            .showSnackbar("input text : go")
                    } catch (e: CancellationException) {
                        Log.e("CancelText", "in catch")
                    }
                }
            }

            LaunchedScreen(viewModel)
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, mainNavigator)
        },
        drawerGesturesEnabled = false
    )
}

@Composable
fun LaunchedScreen(viewModel: LaunchedEffectViewModel) {

    var textState by remember { mutableStateOf("Default") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = textState,
            onValueChange = { change ->
                textState = change
                viewModel.onChangeText(textState)
            },
            label = { Text("Input go") }
        )

        RememberUpdateTestText(textState)
    }
}

@Composable
fun RememberUpdateTestText(text: String) {
    val rememberText by remember { mutableStateOf(text) }
        .apply {
            value = text
        }
    val rememberUpdatedText by rememberUpdatedState(text)

    Text("RememberText : $rememberText")
    Text("UpdatedText : $rememberUpdatedText")
}