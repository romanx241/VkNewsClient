package ru.netology.vknewsclient.presentation.news

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.netology.vknewsclient.data.repository.NewsFeedRepository
import ru.netology.vknewsclient.domain.FeedPost
import ru.netology.vknewsclient.domain.StatisticItem

class NewsFeedViewModel(application: Application): AndroidViewModel(application) {

    private val initialState = NewsFeedScreenState.Initial
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState
    private val repository = NewsFeedRepository(application)

    init {
        _screenState.value = NewsFeedScreenState.Loading
        loadRecommendations()
    }

    private fun loadRecommendations(){
        viewModelScope.launch {
            delay(5000)
            val feedPosts = repository.loadRecommendations()
            _screenState.value = NewsFeedScreenState.Posts(posts = feedPosts)
        }
    }

    fun loadNextRecommendations(){
        _screenState.value = NewsFeedScreenState.Posts(
            posts = repository.feedPosts,
            nextDataIsLoading = true
        )
        loadRecommendations()
    }

    fun changeLikeStatus(feedPost: FeedPost){
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)

        }
    }



//    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
//
//        val currentState = screenState.value
//        if(currentState !is NewsFeedScreenState.Posts) return
//
//        val oldPosts = currentState.posts.toMutableList()
//        val oldStatistic = feedPost.statistics
//        val newStatistic = oldStatistic.toMutableList().apply {
//            replaceAll { oldItem ->
//                if (oldItem.type == item.type) {
//                    oldItem.copy(count = oldItem.count + 1)
//                } else {
//                    oldItem
//                }
//            }
//        }
//        val newFeedPost = feedPost.copy(statistics = newStatistic)
//        val newPosts = oldPosts.apply {
//            replaceAll{
//                if(it.id == newFeedPost.id){
//                    newFeedPost
//                } else {
//                    it
//                }
//            }
//        }
//        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
//    }

    fun remove(feedPost: FeedPost){
       viewModelScope.launch {
           repository.deletePost(feedPost)
           _screenState.value = NewsFeedScreenState.Posts(posts = repository.feedPosts)

       }
    }
}