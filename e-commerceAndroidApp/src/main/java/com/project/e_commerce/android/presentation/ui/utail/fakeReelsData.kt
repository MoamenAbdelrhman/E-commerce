package com.project.e_commerce.android.presentation.ui.utail

import android.net.Uri


object DummyData {
    val reels = listOf<Reel>(
        Reel(
            id = 1,
            video = "https://dl.dropboxusercontent.com/scl/fi/r5iwu55alyisg4jqd1vgu/997140902_tk.mp4?rlkey=g687oi0n7sd4ragrvn5oe29kq&amp;st=v17hcbvn&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/4.png",
            userName = "حوراء تحسين",
            isLiked = true,
            likesCount = 778,
            commentsCount = 156,
            comment = "منشور تجريبي .... ",

        ),
        Reel(
            id = 2,
            video = "https://dl.dropboxusercontent.com/scl/fi/a5aktfmd58byrslv5tn20/997140902_tk1.mp4?rlkey=sfvzq65in938s839tzkzv6cr1&amp;st=q5vrk1bu&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/7.png",
            userName = "استاذ شريف",
            isLiked = true,
            likesCount = 5923,
            commentsCount = 11,
            comment = "منشور تجريبي"
        ),
        Reel(
            id = 3,
            video = "https://dl.dropboxusercontent.com/scl/fi/s9irdxc1xfrpxqbywkj0b/997140902_tk3.mp4?rlkey=kewnn9di0f5aerjtzlo69yalo&amp;st=u4297tmu&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/3.png",
            userName = "حسناء محمود",
            isLiked = true,
            likesCount = 2314,
            comment = "منشور تجريبي",
            commentsCount = 200
        ),
        Reel(
            id = 4,
            video = "https://dl.dropboxusercontent.com/scl/fi/sgo0xay05z9f0utlzkyy4/997140902_tk4.mp4?rlkey=811o8eq0hkls2n1t5yzy9e7ky&amp;st=s7swfzth&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/6.png",
            userName = "احمد خالد",
            isLiked = true,
            likesCount = 786,
            comment = "منشور تجريبي",
            commentsCount = 700
        ),
        Reel(
            id = 5,
            video = "https://dl.dropboxusercontent.com/scl/fi/r5iwu55alyisg4jqd1vgu/997140902_tk.mp4?rlkey=g687oi0n7sd4ragrvn5oe29kq&amp;st=v17hcbvn&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "احمد محمد",
            isLiked = true,
            likesCount = 1890,
            comment = "منشور تجريبي",
            commentsCount = 232
        ),
        Reel(
            id = 6,
            video = "https://dl.dropboxusercontent.com/scl/fi/r5iwu55alyisg4jqd1vgu/997140902_tk.mp4?rlkey=g687oi0n7sd4ragrvn5oe29kq&amp;st=v17hcbvn&amp;dl=0",
            userImage = "https://generated.photos/vue-static/home/hero/2.png",
            userName = "احمد محمد",
            isLiked = true,
            likesCount = 1890,
            comment = "منشور تجريبي",
            commentsCount = 232
        ),

    )
}

data class Reel(
    val id: Int,
    val video: String,
    val userImage: String,
    val userName: String,
    val isLiked: Boolean = false,
    val likesCount: Int,
    val comment: String,
    val commentsCount: Int
) {

    fun getVideoUri(videoUri: String): Uri {
        // Example video URL
        val videoUrl =
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        return Uri.parse(videoUri)
    }

}
fun String.getVideoUri(videoUri: String): Uri? {
    return Uri.parse(videoUri)
}