package com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.Comment
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.LoveItem
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.NewComment
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.Ratings
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.Reels
import com.project.e_commerce.android.presentation.viewModel.baseViewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Math.random

class ReelsScreenViewModel : BaseViewModel(), ReelsScreenInteraction {

    private val _state: MutableStateFlow<List<Reels>> = MutableStateFlow(listOf())
    val state: StateFlow<List<Reels>> get() = _state

    private val _showAddToCart = MutableStateFlow(false)
    val showAddToCart: StateFlow<Boolean> = _showAddToCart.asStateFlow()

    init {
        viewModelScope.launch {
            _state.emit(
                listOf(
                    Reels(
                        id = random().toString(),
                        userName = "Hassan Ali",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/r5iwu55alyisg4jqd1vgu/997140902_tk.mp4?rlkey=g687oi0n7sd4ragrvn5oe29kq&amp;st=v17hcbvn&amp;dl=0"),
                        images = listOf(
                            Uri.parse("android.resource://com.project.e_commerce.android/" + R.drawable.perfume3),
                            Uri.parse("android.resource://com.project.e_commerce.android/" + R.drawable.perfume1),
                            Uri.parse("android.resource://com.project.e_commerce.android/" + R.drawable.perfume2),
                        ),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = listOf(
                            Ratings(userName = "Alice", review = "Great product!", rate = 5),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4),
                            Ratings(userName = "Charlie", review = "Could be better", rate = 3)
                        ),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = "Hawraa Tahseen",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/a5aktfmd58byrslv5tn20/997140902_tk1.mp4?rlkey=sfvzq65in938s839tzkzv6cr1&amp;st=q5vrk1bu&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = listOf(
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Charlie", review = "Could be better", rate = 3, time = "8 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                        ),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = "Hasnaa Mohamed",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/s9irdxc1xfrpxqbywkj0b/997140902_tk3.mp4?rlkey=kewnn9di0f5aerjtzlo69yalo&amp;st=u4297tmu&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = listOf(
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "1 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "12 h"),
                            Ratings(userName = "Charlie", review = "Could be better", rate = 3, time = "18 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "12 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "5 h"),
                        ),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = " Mohamed Ali",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/sgo0xay05z9f0utlzkyy4/997140902_tk4.mp4?rlkey=811o8eq0hkls2n1t5yzy9e7ky&amp;st=s7swfzth&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = listOf(
                            Ratings(userName = "Alice", review = "Great product!", rate = 5, time = "16 h"),
                            Ratings(userName = "Bob", review = "Satisfied", rate = 4, time = "20 h"),
                            Ratings(userName = "Charlie", review = "Could be better", rate = 3, time = "2 h")
                        ),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = " Mohamed Ali",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/673a1sp4ui542xh4h6qd2/997140902_tk6.mp4?rlkey=n9ot9cifjtk2xqwd3mwdiwnhr&amp;st=8rdn79rh&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = emptyList(),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = " Mohamed Ali",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/673a1sp4ui542xh4h6qd2/997140902_tk6.mp4?rlkey=n9ot9cifjtk2xqwd3mwdiwnhr&amp;st=8rdn79rh&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = emptyList(),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),
                    Reels(
                        id = random().toString(),
                        userName = " علي",
                        userImage = R.drawable.profile,
                        video = Uri.parse("https://dl.dropboxusercontent.com/scl/fi/1z32xan3bpklc8w3kstpt/997140902_tk9.mp4?rlkey=ft2m7qgzaqmdnaz8c0h1f2xjj&amp;st=zevpub9j&amp;dl=0"),
                        contentDescription = "Sample Post",
                        love = LoveItem(10, false),
                        ratings = emptyList(),
                        comments = listOf(
                            Comment(
                                id = "12342423df",
                                userName = "Mr. Sherif",
                                comment = "Is this product still available? ",
                                time = "20h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "What is the price? ",
                                        time = "1h ",
                                        isLoved = false,
                                    ),
                                    Comment(
                                        userName = "Hassan Ali",
                                        comment = "What colors are available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Ibtisam Ahmed",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ), Comment(
                                id = "12342423dewewf",
                                userName = "Hassan Ali",
                                comment = "What colors are available? ؟ ",
                                time = "14h ",
                                isLoved = false,
                                isReplyShown = true,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,

                                        ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdwds",
                                userName = "Omar Farouk",
                                comment = " What is the size of the product?",
                                time = "1h ",
                                isLoved = false,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            ),
                            Comment(
                                id = "12342423dfdsfds",
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                                isReplyShown = false,
                                reply = listOf(
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                    Comment(
                                        userName = "Mohamed Arslan",
                                        comment = "Is this product still available? ",
                                        time = "14h ",
                                        isLoved = true,
                                    ),
                                )
                            )
                        ) as MutableList<Comment>,
                        isLoading = true,
                        isError = true,
                        numberOfCart = 20,
                        numberOfComments = 30
                    ),

                    )
            )
        }
    }

    override fun setLoadingState(loadingState: Boolean) {

    }

    override fun setErrorState(errorState: Boolean, errorMessage: String) {

    }


    fun forceLoveReels(reelsId: String) {
        viewModelScope.launch {
            _state.value = _state.value.map { reel ->
                if (reel.id == reelsId) {
                    reel.copy(love = reel.love.copy(isLoved = true))
                } else reel
            }
        }
    }

    fun onWriteNewComment(comment: String) {
        val copyState = _state.value.mapIndexed { index, value ->
            if (index == 0) {
                value.copy(
                    newComment = NewComment(comment)

                )
            } else value
        }
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onClickSearchButton(navController: NavController) {
        navController.navigate(Screens.ReelsScreen.SearchReelsAndUsersScreen.route)
    }

    override fun onClackLoveReelsButton(reelId: String) {

        val copyState = _state.value.mapIndexed { index, it ->
            if (it.id == reelId) {
                val data = it.copy(
                    love = _state.value[index].love.copy(
                        number = if (_state.value[index].love.isLoved) _state.value[index].love.number - 1
                        else _state.value[index].love.number + 1,
                        isLoved = !_state.value[index].love.isLoved
                    )
                )
                Log.i("LOVED", it.love.number.toString() + "  : " + it.love.isLoved.toString())
                data
            } else it
        }
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onClickCartButton() {
        _showAddToCart.value = true
    }


    override fun onClickMoreButton(shareLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val sendIntent: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "www.google.com")
            type = "text/plain"
        }
        shareLauncher.launch(Intent.createChooser(sendIntent, null))
    }

    override fun onPauseVideo() {
        TODO("Not yet implemented")
    }

    override fun onResumeVideo() {
        TODO("Not yet implemented")
    }

    override fun onTwoClickToVideo() {
        TODO("Not yet implemented")
    }

    override fun onLoveComment(reelsId: String, commentId: String) {

    }
