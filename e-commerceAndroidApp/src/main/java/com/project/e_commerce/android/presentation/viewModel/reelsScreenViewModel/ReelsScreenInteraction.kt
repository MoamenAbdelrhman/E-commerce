package com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.navigation.NavController

interface ReelsScreenInteraction {

    fun onClickSearchButton(navController: NavController)

    fun onClackLoveReelsButton(reelId: String)

    fun onClickCartButton()

    fun onClickMoreButton(shareLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>)

    fun onPauseVideo()

    fun onResumeVideo()

    fun onTwoClickToVideo()

    fun onLoveComment(reelsId : String ,commentId : String)
    fun onClickAddComment(videoId : String, comment: String)

    fun onClickShownAllComment()

    fun onClickShownAllRates()
}