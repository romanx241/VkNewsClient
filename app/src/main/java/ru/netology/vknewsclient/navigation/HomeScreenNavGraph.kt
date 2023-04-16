package ru.netology.vknewsclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.netology.vknewsclient.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph (
    newsFeedScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit

) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route
    ) {
        composable(Screen.NewsFeed.route) {
            newsFeedScreenContent()
        }
        composable(
            route = Screen.Comments.route,
            arguments = listOf(
                navArgument(Screen.KEY_FEED_POST_ID){
                    type = NavType.IntType
                },
                navArgument(Screen.KEY_CONTENT_TEXT){
                    type = NavType.StringType
                }
            )

            ) {
           val feesPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
            val contentText = it.arguments?.getString(Screen.KEY_CONTENT_TEXT) ?: ""
            commentsScreenContent(FeedPost(
                id = feesPostId,
                contentText = contentText
            )
            )
        }
    }
}