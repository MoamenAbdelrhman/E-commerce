package com.project.e_commerce.android.presentation.ui.screens.reelsScreen

import android.net.Uri
import com.project.e_commerce.R

data class Reels(
    val id: String = "",
    val userName: String = "",
    val userImage: Int = 0,
    val video: Uri? = null,
    val images: List<Uri>? = null,
    val fallbackImageRes: Int = com.project.e_commerce.android.R.drawable.reelsphoto,
    val contentDescription: String = "",
    val love: LoveItem = LoveItem(),
    val ratings: List<Ratings> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val newComment: NewComment = NewComment(),
    val isDialogShown: Boolean = false,
    val isLoading: Boolean = true,
    val isError: Boolean = true,
    val numberOfCart: Int = 0,
    val numberOfComments: Int = 0

)

data class LoveItem(
    val number: Int = 0,
    val isLoved: Boolean = false,
)


data class NewComment(
    val comment: String = ""
)

data class Comment(
    val id: String = "",
    val userName: String = "",
    val comment: String = "",
    val time: String = "",
    val reply: List<Comment> = emptyList(),
    val isLoved: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isReplyShown: Boolean = false,
)

data class Ratings(
    val userName: String = "",
    val review: String = "",
    val rate: Int = 0,
    val time: String = ""
)
