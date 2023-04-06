package ru.netology.vknewsclient.ui.theme

import ru.netology.vknewsclient.domain.FeedPost
import ru.netology.vknewsclient.domain.PostComment


sealed class CommentsScreenState {

        object Initial : CommentsScreenState()

        data class Comments(
            val feedPost: FeedPost,
            val comments: List<PostComment>
        ) : CommentsScreenState()
    }

