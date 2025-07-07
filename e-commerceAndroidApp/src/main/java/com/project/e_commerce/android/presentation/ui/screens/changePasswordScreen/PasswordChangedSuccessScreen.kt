package com.project.e_commerce.android.presentation.ui.screens.changePasswordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80

@Composable
fun PasswordChangedSuccessScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Password Changed Successfully!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF114B7F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Box(contentAlignment = Alignment.Center) {
            Icon(
                painter = painterResource(id = R.drawable.star),
                contentDescription = "Star Icon",
                tint = Color(0xFF22C55E), // لون ذهبي للنجمة
                modifier = Modifier
                    .size(80.dp) // حجم النجمة أكبر لتكون خلفية
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_check_circle),
                contentDescription = "Success Icon",
                tint = Color.White, // لون أخضر نجاح
                modifier = Modifier
                    .size(60.dp) // حجم أصغر لتظهر فوق النجمة
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Your password has been changed successfully. You can now log in with your new password.",
            fontSize = 16.sp,
            color = Color(0xFF737272),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(44.dp))

        Button(
            onClick = {
                navController.navigate(Screens.LoginScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = false }
                }
            }, // العودة إلى صفحة تسجيل الدخول
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(8.dp)
        ) {
            Text(
                text = "Login Now!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PasswordChangedSuccessScreenPreview() {
    PasswordChangedSuccessScreen(navController = rememberNavController())
}