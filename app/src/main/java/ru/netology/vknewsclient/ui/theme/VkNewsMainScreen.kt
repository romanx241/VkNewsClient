package ru.netology.vknewsclient.ui.theme

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import ru.netology.vknewsclient.MainViewModel
import ru.netology.vknewsclient.navigation.AppNavGraph

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable

fun MainScreen(viewModel: MainViewModel) {

//    val selectedNavItem by viewModel.selectedNavItem.observeAsState(NavigationItem.Home)

    val navHostController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val fabIsVisible = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            val action = snackbarHostState.showSnackbar(
                                message = "This is snackbar",
                                actionLabel = "Hide FAB",
                                duration = SnackbarDuration.Long
                            )
                            if (action == SnackbarResult.ActionPerformed) {
                                fabIsVisible.value = false
                            }
                        }
                    }
                ) {
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
        bottomBar = {
            BottomNavigation {
//                val selectedItemPosition = remember { mutableStateOf(0) }
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile
                )
                items.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.screen.route,
                        onClick = { navHostController.navigate(item.screen.route) },
                        icon = {
                            Icon(item.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = stringResource(id = item.titleResId))
                        },
                        selectedContentColor = MaterialTheme.colors.onPrimary,
                        unselectedContentColor = MaterialTheme.colors.onSecondary
                    )
                }
            }

        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navHostController,
            homeScreenContent = {
                HomeScreen(
                    viewModel = viewModel,
                    paddingValues = paddingValues
                )
            },
            favouriteScreenContent = { TextCounter(name = "Favourite") },
            profileScreenContent = { TextCounter(name = "Profile") }
        )
    }
}

//    when(selectedNavItem){
//        NavigationItem.Home -> {
//            HomeScreen(viewModel = viewModel, paddingValues = paddingValues)
//        }
//        NavigationItem.Favourite -> TextCounter(name = "Favourite")
//        NavigationItem.Profile -> TextCounter(name = "Profile")
//        }


@Composable

private fun TextCounter(name: String) {
    var count by remember {
        mutableStateOf(0)
    }
    Text(
        modifier = Modifier.clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black

    )

}

