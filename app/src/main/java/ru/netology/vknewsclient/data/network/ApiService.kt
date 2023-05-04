package ru.netology.vknewsclient.data.network

import retrofit2.http.GET
import retrofit2.http.Query
import ru.netology.vknewsclient.data.model.NewsFeedResponseDto

interface ApiService {

    @GET("newsfeed.getRecommended?.v=5.131")
    suspend fun loadRecommendations(
        @Query("access_token") token : String
    ) : NewsFeedResponseDto
}