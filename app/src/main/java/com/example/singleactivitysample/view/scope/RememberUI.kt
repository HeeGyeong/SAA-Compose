package com.example.singleactivitysample.view.scope

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun RememberUI(mainNavigator: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    var changeUI by remember { mutableStateOf(1) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Remember UI", scaffoldState, scope, mainNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            when (changeUI) {
                1 -> {
                    // scope를 넣지 않으면 FirstScreen 생성 시 rememberCoroutineScope()를 호출함.
                    CoroutineScreen(scaffoldState,
                        changeState = {
                            changeUI = 2
                        }
                    )
                }
                2 -> {
                    // lifecycleScope은 Activity의 생명주기를 따름.
                    CoroutineScreen(scaffoldState,
                        changeState = {
                            changeUI = 3
                        },
//                        lifecycleScope
                    )
                }
                else -> {
                    CoroutineTempScreen()
                }
            }
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, mainNavigator)
        },
        drawerGesturesEnabled = false
    )
}

/**
 * rememberCoroutineScope() 이 호출되는 경우,
 * 해당 Composable의 lifeCycle을 따라간다.
 *
 * 따라서, 화면 이동에 상관 없이 유지되어야 하는 코루틴이 아니면
 * rememberCoroutineScope()를 통해 생성하여 사용하도록 하는 것이 좋다.
 *
 * LaunchedScope은 composable function으로 composable function 안에서만 호출이 가능하기 때문에
 * 이외의 부분에서 coroutine scope을 얻기 위해서는 rememberCoroutineScope을 사용해야 합니다.
 * 라고 하는데, 정확하게 이해는 하지 못했음.
 */
@Composable
fun CoroutineScreen(
    scaffoldState: ScaffoldState,
    changeState: () -> Unit,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) {
    Column {
        Button(
            onClick = {
                coroutineScope.launch {
                    scaffoldState.snackbarHostState
                        .showSnackbar("Show Snackbar $coroutineScope")
                }
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
        ) {
            Text("Make SnackBar")
        }

        Button(
            onClick = {
                changeState()
            },
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            ),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Change State")
        }
    }
}

@Composable
fun CoroutineTempScreen() {
    Text("Second Screen")
}