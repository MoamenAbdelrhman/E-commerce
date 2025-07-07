package com.project.e_commerce.android.presentation.ui.screens.forgetPassword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.utail.GrayColor80
import com.project.e_commerce.android.presentation.ui.utail.PrimaryColor

@Composable
fun ResetPasswordScreen(navController: NavController) {
    var code1 by remember { mutableStateOf("") }
    var code2 by remember { mutableStateOf("") }
    var code3 by remember { mutableStateOf("") }
    var code4 by remember { mutableStateOf("") }
    var code5 by remember { mutableStateOf("") }
    var code6 by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Reset Password",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF114B7F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, bottom = 32.dp),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = "Reset Password Icon",
            modifier = Modifier
                .size(150.dp)
                .background(Color(0xFFE8EEF3), CircleShape)
                .padding(start = 26.dp, end = 26.dp, top = 26.dp, bottom = 16.dp)
                .padding(bottom = 16.dp)
        )
        Text(
            text = "Please enter the 6 numbers that we sent to your e-mail",
            fontSize = 16.sp,
            color = Color(0xFF737272),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top= 18.dp, bottom = 24.dp, start = 18.dp, end = 18.dp),
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = code1,
                onValueChange = { if (it.length <= 1) code1 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = code2,
                onValueChange = { if (it.length <= 1) code2 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = code3,
                onValueChange = { if (it.length <= 1) code3 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = code4,
                onValueChange = { if (it.length <= 1) code4 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = code5,
                onValueChange = { if (it.length <= 1) code5 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
            OutlinedTextField(
                value = code6,
                onValueChange = { if (it.length <= 1) code6 = it },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = GrayColor80
                ),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text(
                text = "Code was sent to",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "example@gmail.com",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 4.dp),
            )
        }

        Text(
            text = "Edit E-mail",
            fontSize = 16.sp,
            fontWeight = Bold,
            color = Color(0xFF114B7F),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { /* Handle Edit action */ },
            textAlign = TextAlign.Center
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Re-send code in",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = " 50 ",
                fontSize = 14.sp,
                color = Color(0xFF114B7F),
                modifier = Modifier
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = "seconds",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = {
                navController.navigate(Screens.LoginScreen.ChangePasswordScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)), // لون برتقالي
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(12.dp)
        ) {
            Text(
                text = "Submit code",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ResetPasswordScreenPreview() {
    ResetPasswordScreen(navController = rememberNavController())
}