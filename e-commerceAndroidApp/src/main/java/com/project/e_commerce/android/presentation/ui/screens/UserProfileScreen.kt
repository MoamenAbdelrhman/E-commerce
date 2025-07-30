package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.profileScreen.ProfileStat
import com.project.e_commerce.android.presentation.ui.screens.profileScreen.SimpleProductGrid
import com.project.e_commerce.android.presentation.ui.screens.profileScreen.SimpleReelGrid


@Composable
fun UserProfileScreen(navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        Pair(R.drawable.ic_reels, R.drawable.ic_reels),
        Pair(R.drawable.ic_products_filled, R.drawable.ic_products)
    )

    var isFollowing by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Top Bar: Back (left), Menu (right)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp, start = 6.dp, end = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .size(22.dp)
                        .padding(start = 2.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = "Back",
                        tint = Color(0xFF0066CC)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { /* Show more actions */ },
                    modifier = Modifier
                        .size(48.dp)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_more), // ثلاث نقاط فوق بعضهم
                        contentDescription = "Menu",
                        tint = Color(0xFF0066CC),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }


        item {
            // Stats

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ProfileStat("122", "Following") {
                        navController.navigate(
                            Screens.FollowListScreen.createRoute(
                                username = "karme",
                                startTab = 1,
                                showFriendsTab = false
                            )
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp).clip(CircleShape)
                    )
                    ProfileStat("150K", "Followers") {
                        navController.navigate(
                            Screens.FollowListScreen.createRoute(
                                username = "karme",
                                startTab = 0,
                                showFriendsTab = false
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

        }
        item {
            // Name & Username
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Karme Show",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D3D67)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = R.drawable.verified_badge),
                    contentDescription = "Verified",
                    modifier = Modifier.size(22.dp)
                )
            }
            Text("@karme ", fontSize = 13.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item { Spacer(modifier = Modifier.height(4.dp)) }

        item {
            ProfileStat("1.5M", "Likes")
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            // Follow Button (main orange color)
            Button(
                onClick = {
                    isFollowing = !isFollowing
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing) Color.LightGray else Color(0xFFFF6600)
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(150.dp)
                    .height(44.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                Text(
                    if (isFollowing) "Following" else "Follow",
                    fontSize = 16.sp,
                    color = if (isFollowing) Color.DarkGray else Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        item { Spacer(modifier = Modifier.height(10.dp)) }



        item {
            // Tabs Row (Reels, Products only)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                tabs.forEachIndexed { index, (filledIcon, outlineIcon) ->
                    IconButton(
                        onClick = { selectedTabIndex = index },
                        modifier = Modifier.size(34.dp)
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (selectedTabIndex == index) filledIcon else outlineIcon
                            ),
                            contentDescription = null,
                            tint = if (selectedTabIndex == index) Color(0xFFFF6600) else Color.Gray,
                            modifier = Modifier.size( if (index == 0) 30.dp else 20.dp )
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            when (selectedTabIndex) {
                0 -> SimpleReelGrid(navController)      // Reels
                1 -> SimpleProductGrid(navController)   // Products
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewUserProfileScreen() {
    val navController = rememberNavController()
    UserProfileScreen(navController = navController)
}