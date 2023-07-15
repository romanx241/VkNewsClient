package ru.netology.vknewsclient.domain

import ru.netology.vknewsclient.R

data class PostComment(

    val id: Long,
    val authorName: String,
    val authorAvatarUrl: String,
    val commentText: String,
    val publicationDate: String

)
