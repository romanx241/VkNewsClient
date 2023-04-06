package ru.netology.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.vknewsclient.domain.FeedPost
import ru.netology.vknewsclient.domain.PostComment
import ru.netology.vknewsclient.ui.theme.CommentsScreenState

class CommentsViewModel : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(FeedPost())
    }


    fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(
            feedPost = feedPost,
            comments = comments
        )
    }
}