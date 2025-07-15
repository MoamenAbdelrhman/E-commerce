package com.project.e_commerce.android.presentation.ui.screens.profileScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
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

@Composable
fun ProfileScreen(navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        Pair(R.drawable.ic_reels, R.drawable.ic_reels),
        Pair(R.drawable.ic_products_filled, R.drawable.ic_products),
        Pair(R.drawable.ic_save_filled, R.drawable.ic_save),
        Pair(R.drawable.ic_love_checked, R.drawable.ic_love_un_checked)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 6.dp, end = 4.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(Screens.ProfileScreen.NotificationScreen.route) }) {
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_icon), // أيقونة الجرس الخاصة بك
                            contentDescription = "Notifications",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(28.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFFFF3D00), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                IconButton(onClick = { navController.navigate(Screens.ProfileScreen.SettingsScreen.route) }) {
                    Icon(
                        modifier = Modifier.padding(7.dp),
                        painter = painterResource(id = R.drawable.ic_menu),
                        contentDescription = "Menu",
                        tint = Color(0xFFFF6F00)
                    )
                }
            }
        }


        item {
            // Profile Image
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = null,
                modifier = Modifier.size(100.dp).clip(CircleShape)
            )
        }

        item { Spacer(modifier = Modifier.height(8.dp)) }

        item {
            // Name & Username
            Text("Jenny Wilson", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D3D67))
            Text("@jenny_wilson", fontSize = 13.sp, color = Color.Gray)
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            // Stats
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 56.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProfileStat("122", "Following")
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(20.dp)
                        .width(0.5.dp)
                )
                ProfileStat("150", "Followers")
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(20.dp)
                        .width(0.5.dp)
                )
                ProfileStat("200", "Likes")
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        item {
            // Edit / Share Buttons
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 60.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { navController.navigate(Screens.ProfileScreen.EditProfileScreen.route) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF176DBA)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(42.dp)
                ) {
                    Text("Edit Profile", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
                Button(
                    onClick = { /* Share */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFf2f2f2)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).height(42.dp)
                ) {
                    Text("Share Profile", color = Color.Black, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        item { Spacer(modifier = Modifier.height(12.dp)) }

        item {
            Button(
                onClick = { navController.navigate(Screens.ProfileScreen.AddNewContentScreen.route) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp)
                    .height(42.dp),
                elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                Text("+ Add New Post", fontSize = 15.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }

        item {
            // Tabs Row
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
                            tint = if (selectedTabIndex == index) Color(0xFFFF6F00) else Color.Gray,
                            modifier = Modifier.size( if (index == 0) 30.dp else 20.dp )
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(16.dp)) }

        // هنا أهم جزء: نعرض الشبكة المطلوبة حسب التاب المختار كـ item داخل الـ LazyColumn
        item {
            when (selectedTabIndex) {
                0 -> SimpleReelGrid(navController)      // Reels
                1 -> SimpleProductGrid(navController)   // Products
                2 -> MixedSavedGrid(navController)      // Saved
                3 -> LikedReelGrid(navController)       // Likes
            }
        }
    }
}


@Composable
fun ProfileStat(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(number,color = Color(0xFF0D3D67), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, fontSize = 12.sp, color = Color.Gray)
    }
}

@Composable
fun SimpleReelGrid(navController: NavHostController) {
    val reels = listOf(
        R.drawable.perfume1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.perfume1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.perfume1, R.drawable.img2, R.drawable.img3
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(650.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(reels) { imageRes ->
            Box(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .height(130.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_play),
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Spacer(Modifier.width(4.dp))
                    Text("105", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun MixedSavedGrid(navController: NavHostController) {
    val savedItems = listOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
        )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(650.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(savedItems) { imageRes ->
            Box(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .height(130.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



@Composable
fun LikedReelGrid(navController: NavHostController) {
    val likedReels = listOf(
        R.drawable.img4, R.drawable.img2, R.drawable.img3, R.drawable.perfume1,
        R.drawable.img4, R.drawable.img2, R.drawable.img3, R.drawable.perfume1,
        R.drawable.img4, R.drawable.img2, R.drawable.img3, R.drawable.perfume1,
        )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(650.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(likedReels) { imageRes ->
            Box(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .height(130.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}


@Composable
fun SimpleProductGrid(navController: NavHostController) {
    val products = listOf(
        Triple("White Laptop", "In Stock", "$100"),
        Triple("Elegant Perfume", "Out of Stock", "$50"),
        Triple("Smart Watch", "5 left", "$75"),
        Triple("Designer Bag", "10 left", "$150"),
        Triple("Smart Watch", "Out of Stock", "$75"),
        Triple("Smart Watch", "In Stock", "$100"),
        Triple("Smart Watch", "In Stock", "$75"),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(650.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(products) { (title, stock, price) ->
            Column(
                modifier = Modifier
                    .background(Color(0xFFF8F8F8))
                    .padding(bottom = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img2),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

                // Title
                Text(
                    title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(top = 6.dp, start = 8.dp, end = 8.dp)
                )

                // Stock & Price in same row
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stock,
                        color = if (stock == "Out of Stock") Color(0xFFEB1919)
                                else if (stock == "In Stock") Color(0xFF22C55E)
                                else Color.Gray,
                        fontSize = 12.sp
                    )
                    Text(
                        price,
                        color = Color(0xFFFF6F00),
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
