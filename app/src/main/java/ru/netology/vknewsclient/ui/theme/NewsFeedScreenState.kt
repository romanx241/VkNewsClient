package ru.netology.vknewsclient.ui.theme

import ru.netology.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts : List<FeedPost>) : NewsFeedScreenState()

}
