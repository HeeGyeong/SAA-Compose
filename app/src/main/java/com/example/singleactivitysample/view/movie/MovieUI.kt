package com.example.singleactivitysample.view.movie

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.singleactivitysample.model.MovieEntity
import com.example.singleactivitysample.view.base.BottomBar
import com.example.singleactivitysample.view.base.DrawerItem
import com.example.singleactivitysample.view.base.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalAnimationApi
@Composable
fun MovieUI(composeNavigator: NavController) {
    // 인자가 필요하기 때문에 compose ViewModel은 사용 불가능.
//    val viewModel = viewModel<MovieViewModel>()
    // implementation "io.insert-koin:koin-androidx-compose:3.2.0" 사용 시 2가지 형태로 VM 호출 가능.
//    val viewModel = getViewModel<MovieViewModel>()
//    val viewModel = koinViewModel<MovieViewModel>()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar("Movie UI", scaffoldState, scope, composeNavigator)
        },
        bottomBar = {
            BottomBar()
        },
        content = {
            MovieScreen(scope)
        },
        drawerContent = {
            DrawerItem(scaffoldState, scope, composeNavigator)
        },
        drawerGesturesEnabled = false
    )
}

@Composable
fun MovieScreen(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    viewModel: MovieViewModel = getViewModel(), // getViewModel()을 통해 주입 가능
//    viewModel: MovieViewModel = koinViewModel(), // koinViewModel()을 통해 주입 가능
) {

    var apiText by remember { mutableStateOf("null") }
    var flowApiTest by remember { mutableStateOf("null") }

    val data by viewModel.data.observeAsState()
    val flowData by viewModel.flowData.observeAsState()
    val flowData2 by viewModel.flowData2.observeAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = apiText,
            onValueChange = { change ->
                apiText = change
                if (apiText.isNotEmpty()) {
                    viewModel.apiFunction(apiText)
                }
            },
            label = { Text("Search Movie") }
        )

        DataListSizeText(data)

        TextField(
            value = flowApiTest,
            onValueChange = { change ->
                flowApiTest = change

                coroutineScope.launch {
                    if (flowApiTest.isNotEmpty()) {
                        viewModel.apiFlowFunction(flowApiTest)
                    }
                }
            },
            label = { Text("Search Movie Flow") }
        )

        DataListSizeText(flowData)
        DataListSizeText(flowData2)
    }


}

@Composable
fun DataListSizeText(data: List<MovieEntity>?) {
    val rememberUpdatedData by rememberUpdatedState(data)

    Text("insertData : ${rememberUpdatedData?.size ?: "no data"}")
}