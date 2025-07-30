package com.project.e_commerce.android.presentation.ui.screens

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.RequireLoginPrompt
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


data class UserFollowModel(
    val name: String,
    val username: String,
    val isFollowingMe: Boolean,
    val isIFollow: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FollowListScreen(
    navController: NavHostController,
    username: String,
    isCurrentUser: Boolean,
    startTabIndex: Int = 0,
    showFriendsTab: Boolean = true
) {
    val allTabs = listOf("Followers", "Following", "Friends", "Suggested")
    val tabs = if (showFriendsTab) allTabs else listOf("Followers", "Following", "Suggested")

    // قوائم وهمية مختلفة لكل تبويب
    // تعديل القوائم الوهمية
    val followersList = List(10) { index ->
        UserFollowModel(
            name = "Follower $index",
            username = "@follower$index",
            isFollowingMe = if (showFriendsTab) true else index % 3 != 0, // عند showFriendsTab=false، تجعل بعض المستخدمين لا يتابعونك
            isIFollow = index % 2 == 0 // بعض المستخدمين تتابعهم، والبعض لا
        )
    }

    val followingList = List(10) { index ->
        UserFollowModel(
            name = "Following $index",
            username = "@following$index",
            isFollowingMe = index % 2 == 0, // بعض المستخدمين يتابعونك
            isIFollow = if (showFriendsTab) true else index % 3 != 0 // عند showFriendsTab=false، تجعل بعض المستخدمين لا تتابعهم
        )
    }

    val friendsList = List(10) {
        UserFollowModel(
            name = "Friend $it",
            username = "@friend$it",
            isFollowingMe = true,
            isIFollow = true
        )
    }

    val suggestedList = List(10) { index ->
        UserFollowModel(
            name = "Suggested $index",
            username = "@suggested$index",
            isFollowingMe = index % 2 == 0,
            isIFollow = false
        )
    }


    val pagerState = rememberPagerState(initialPage = startTabIndex)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0066CC)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color(0xFF0066CC)
                    )
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White
            )
        )

        ScrollableTabRow(
            backgroundColor = Color.White,
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = pagerState.currentPage,
            edgePadding = if (showFriendsTab) 0.dp else 22.dp,
            contentColor = Color(0xFF0066CC),
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                        .height(3.dp),
                    color = Color(0xFF0066CC)
                )
            },
        ) {
            tabs.forEachIndexed { index, title ->
                val labelWithCount = when (title) {
                    "Followers" -> "Followers (${followersList.size})"
                    "Following" -> "Following (${followingList.size})"
                    "Friends" -> "Friends"
                    else -> title
                }

                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = labelWithCount,
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }
        }


        HorizontalPager(count = tabs.size, state = pagerState) { page ->

            val users = when (tabs[page]) {
                "Followers" -> followersList
                "Following" -> followingList
                "Friends" -> friendsList
                else -> suggestedList
            }

            LazyColumn(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
                items(users.size) { index ->
                    val user = users[index]

                    val initialState = when {
                        !showFriendsTab -> {
                            when (tabs[page]) {
                                "Followers", "Following" -> {
                                    when {
                                        user.isFollowingMe && user.isIFollow -> "Friends"
                                        user.isFollowingMe -> "Follow back"
                                        user.isIFollow -> "Following"
                                        else -> "Follow"
                                    }
                                }
                                "Suggested" -> {
                                    if (user.isFollowingMe) "Follow back" else "Follow"
                                }
                                else -> "Follow" // لن يحدث هذا لأن التبويبات محدودة
                            }
                        }
                        else -> {
                            // السلوك الافتراضي عندما تكون showFriendsTab = true
                            when (tabs[page]) {
                                "Following" -> if (user.isFollowingMe) "Friends" else "Following"
                                "Followers" -> if (user.isIFollow) "Friends" else "Follow back"
                                "Friends" -> "Friends"
                                "Suggested" -> if (user.isFollowingMe) "Follow back" else "Follow"
                                else -> "Follow"
                            }
                        }
                    }

                    var buttonState by remember(tabs[page], user, showFriendsTab) {
                        mutableStateOf(initialState)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(user.name, fontWeight = FontWeight.Bold, color = Color(0xFF0D3D67))
                            Text(user.username, fontSize = 12.sp, color = Color.Gray)
                        }


                            Button(
                                modifier = Modifier.width(110.dp),
                                onClick = {
                                    buttonState = when (buttonState) {
                                        "Follow" -> "Following"
                                        "Follow back" -> "Friends"
                                        "Following" -> "Follow"
                                        "Friends" -> "Follow back"
                                        else -> "Follow"
                                    }

                                },
                                shape = RoundedCornerShape(24.dp),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = when (buttonState) {
                                        "Follow" -> Color(0xFFFF6600)
                                        "Follow back" -> Color(0xFFFF6600)
                                        "Friends" -> Color(0xFFF2F2F2)
                                        else -> Color(0xFFF2F2F2)
                                    },
                                    contentColor = if (buttonState == "Friends") Color.Black else Color.White
                                )
                            ) {
                                Text(
                                    buttonState,
                                    color = if (buttonState == "Friends" || buttonState == "Following") Color.Black else Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 412, heightDp = 892)
@Composable
fun FollowListScreenPreview() {
    val fakeNavController = rememberNavController()
    FollowListScreen(
        navController = fakeNavController,
        isCurrentUser = true,
        username = "Username"
    )
}