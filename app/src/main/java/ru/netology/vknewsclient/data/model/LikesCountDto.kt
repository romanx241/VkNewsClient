package ru.netology.vknewsclient.data.model

import com.google.gson.annotations.SerializedName

data class LikesCountDto (

    @SerializedName("likes") val count : Int
        )