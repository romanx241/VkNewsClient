package ru.netology.vknewsclient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.vknewsclient.domain.FeedPost
import ru.netology.vknewsclient.domain.StatisticItem
import ru.netology.vknewsclient.ui.theme.HomeScreenState
import java.util.Collections.replaceAll

class MainViewModel: ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10){
            add(FeedPost(id=it))
        }
    }

    private val initialState = HomeScreenState.Posts(posts = sourceList)

    private val _screenState = MutableLiveData<HomeScreenState>(initialState)

    val screenState: LiveData<HomeScreenState> = _screenState

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {

        val oldPosts = screenState.value?.toMutableList() ?: mutableListOf()
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
        _screenState.value = oldPosts.apply {
            replaceAll{
                if(it.id == newFeedPost.id){
                    newFeedPost
                } else {
                    it
                }
            }
        }
    }

    fun remove(feedPost: FeedPost){
        val oldPosts = screenState.value?.toMutableList() ?: mutableListOf()
        oldPosts.remove(feedPost)
        _screenState.value = oldPosts
    }
}