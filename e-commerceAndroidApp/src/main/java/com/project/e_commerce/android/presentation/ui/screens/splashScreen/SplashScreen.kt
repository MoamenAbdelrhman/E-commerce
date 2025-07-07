package com.project.e_commerce.android.presentation.ui.screens.splashScreen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(3000)
        val prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        prefs.edit().putBoolean("isFirstTime", false).apply()
        navController.navigate("onboarding1") {
            popUpTo("splash_screen") { inclusive = true }
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            // Logo with play button
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = "BUYV Logo",
                modifier = Modifier.size(180.dp)
            )
            // Text "BUYV"
            Text(
                text = "BUYV",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0B3050)
            )
            // Slogan
            Text(
                text = "Shop Smarter. Live Better.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF114B7F)
            )
        }
        // Blue shape on top-right as image with negative margin
        Image(
            painter = painterResource(id = R.drawable.shape1),
            contentDescription = "Blue Shape",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(200.dp)
                .offset(x = (20).dp, y = (0).dp) // Negative margin effect
        )
        // Orange shape on bottom-left as image with negative margin
        Image(
            painter = painterResource(id = R.drawable.shape2),
            contentDescription = "Orange Shape",
            modifier = Modifier
                .align(Alignment.BottomStart)
                .size(200.dp)
                .offset(x = (-35).dp, y = (30).dp) // Negative margin effect
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}