//        val copyState = _state.value.mapIndexed { _, reel ->
//            if (reel.id == reelsId) {
//                val data = reel.comments.map { comment ->
//                    if (comment.id == commentId) {
//                        val newData = reel.copy(
//                            isLoved = !comment.isLoved,
//                        )
//                        newData
//                        reel
//                    } else reel
//                }
//            }
//            else reel
//        }
//

    override fun onClickAddComment(videoId: String, comment: String) {
        val newValue =  Comment(
            id = random().toString(),
            userName = "User",
            comment = comment,
            time = "now",
        )
        val updatedRequests = _state.value.map {
            if(it.id == videoId) {
                it.comments.toMutableList().apply { add(newValue) }
                it
            }
            else it
        }
        val copyState = _state.value.map {
            if (it.id == videoId)
                it.copy(
                    comments = updatedRequests.filter { it.id == videoId }[0].comments
                )
            else it
        }
        viewModelScope.launch {
            _state.emit(copyState)
        }

    }

    override fun onClickShownAllComment() {
        val copyState = _state.value.map {
            it.copy(
                comments = listOf(
                    Comment(
                        id = "12342423df",
                        userName = "Mr. Sherif",
                        comment = "Is this product still available? ",
                        time = "20h ",
                        isLoved = true,
                        isReplyShown = false,
                        reply = listOf(
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "What is the price? ",
                                time = "1h ",
                                isLoved = false,
                            ),
                            Comment(
                                userName = "Hassan Ali",
                                comment = "What colors are available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Ibtisam Ahmed",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                        )
                    ), Comment(
                        id = "12342423dewewf",
                        userName = "Hassan Ali",
                        comment = "What colors are available? ؟ ",
                        time = "14h ",
                        isLoved = false,
                        isReplyShown = true,
                        reply = listOf(
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,

                                ),
                        )
                    ),
                    Comment(
                        id = "12342423dfdwds",
                        userName = "Omar Farouk",
                        comment = " What is the size of the product?",
                        time = "1h ",
                        isLoved = false,
                        isReplyShown = false,
                        reply = listOf(
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                        )
                    ),
                    Comment(
                        id = "12342423dfdsfds",
                        userName = "Mohamed Arslan",
                        comment = "Is this product still available? ",
                        time = "14h ",
                        isLoved = true,
                        isReplyShown = false,
                        reply = listOf(
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                            Comment(
                                userName = "Mohamed Arslan",
                                comment = "Is this product still available? ",
                                time = "14h ",
                                isLoved = true,
                            ),
                        )
                    )
                ) as MutableList<Comment>
            )

        }
        viewModelScope.launch {
            _state.emit(copyState)
        }
    }

    override fun onClickShownAllRates() {
        TODO("Not yet implemented")
    }

}