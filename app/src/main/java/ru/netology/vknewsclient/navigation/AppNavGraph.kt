package ru.netology.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.netology.vknewsclient.domain.FeedPost

@Composable

fun AppNavGraph(
    navHostController: NavHostController,
    favouriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
    newsFeedScreenContent: @Composable () -> Unit,

    ) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ) {
        homeScreenNavGraph(
            newsFeedScreenContent = newsFeedScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        composable(Screen.Favourite.route) {
            favouriteScreenContent()
        }

        composable(Screen.Profile.route) {
            profileScreenContent()
        }
    }
}