package ru.netology.vknewsclient.presentation.news

import android.app.Application
import androidx.lifecycle.*
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.launch
import ru.netology.vknewsclient.data.mapper.NewsFeedMapper
import ru.netology.vknewsclient.data.network.ApiFactory
import ru.netology.vknewsclient.data.network.ApiService
import ru.netology.vknewsclient.domain.FeedPost
import ru.netology.vknewsclient.domain.StatisticItem

class NewsFeedViewModel(application: Application): AndroidViewModel(application) {

    private val initialState = NewsFeedScreenState.Initial
    private val _screenState = MutableLiveData<NewsFeedScreenState>(initialState)
    val screenState: LiveData<NewsFeedScreenState> = _screenState

    private val mapper = NewsFeedMapper()

    init {
        loadRecommendation()
    }

    private fun loadRecommendation(){
        viewModelScope.launch {
            val storage = VKPreferencesKeyValueStorage(getApplication())
            val token = VKAccessToken.restore(storage) ?: return@launch
            val response = ApiFactory.apiService.loadRecommendations(token.accessToken)
            val feedPosts = mapper.mapResponseToPosts(response)
            _screenState.value = NewsFeedScreenState.Posts(posts = feedPosts)
        }



    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {

        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return

        val oldPosts = currentState.posts.toMutableList()
        val oldStatistic = feedPost.statistics
        val newStatistic = oldStatistic.toMutableList().apply {
            replaceAll { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistic)
        val newPosts = oldPosts.apply {
            replaceAll{
                if(it.id == newFeedPost.id){
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = NewsFeedScreenState.Posts(posts = newPosts)
    }

    fun remove(feedPost: FeedPost){
        val currentState = screenState.value
        if(currentState !is NewsFeedScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = NewsFeedScreenState.Posts(posts = oldPosts)
    }
}