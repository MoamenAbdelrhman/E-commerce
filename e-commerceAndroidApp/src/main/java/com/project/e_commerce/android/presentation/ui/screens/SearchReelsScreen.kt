package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.platform.LocalFocusManager
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import kotlinx.coroutines.launch
import com.google.accompanist.pager.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.ui.focus.focusRequester

// ------------- بيانات وهمية ----------
data class Reel(
    val id: Int,
    val thumbnail: Int,
    val username: String,
    val profileImage: Int,
    val views: Int
)
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val profileImage: Int,
    val isVerified: Boolean = false
)

val sampleReels = List(15) {
    Reel(
        id = it,
        thumbnail = listOf(
            R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4
        )[it % 4],
        username = "user$it",
        profileImage = listOf(
            R.drawable.profile, R.drawable.img2, R.drawable.img3, R.drawable.img4
        )[it % 4],
        views = (1500..9800).random()
    )
}

val sampleUsers = List(12) {
    User(
        id = it,
        name = "Jenny $it",
        username = "jenny_wilson$it",
        profileImage = listOf(
            R.drawable.profile, R.drawable.img2, R.drawable.img3, R.drawable.img4
        )[it % 4],
        isVerified = it % 3 == 0
    )
}

// --------- شاشة البحث -------------

@Composable
fun SearchReelsAndUsersScreen(navController: NavHostController) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Reels", "Users")
    val focusManager = LocalFocusManager.current
    val textFieldFocusRequester = remember { androidx.compose.ui.focus.FocusRequester() }

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header Search Bar
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() },
                modifier = Modifier.offset(x = (-12).dp)) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color(0xFF1B7ACE))
            }

            TextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text("Search reels or users...", color = Color.Gray) },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFFF6F00),
                        modifier = Modifier.clickable {
                            focusManager.clearFocus()
                        }
                    )
                },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .offset(x = (-6).dp)
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF0F0F0))
                    .focusRequester(textFieldFocusRequester)
                ,
                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF6F00),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    cursorColor = Color(0xFFFF6F00)
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                )
            )

            /*IconButton(onClick = { *//* TODO: Filter Action *//* }) {
                Icon(Icons.Default.FilterList, contentDescription = null, tint = Color(0xFFFF6F00))
            }*/
        }

        // Tabs
        // Tabs + Pager Indicators
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.White,
            contentColor = Color(0xFFFF6F00)
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = {
                        Text(
                            tab,
                            fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal,
                            color = if (pagerState.currentPage == index) Color(0xFFFF6F00) else Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }

        // Pager Content
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> ReelsGridContent(query, sampleReels)
                1 -> UsersListContent(query, sampleUsers)
            }
        }
    }
}

@Composable
fun ReelsGridContent(query: String, reels: List<Reel>) {
    val filteredReels = reels.filter {
        it.username.contains(query, ignoreCase = true)
    }
    if (filteredReels.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No Reels found", color = Color.Gray)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredReels) { reel ->
                Box(
                    Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFF0F0F0))
                        .aspectRatio(10f / 15f)
                        .clickable { /* Open reel */ }
                ) {
                    Image(
                        painter = painterResource(id = reel.thumbnail),
                        contentDescription = "Reel thumbnail",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = reel.profileImage),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .size(26.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.White, CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "${reel.views} views",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .background(Color(0x80000000), RoundedCornerShape(10.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun UsersListContent(query: String, users: List<User>) {
    val filteredUsers = users.filter {
        it.name.contains(query, ignoreCase = true) || it.username.contains(query, ignoreCase = true)
    }
    var followedStates by remember { mutableStateOf(List(users.size) { false }) }

    if (filteredUsers.isEmpty()) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("No users found", color = Color.Gray)
        }
    } else {
        LazyColumn(contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)) {
            items(filteredUsers) { user ->
                val realIndex = users.indexOfFirst { it.id == user.id }
                val isFollowed = followedStates.getOrElse(realIndex) { false }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White) // أضف لون خلفية فاتح مناسب
                        .clickable(
                            indication = null, // هذا يلغي تأثير الـ ripple تمامًا
                            interactionSource = remember { MutableInteractionSource() }
                        ) { /* Open Profile */ },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFFFF6F00), CircleShape)
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Test", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        }
                        Text("@test", color = Color.Gray, fontSize = 13.sp)
                    }
                    Button(
                        onClick = { followedStates = followedStates.toMutableList().also {
                            it[realIndex] = !it[realIndex]
                        } },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                                if (isFollowed) Color(0xFFFF6F00) // أخضر عند المتابعة
                                else Color(0xFF176DBA)           // أزرق افتراضي قبل المتابعة
                        ),
                        modifier = Modifier.height(38.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            if (isFollowed) "Following" else "Follow",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSearchReelsAndUsersScreen() {
    val navController = rememberNavController()
    SearchReelsAndUsersScreen(navController)
}



