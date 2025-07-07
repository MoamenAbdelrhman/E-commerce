package com.project.e_commerce.android.presentation.ui.screens.profileScreen

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    var isSellerMode by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    var showLogoutDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {

            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )

            IconButton(
                onClick = {
                    navController.navigate(Screens.ProfileScreen.NotificationScreen.route)
                },
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = "Notifications",
                    tint = Color(0xFF0066CC)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = null,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text("Jenny Wilson", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0066CC))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 4.dp, bottom = 12.dp)
                .clickable {
                    navController.navigate(Screens.ProfileScreen.EditProfileScreen.route)
                }
        ) {
            Text("Edit", fontSize = 13.sp, color = Color(0xFF0066CC))
            Spacer(Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit",
                modifier = Modifier.size(12.dp),
                tint = Color(0xFF0066CC)
            )
        }

        ProfileOption(icon = R.drawable.ic_fav, label = "Favourites") {
            navController.navigate(Screens.ProfileScreen.FavouritesScreen.route)
        }
        ProfileOption(icon = R.drawable.ic_orders, label = "Orders history") {
            navController.navigate(Screens.ProfileScreen.OrdersHistoryScreen.route)
        }
        ProfileOption(icon = R.drawable.ic_eye, label = "Recently viewed") {
            navController.navigate(Screens.ProfileScreen.RecentlyScreen.route)
        }
        ProfileOption(icon = R.drawable.ic_settings, label = "Settings") {
            navController.navigate(Screens.ProfileScreen.SettingsScreen.route)
        }
        ProfileOption(icon = R.drawable.ic_location, label = "Location", trailingText = "Algeria")
        ProfileOption(icon = R.drawable.ic_calendar, label = "Join date", trailingText = "12/06/2025")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth()
                .border(0.8.dp, Color(0xFFFF6600), RoundedCornerShape(12.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(if (isSellerMode) "Switch to user" else "Switch to seller")
            Switch(checked = isSellerMode, onCheckedChange = { isSellerMode = it })
        }

        if (isSellerMode) {
            Spacer(modifier = Modifier.height(16.dp))
            val tabs = listOf("Products", "Reels")
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color(0xFFF8F8F8),
                contentColor = Color(0xFF1B7ACE),
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = Color(0xFFFF6F00),
                        height = 3.dp
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(
                                title,
                                color = if (selectedTabIndex == index) Color(0xFF1B7ACE) else Color.Gray
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            when (selectedTabIndex) {
                0 -> {
                    Button(
                        onClick = { navController.navigate(Screens.ProfileScreen.AddNewProductScreen.route) },
                        modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.elevation(6.dp)
                    ) {
                        Text("Add New Product", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    SimpleProductGrid(navController)
                }
                1 -> {
                    Button(
                        onClick = { navController.navigate(Screens.ProfileScreen.AddNewReelScreen.route) },
                        modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = ButtonDefaults.elevation(6.dp)
                    ) {
                        Text("Add New Reel", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    SimpleReelGrid(navController)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        LogoutSection(onLogoutClick = { showLogoutDialog = true })

        if (showLogoutDialog) {
            AlertDialog(
                onDismissRequest = { showLogoutDialog = false },
                buttons = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // أيقونة الحذف أعلى
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .background(Color(0x1AFF0000)), // شفافة قليلاً
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_delete), // استبدل بـ ic_logout أو مشابه
                                contentDescription = null,
                                tint = Color(0xFFEB1919),
                                modifier = Modifier.size(32.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            "Are you sure you want to log out?",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "You will be signed out of your account and redirected to the home page. Any unsaved progress may be lost.",
                            fontSize = 14.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = { showLogoutDialog = false },
                                modifier = Modifier.weight(1f),
                                border = BorderStroke(1.dp, Color.Gray)
                            ) {
                                Text("Cancel", color = Color.Black)
                            }
                            Button(
                                onClick = {
                                    val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
                                    prefs.edit().putBoolean("isLoggedIn", false).apply()
                                    showLogoutDialog = false
                                    navController.navigate("login_screen") {
                                        popUpTo(0)
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFD92D20)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Logout", color = Color.White)
                            }
                        }
                    }
                },
                backgroundColor = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}


@Composable
fun LogoutSection(onLogoutClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { onLogoutClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_logout),
            contentDescription = "Logout",
            tint = Color(0xFFEB1919),
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("Logout", color = Color(0xFFEB1919), fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}




@Composable
fun ProfileOption(
    icon: Int,
    label: String,
    trailingText: String? = null,
    onClick: () -> Unit = {}
) {
    Divider(Modifier.padding(top = 8.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(label, modifier = Modifier.weight(1f), fontSize = 16.sp)
        trailingText?.let {
            Text(it, color = Color.Gray)
        } ?: Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
    }

    Divider(Modifier.padding(bottom = 8.dp), color = Color(0xFFE0E0E0), thickness = 1.dp)
}


@Composable
fun SimpleProductGrid(navController: NavHostController) {
    val products = listOf(
        Triple("Sleek White Laptop", "Out of stock", 0),
        Triple("Sleek White Laptop", "10 items left", 10),
        Triple("Sleek White Laptop", "50 items left", 50),
        Triple("Sleek White Laptop", "Out of stock", 0),
        Triple("Sleek White Laptop", "10 items left", 10),
        Triple("Sleek White Laptop", "50 items left", 50)
    )

    for (row in products.chunked(3)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for ((title, stockText, stock) in row) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFFF8F8F8))
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img2),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                        )
                        IconButton(
                            onClick = {
                                navController.navigate(Screens.ProfileScreen.EditProductScreen.route)
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(24.dp)
                                .padding(top = 4.dp, end = 4.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Edit",
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    Text(
                        stockText,
                        color = if (stock == 0) Color(0xFFEB1919) else Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Text(
                        title,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("100$", color = Color(0xFFFF6F00), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { /* Unpublish */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Unpublish", fontSize = 11.sp, color = Color.White)
                    }
                }
            }
            if (row.size < 3) {
                repeat(3 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}



@Composable
fun SimpleReelGrid(navController: NavHostController) {
    val items = listOf(
        R.drawable.img1, R.drawable.img2, R.drawable.img3,
        R.drawable.img4, R.drawable.img5, R.drawable.img6,
        R.drawable.perfume1, R.drawable.perfume2, R.drawable.perfume3
    )
    for (row in items.chunked(3)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (imageRes in row) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.ProfileScreen.EditReelScreen.route)
                        },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(24.dp)
                            .padding(top = 4.dp, end = 4.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_edit),
                            contentDescription = "Edit",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(Color(0x88000000))
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_play),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("105", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
            if (row.size < 3) {
                repeat(3 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
