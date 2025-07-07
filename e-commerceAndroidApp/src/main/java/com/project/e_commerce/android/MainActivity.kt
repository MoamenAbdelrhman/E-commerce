package com.project.e_commerce.android

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.presentation.ui.navigation.BottomNavigation
import com.project.e_commerce.android.presentation.ui.navigation.MyNavHost
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val isFirstTime = prefs.getBoolean("isFirstTime", true)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)
        val startDestination = when {
            isFirstTime -> Screens.SplashScreen.route
            isLoggedIn -> Screens.ReelsScreen.route
            else -> Screens.LoginScreen.route
        }

        val items = listOf(
            Screens.ReelsScreen,
            Screens.ProductScreen,
            Screens.CartScreen,
            Screens.ProfileScreen,
        )

        setContent {
            @OptIn(ExperimentalMaterialApi::class)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: startDestination
                    val coroutineScope = rememberCoroutineScope()

                    val showBottomBar = currentRoute in listOf(
                        Screens.ReelsScreen.route,
                        Screens.ProductScreen.route,
                        Screens.CartScreen.route,
                        Screens.ProfileScreen.route
                    )

                    Scaffold(
                        bottomBar = {
                            if (showBottomBar) {
                                BottomNavigation(navController, screens = items)
                            }
                        },
                        floatingActionButton = {
                            if (currentRoute == Screens.ReelsScreen.route) {
                                FloatingActionButton(
                                    onClick = { /* action */ },
                                    shape = androidx.compose.foundation.shape.CircleShape,
                                    containerColor = Color(0xFFFF6F00),
                                    contentColor = Color.White,
                                    elevation = FloatingActionButtonDefaults.elevation(
                                        defaultElevation = 4.dp,
                                        pressedElevation = 6.dp
                                    ),
                                ) {
                                    androidx.compose.material3.Text("Buy")
                                }
                            }
                        },
                        floatingActionButtonPosition = androidx.compose.material.FabPosition.Center,
                        isFloatingActionButtonDocked = true
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            MyNavHost(navController, startDestination)
                        }
                    }
                }
            }
        }
    }
}
