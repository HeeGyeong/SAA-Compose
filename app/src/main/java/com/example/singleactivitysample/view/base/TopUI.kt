package com.example.singleactivitysample.view.base

import android.app.Activity
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.singleactivitysample.navi.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalAnimationApi
@Composable
fun TopBar(
    title: String,
    scaffoldState: ScaffoldState,
    scope: CoroutineScope = rememberCoroutineScope(), // 인자가 안넘어오면 생성. 안정성을 위해 추가.
    composeNavigator: NavController,
) {
    val context = LocalContext.current as Activity

    val expanded = remember { mutableStateOf(false) }
    val liked = remember { mutableStateOf(true) }

    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        scaffoldState.drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                }
            ) {
                Icon(Icons.Filled.Menu, contentDescription = "")
            }
        },
        actions = {
            IconButton(onClick = {
                composeNavigator.navigate(Screen.Main.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            }) {
                Icon(Icons.Filled.Home, contentDescription = "")
            }

            IconToggleButton(
                checked = liked.value,
                onCheckedChange = {
                    liked.value = it

                    scope.launch {
                        Toast.makeText(context, "click Toggle", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                val tint by animateColorAsState(
                    if (liked.value) Color.Red
                    else Color.LightGray
                )
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite description",
                    tint = tint
                )
            }

            Box(
                Modifier
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = {
                    expanded.value = true
                }) {
                    Icon(
                        Icons.Filled.MoreVert,
                        contentDescription = "TopEnd description"
                    )
                }

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                ) {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                        composeNavigator.navigate(Screen.Sub.route) {
                            launchSingleTop = true
                            popUpTo("main")
                        }
                    }) {
                        Text("Sub")
                    }

                    DropdownMenuItem(onClick = {
                        expanded.value = false

                        composeNavigator.navigate(Screen.Progress.route) {
                            launchSingleTop = true
                            popUpTo("main")
                        }
                    }) {
                        Text("Progress")
                    }

                    Divider()

                    DropdownMenuItem(onClick = {
                        expanded.value = false

                        composeNavigator.navigate(Screen.Cal.route) {
                            launchSingleTop = true
                            popUpTo("main")
                        }
                    }) {
                        Text("Cal")
                    }

                    Divider(thickness = 4.dp)

                    DropdownMenuItem(onClick = {
                        expanded.value = false

                        composeNavigator.navigate(Screen.Scope.route) {
                            launchSingleTop = true
                            popUpTo("main")
                        }
                    }) {
                        Text("Scope")
                    }

                    Divider(thickness = 8.dp)

                    DropdownMenuItem(onClick = {
                        expanded.value = false

                        composeNavigator.navigate(Screen.Movie.route) {
                            launchSingleTop = true
                            popUpTo("main")
                        }
                    }) {
                        Text("Movie")
                    }
                }
            }
        },
        backgroundColor = Color(0xFDCD7F32),
        elevation = AppBarDefaults.TopAppBarElevation
    )
}

@ExperimentalAnimationApi
@Composable
fun DrawerItem(
    scaffoldState: ScaffoldState,
    scope: CoroutineScope,
    composeNavigator: NavController,
) {

    Row {
        IconButton(
            onClick = {
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            },
        ) {
            Row {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
        Text("Navigation Drawer", modifier = Modifier.padding(top = 13.5.dp))
    }
    Divider()

    Row(modifier = Modifier
        .clickable {
            scope.launch {
                scaffoldState.drawerState.close()
                composeNavigator.navigate(Screen.Scope.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            }
        }
        .fillMaxWidth()
        .padding(8.dp)
        .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Filled.Refresh, contentDescription = "")
        Text(
            text = "Go to Scope",
            fontWeight = FontWeight.Bold
        )
    }

    Row(modifier = Modifier
        .clickable {
            scope.launch {
                scaffoldState.drawerState.close()
                composeNavigator.navigate(Screen.Progress.route) {
                    launchSingleTop = true
                    popUpTo("main")
                }
            }
        }
        .fillMaxWidth()
        .padding(8.dp)
        .padding(start = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(Icons.Filled.Call, contentDescription = "")
        Text(
            text = "Go to progress",
            fontWeight = FontWeight.Bold
        )
    }
}