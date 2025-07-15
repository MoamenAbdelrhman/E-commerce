package com.project.e_commerce.android

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.presentation.ui.navigation.BottomNavigation
import com.project.e_commerce.android.presentation.ui.navigation.MyNavHost
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.AddToCartBottomSheet
import com.project.e_commerce.android.presentation.viewModel.MainUiStateViewModel
import com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel.ReelsScreenViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.zIndex
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.core.tween


class MainActivity : ComponentActivity() {
    private val mainUiStateViewModel by viewModels<MainUiStateViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val isFirstTime = prefs.getBoolean("isFirstTime", true)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)
        val startDestination = when {
            isFirstTime -> Screens.SplashScreen.route
            isLoggedIn -> Screens.ReelsScreen.route
            else -> Screens.ReelsScreen.route
        }

        val items = listOf(
            Screens.ReelsScreen,
            Screens.ProductScreen,
            Screens.CartScreen,
            Screens.ProfileScreen,
        )

        setContent {
            val isBottomSheetVisible by mainUiStateViewModel.isBottomSheetVisible.collectAsState()
            val viewModel: ReelsScreenViewModel = koinViewModel()
            val isAddToCartSheetVisible by mainUiStateViewModel.isAddToCartSheetVisible.collectAsState()

            MyApplicationTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        ,
                    color = Color.Transparent
                ) {
                    val navController = rememberNavController()
                    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: startDestination
                    LaunchedEffect(currentRoute) {
                        // كلما تغيرت الصفحة، أغلق الشيت إذا كان ظاهر
                        if (isAddToCartSheetVisible) {
                            mainUiStateViewModel.hideAddToCartSheet()
                        }
                    }

                    val coroutineScope = rememberCoroutineScope()
                    val isBottomSheetVisible by mainUiStateViewModel.isBottomSheetVisible.collectAsState()

                    val showBottomBar = currentRoute in listOf(
                        Screens.ReelsScreen.route,
                        Screens.ProductScreen.route,
                        Screens.CartScreen.route,
                        Screens.ProfileScreen.route,
                        Screens.ReelsScreen.ExploreScreen.route
                    )

                    Scaffold(
                        bottomBar = {
                            if (showBottomBar && !isBottomSheetVisible) { // الشرط الصح!
                                Box(
                                    Modifier.fillMaxWidth()
                                ) {
                                    BottomNavigation(navController, screens = items)
                                }
                            }
                        },
                        floatingActionButton = {
                            AnimatedVisibility(
                                visible = currentRoute == Screens.ReelsScreen.route && !isBottomSheetVisible,
                                enter = scaleIn(
                                    initialScale = 0.1f, // يبدأ صغير جدًا
                                    animationSpec = tween(350)
                                ) + fadeIn(animationSpec = tween(350)),
                                exit = scaleOut(
                                    targetScale = 0.1f, // يصغر جدًا عند الخروج
                                    animationSpec = tween(220)
                                ) + fadeOut(animationSpec = tween(220))
                            ) {
                                FloatingActionButton(
                                    onClick = {
                                        mainUiStateViewModel.showAddToCartSheet()
                                    },
                                    shape = CircleShape,
                                    containerColor = Color(0xFFFF6F00),
                                    contentColor = Color.White,
                                    elevation = FloatingActionButtonDefaults.elevation(
                                        defaultElevation = 0.dp,
                                        pressedElevation = 0.dp
                                    ),
                                    modifier = Modifier
                                        .zIndex(1f)
                                        .padding(bottom = 12.dp)
                                        .size(64.dp)
                                        .border(5.dp, Color.White, CircleShape)
                                        .drawBehind {
                                            val shadowRadius = size.width * 0.58f
                                            val shadowOffsetY = size.height * 0.0f
                                            drawCircle(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(
                                                        Color.Black,
                                                        Color.Black.copy(alpha = 0.2f)
                                                    ),
                                                    center = Offset(center.x, center.y + shadowOffsetY),
                                                    radius = shadowRadius
                                                ),
                                                radius = shadowRadius,
                                                center = Offset(center.x, center.y + shadowOffsetY)
                                            )
                                        }
                                ) {
                                    Text(
                                        "Buy",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        },

                        /*floatingActionButton = {
                            AnimatedVisibility(
                                visible = currentRoute == Screens.ReelsScreen.route && !isBottomSheetVisible,
                                enter = slideInVertically(
                                    initialOffsetY = { it / 2 }, // يدخل من أسفل منتصف الشاشة تقريبًا (يمكنك تعديلها لـ it)
                                ) + fadeIn(),
                                exit = slideOutVertically(
                                    targetOffsetY = { it / 2 }, // يختفي لنفس الاتجاه
                                ) + fadeOut()
                            ) {
                                FloatingActionButton(
                                    onClick = {
                                        mainUiStateViewModel.showAddToCartSheet()
                                    },
                                    shape = CircleShape,
                                    containerColor = Color(0xFFFF6F00),
                                    contentColor = Color.White,
                                    elevation = FloatingActionButtonDefaults.elevation(
                                        defaultElevation = 6.dp,
                                        pressedElevation = 4.dp
                                    ),
                                    modifier = Modifier
                                        .zIndex(1f)
                                        .padding(bottom = 12.dp)
                                        .size(64.dp)
                                        .border(5.dp, Color.White, CircleShape)
                                        .drawBehind {
                                            val shadowRadius = size.width * 0.58f
                                            val shadowOffsetY = size.height * 0.0f
                                            drawCircle(
                                                brush = Brush.radialGradient(
                                                    colors = listOf(
                                                        Color.Black,
                                                        Color.Black.copy(alpha = 0.2f)
                                                    ),
                                                    center = Offset(center.x, center.y + shadowOffsetY),
                                                    radius = shadowRadius
                                                ),
                                                radius = shadowRadius,
                                                center = Offset(center.x, center.y + shadowOffsetY)
                                            )
                                        }

                                ) {
                                    Text(
                                        "Buy",
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        },*/
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        modifier = Modifier.fillMaxSize()
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = paddingValues.calculateTopPadding())
                        ) {
                            MyNavHost(navController, startDestination)

                            if (isAddToCartSheetVisible) {
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    // طبقة شفافة تغطي الشاشة كلها وتغلق الشيت عند الضغط
                                    Box(
                                        Modifier
                                            .fillMaxSize()
                                            .background(Color.Black.copy(alpha = 0.25f))
                                            .clickable(
                                                indication = null,
                                                interactionSource = remember { MutableInteractionSource() }
                                            ) {
                                                mainUiStateViewModel.hideAddToCartSheet()
                                            }
                                    )

                                    // الشيت نفسه (من أسفل)
                                    Box(
                                        modifier = Modifier
                                            .align(Alignment.BottomCenter)
                                            // يمنع تسرب الضغط خارج الشيت
                                            .pointerInput(Unit) { }
                                    ) {
                                        AddToCartBottomSheet(
                                            onClose = { mainUiStateViewModel.hideAddToCartSheet() },
                                            productName = "Sample Product",
                                            productPrice = "$29.99",
                                            onAddToCart = { color, size, quantity -> /* ... */ },
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
