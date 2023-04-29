package ru.netology.vknewsclient.presentation.news

import ru.netology.vknewsclient.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts : List<FeedPost>) : NewsFeedScreenState()

}
