package com.project.e_commerce.android.presentation.ui.screens.changePasswordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.achiver.presentation.ui.composable.spacerComposable.SpacerVerticalSmall
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.composable.spacerComposable.SpacerHorizontalSmall
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.createAccountScreen.PasswordFieldWithLabel
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.ErrorPrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.mediumUnit
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable
import com.project.e_commerce.android.presentation.viewModel.restPasswordViewModel.RestPasswordViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangePasswordScreen(navController: NavController) {
    val viewModel: RestPasswordViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var password by remember { mutableStateOf("") }
    var rePassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rePasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = mediumUnit),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = null,
                tint = BlackColor80,
                modifier = Modifier.noRippleClickable {
                    viewModel.onClickBackArrowButton(navController)
                }
            )
            SpacerHorizontalSmall()
            Text(
                text = "Change Password",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF114B7F), // لون أزرق غامق
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )


        }

        Spacer(modifier = Modifier.height(36.dp))

        PasswordFieldWithLabel(
            label = "New Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Enter your password",
            passwordVisible = passwordVisible,
            onToggleVisibility = { passwordVisible = !passwordVisible }
        )
        Spacer(modifier = Modifier.height(16.dp))

        SpacerVerticalSmall()
        if (state.newPassword.isError) {
            Text(
                text = state.newPassword.errorMessage,
                color = ErrorPrimaryColor,
                fontWeight = FontWeight.Normal,
                fontSize = UnitsApplication.tinyFontSize
            )
        }
        PasswordFieldWithLabel(
            label = "Re-Password",
            value = rePassword,
            onValueChange = { rePassword = it },
            placeholder = "Re-Enter your password",
            passwordVisible = rePasswordVisible,
            onToggleVisibility = { rePasswordVisible = !rePasswordVisible }
        )
        SpacerVerticalSmall()
        if (state.confirmNewPassword.isError) {
            Text(
                text = state.confirmNewPassword.errorMessage,
                color = ErrorPrimaryColor,
                fontWeight = FontWeight.Normal,
                fontSize = UnitsApplication.tinyFontSize
            )
        }

        Spacer(modifier = Modifier.height(36.dp))
        Button(
            onClick = {
                navController.navigate(Screens.LoginScreen.PasswordChangedSuccessScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(12.dp)
        ) {
            Text(
                text = "Change Password",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        if (state.isError) {
            Text(
                text = state.errorMessage,
                color = ErrorPrimaryColor,
                fontWeight = FontWeight.Normal,
                fontSize = UnitsApplication.tinyFontSize
            )
        }
    }
    if(state.isSuccessChanged){

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(navController = rememberNavController())
}
