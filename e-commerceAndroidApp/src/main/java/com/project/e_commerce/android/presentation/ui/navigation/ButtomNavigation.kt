package com.project.e_commerce.android.presentation.ui.navigation

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.PrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.largeUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.tinyFontSize
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.tinyUnit


@Composable
fun BottomNavigation(
    navController: NavController,
    screens: List<Screens>
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBuy = currentRoute == Screens.ReelsScreen.route


    var profileBadgeCount by remember { mutableStateOf(2) }
    var productsBadgeCount by remember { mutableStateOf(1) }

    if (currentRoute != null) {
        if (
            currentRoute != Screens.SplashScreen.route &&
            currentRoute != Screens.LoginScreen.route &&
            currentRoute != Screens.LoginScreen.EnterEmailScreen.route &&
            currentRoute != Screens.LoginScreen.ResetPasswordScreen.route &&
            currentRoute != Screens.LoginScreen.ChangePasswordScreen.route &&
            currentRoute != Screens.ProfileScreen.SettingsScreen.route &&
            currentRoute != Screens.ProfileScreen.OrdersHistoryScreen.route &&
            currentRoute != Screens.ProfileScreen.FavouritesScreen.route &&
            currentRoute != Screens.ProfileScreen.RequestHelpScreen.route &&
            currentRoute != Screens.ProfileScreen.EditPersonalProfile.route &&
            currentRoute != Screens.ReelsScreen.SearchReelsAndUsersScreen.route &&
            currentRoute != Screens.ProfileScreen.NotificationScreen.route &&
            currentRoute != Screens.ProductScreen.SearchScreen.route &&
            currentRoute != Screens.ProductScreen.DetailsScreen.route
        ) {
            // Floating bar أصغر من الشاشة
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                contentAlignment = Alignment.BottomCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
                        .background(Color.White)
                        .drawBehind {
                            val shadowHeight = 12.dp.toPx()
                            drawRoundRect(
                                cornerRadius = androidx.compose.ui.geometry.CornerRadius(28.dp.toPx(), 28.dp.toPx()),
                                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0x11000000), // ظل في الأعلى (أسود خفيف)
                                        Color.Transparent
                                    ),
                                    startY = 0f,
                                    endY = shadowHeight
                                ),
                                size = size
                            )
                        }

                ) {
                    Row(
                        Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        screens.forEach { item ->
                            val selected = currentRoute == item.route ||
                                    (item.route == Screens.ReelsScreen.route && currentRoute == Screens.ReelsScreen.ExploreScreen.route)
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable(
                                        indication = null,
                                        interactionSource = remember { MutableInteractionSource() },
                                    ) {
                                        // عند الضغط على بروفايل، صفر البادج
                                        if (item.route == Screens.ProfileScreen.route) {
                                            profileBadgeCount = 0
                                        }
                                        // عند الضغط على المنتجات، صفر البادج
                                        if (item.route == Screens.ProductScreen.route) {
                                            productsBadgeCount = 0
                                        }
                                        if (
                                            item.route == Screens.ReelsScreen.route &&
                                            (currentRoute == Screens.ReelsScreen.route || currentRoute == Screens.ReelsScreen.ExploreScreen.route)
                                        ) {
                                            // لا شيء
                                        } else if (currentRoute != item.route) {
                                            navController.navigate(item.route) {
                                                popUpTo(item.route) { inclusive = true }
                                            }
                                        }
                                    }
                            ) {
                                Box(
                                    contentAlignment = Alignment.TopEnd,
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = item.icon ?: R.drawable.ic_cart),
                                        contentDescription = null,
                                        tint = if (selected) Color(0xFF176DBA) else BlackColor80,
                                        modifier = Modifier.size(26.dp).padding(top = 4.dp)
                                    )

                                    // Badge للبروفايل
                                    if (item.route == Screens.ProfileScreen.route && profileBadgeCount > 0) {
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .offset(x = 8.dp, y = (-2).dp)
                                                .background(Color.Red, shape = CircleShape)
                                        )
                                    }

                                    // Badge للمنتجات
                                    if (item.route == Screens.ProductScreen.route && productsBadgeCount > 0) {
                                        Box(
                                            modifier = Modifier
                                                .size(10.dp)
                                                .offset(x = 8.dp, y = (-2).dp)
                                                .background(Color.Red, shape = CircleShape)
                                        )
                                    }
                                }
                                Text(
                                    text = item.title.toString(),
                                    fontSize = 12.sp,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                    color = if (selected) Color(0xFF176DBA) else BlackColor80,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
