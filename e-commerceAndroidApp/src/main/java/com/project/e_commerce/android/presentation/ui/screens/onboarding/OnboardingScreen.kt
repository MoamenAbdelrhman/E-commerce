package com.project.e_commerce.android.presentation.ui.screens.onboarding

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens

@Composable
fun OnboardingScreen(
    imageRes: Int,
    title: String,
    description: String,
    currentIndex: Int,
    total: Int = 3,
    isLast: Boolean,
    onNextClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (!isLast) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF3C8CE7), Color(0xFF00EAFF))
                        )
                    )
                    .clickable { onSkipClick() }
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Skip",
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            Spacer(modifier = Modifier.height(36.dp))

            Box(
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F6FA))
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Card(
                shape = RoundedCornerShape(topStart = 94.dp, topEnd = 94.dp),
                elevation = 16.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize()
                    .padding(top = 32.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 24.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A73E8),
                        modifier = Modifier.padding(start = 24.dp,top = 6.dp)
                    )
                    Text(
                        text = description,
                        fontSize = 16.sp,
                        color = Color(0xFF6D8299),
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    Button(
                        onClick = onNextClick,
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5722)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .padding(top = 32.dp)
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        elevation = ButtonDefaults.elevation(8.dp)
                    ) {
                        Text(
                            text = if (isLast) "Get Started" else "Next",
                            color = Color.White
                        )
                    }

                    DotIndicators(currentIndex = currentIndex, total = total)
                }
            }
        }
    }
}

@Composable
fun DotIndicators(currentIndex: Int, total: Int = 3) {
    Row(
        modifier = Modifier.padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(total) { index ->
            DotIndicator(isActive = index == currentIndex)
        }
    }
}

@Composable
fun DotIndicator(isActive: Boolean) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .size(if (isActive) 12.dp else 8.dp)
            .clip(CircleShape)
            .background(if (isActive) Color(0xFFFF5722) else Color(0xFFCCCCCC))
    )
}

@Composable
fun OnboardingScreen1(navController: NavController) {
    val context = LocalContext.current

    OnboardingScreen(
        imageRes = R.drawable.onboarding1_image,
        title = "Discover Amazing Products!",
        description = "Explore thousands of items.",
        currentIndex = 0,
        isLast = false,
        onNextClick = { navController.navigate("onboarding2") },
        onSkipClick = {
            val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("isFirstTime", false).apply()
            navController.navigate(Screens.ReelsScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    )
}

@Composable
fun OnboardingScreen2(navController: NavController) {
    val context = LocalContext.current

    OnboardingScreen(
        imageRes = R.drawable.onboarding2_image,
        title = "Safe Payments Fast Delivery",
        description = "Choose from multiple payment methods.",
        currentIndex = 1,
        isLast = false,
        onNextClick = { navController.navigate("onboarding3") },
        onSkipClick = {
            val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("isFirstTime", false).apply()
            navController.navigate(Screens.ReelsScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    )
}

@Composable
fun OnboardingScreen3(navController: NavController) {
    val context = LocalContext.current

    OnboardingScreen(
        imageRes = R.drawable.onboarding3_image,
        title = "Track Your Orders",
        description = "Get real-time updates.",
        currentIndex = 2,
        isLast = true,
        onNextClick = {
            val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("isFirstTime", false).apply()
            navController.navigate(Screens.ReelsScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        },
        onSkipClick = {
            val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            prefs.edit().putBoolean("isFirstTime", false).apply()
            navController.navigate(Screens.ReelsScreen.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen1Preview() {
    OnboardingScreen1(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen2Preview() {
    OnboardingScreen2(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreen3Preview() {
    OnboardingScreen3(navController = rememberNavController())
}
