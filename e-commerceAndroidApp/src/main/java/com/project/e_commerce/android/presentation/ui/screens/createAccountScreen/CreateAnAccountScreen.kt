package com.project.e_commerce.android.presentation.ui.screens.createAccountScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.loginScreen.SocialLoginButton


@Composable
fun CreateAnAccountScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rePasswordVisible by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Sign up",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF174378),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputFieldWithLabel("Username", username, { username = it }, "Enter username")
        Spacer(modifier = Modifier.height(16.dp))

        InputFieldWithLabel("Email", email, { email = it }, "Enter your email")
        Spacer(modifier = Modifier.height(16.dp))

        PasswordFieldWithLabel(
            label = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            passwordVisible = passwordVisible,
            onToggleVisibility = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.height(16.dp))

        PasswordFieldWithLabel(
            label = "Re-Password",
            value = rePassword,
            onValueChange = { rePassword = it },
            placeholder = "Re-Enter your password",
            passwordVisible = rePasswordVisible,
            onToggleVisibility = { rePasswordVisible = !rePasswordVisible }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(Screens.LoginScreen.route) {
                    popUpTo(Screens.SplashScreen.route) { inclusive = false }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(12.dp)
        ) {
            Text(
                text = "Register",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFB3C1D1))
            Text(
                text = "  Or Login with  ",
                color = Color(0xFF737272),
                fontSize = 14.sp
            )
            Divider(modifier = Modifier.weight(1f), color = Color(0xFFB3C1D1))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            SocialLoginButton(R.drawable.ic_facebook)
            Spacer(modifier = Modifier.width(12.dp))
            SocialLoginButton(R.drawable.ic_google)
            Spacer(modifier = Modifier.width(12.dp))
            SocialLoginButton(R.drawable.ic_apple)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Already have an account?",
                fontSize = 14.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Login Now",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF174378),
                modifier = Modifier.clickable {
                    navController.popBackStack() // يرجع لشاشة تسجيل الدخول
                }
            )
        }

        Spacer(modifier = Modifier.height(250.dp))
    }
}



@Composable
fun PasswordFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    passwordVisible: Boolean,
    onToggleVisibility: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF174378),
            modifier = Modifier.padding(start = 4.dp,bottom = 4.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Lock else Icons.Filled.Lock
                IconButton(onClick = onToggleVisibility) {
                    Icon(imageVector = icon, contentDescription = null)
                }
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            )
        )
    }
}

@Composable
fun InputFieldWithLabel(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier.padding(start = 4.dp,bottom = 4.dp),
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF174378)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            )
        )
    }
}




@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    CreateAnAccountScreen(navController = rememberNavController())
}