package ru.netology.vknewsclient.domain

import ru.netology.vknewsclient.R

data class PostComment(

    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long comment text",
    val publicationDate: String = "14:00"

)
