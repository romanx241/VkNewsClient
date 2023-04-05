package ru.netology.vknewsclient.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.netology.vknewsclient.MainViewModel
import ru.netology.vknewsclient.domain.PostComment

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable

fun HomeScreen(
    viewModel : MainViewModel,
    paddingValues: PaddingValues,
){
    val feedPost = viewModel.feedPosts.observeAsState(listOf())

    if(feedPost.value.isNotEmpty()){
        val comments = mutableListOf<PostComment>().apply {
            repeat(20){
                add(
                    PostComment(id=it)
                )
            }
        }
        CommentsScreen(feedPost = feedPost.value.get(0), comments = comments)

    }

//    LazyColumn (
//        modifier = Modifier.padding(paddingValues),
//        contentPadding = PaddingValues(
//            top = 16.dp,
//            start = 8.dp,
//            end = 8.dp,
//            bottom = 72.dp
//        ),
//        verticalArrangement = Arrangement.spacedBy(8.dp)
//    ){
//        items(items = feedPost.value, key = { it.id }) { feedPost ->
//            val dismissState = rememberDismissState()
//            if(dismissState.isDismissed(DismissDirection.EndToStart)){
//                viewModel.remove(feedPost)
//            }
//            SwipeToDismiss(
//                modifier = Modifier.animateItemPlacement(),
//                state = dismissState,
//                background = {},
//                directions = setOf(DismissDirection.EndToStart)
//            ) {
//                PostCard(
//                    feedPost = feedPost,
//                    onViewsClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onLikeClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onShareClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                    onCommentClickListener = { statisticItem ->
//                        viewModel.updateCount(feedPost, statisticItem)
//                    },
//                )
//            }
//        }
//    }
}