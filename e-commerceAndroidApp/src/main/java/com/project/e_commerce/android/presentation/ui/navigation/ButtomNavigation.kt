package com.project.e_commerce.android.presentation.ui.navigation

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomAppBar
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
            currentRoute != Screens.ReelsScreen.SearchScreen.route &&
            currentRoute != Screens.ProfileScreen.NotificationScreen.route&&
            currentRoute != Screens.ProductScreen.SearchScreen.route&&
            currentRoute != Screens.ProductScreen.DetailsScreen.route

        ) {
            BottomAppBar(
                contentColor = Color.Transparent,
                backgroundColor = Color.White,
                cutoutShape = RoundedCornerShape(50.dp), // Circular cutout for FAB
                elevation = 0.dp,
                windowInsets = WindowInsets(bottom = 0.dp)
            ) {

                BottomNavigation(
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    modifier = Modifier.clip(RoundedCornerShape(0.dp))
                ) {
                    // Filter out the AddProductScreen since it's replaced by FAB
                    val filteredScreens = screens

                    if (showBuy) {
                        filteredScreens.take(2).forEach { item ->
                            BottomNavigationItem(
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(item.route) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Icon(
                                            painter = painterResource(
                                                id = item.icon ?: R.drawable.ic_cart
                                            ),
                                            contentDescription = null,
                                            tint = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            modifier = Modifier.size(26.dp).padding(top = 4.dp, bottom = 4.dp)
                                        )

                                        Text(
                                            modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                                            text = item.title.toString(),
                                            fontSize = 12.sp,
                                            fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal,
                                            color = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                selectedContentColor = PrimaryColor,
                                unselectedContentColor = BlackColor80
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f, true))
                        filteredScreens.takeLast(2).forEach { item ->
                            BottomNavigationItem(
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(item.route) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Icon(
                                            painter = painterResource(
                                                id = item.icon ?: R.drawable.ic_cart
                                            ),
                                            contentDescription = null,
                                            tint = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            modifier = Modifier.size(26.dp).padding(top = 4.dp, bottom = 4.dp)
                                        )

                                        Text(
                                            modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                                            text = item.title.toString(),
                                            fontSize = 12.sp,
                                            fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal,
                                            color = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                selectedContentColor = PrimaryColor,
                                unselectedContentColor = BlackColor80
                            )
                        }
                    } else {
                        filteredScreens.forEach { item ->
                            BottomNavigationItem(
                                selected = currentRoute   == item.route,
                                onClick = {
                                    navController.navigate(item.route) {
                                        popUpTo(item.route) { inclusive = true }
                                    }
                                },
                                icon = {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Icon(
                                            painter = painterResource(
                                                id = item.icon ?: R.drawable.ic_cart
                                            ),
                                            contentDescription = null,
                                            tint = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            modifier = Modifier.size(26.dp).padding(top = 2.dp, bottom = 4.dp)
                                        )

                                        Text(
                                            modifier = Modifier.padding(top = 2.dp, bottom = 4.dp),
                                            text = item.title.toString(),
                                            fontSize = 12.sp,
                                            fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal,
                                            color = if (currentRoute == item.route) PrimaryColor else BlackColor80,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                },
                                selectedContentColor = PrimaryColor,
                                unselectedContentColor = BlackColor80
                            )
                        }
                    }
                }
            }
        }
    }
}