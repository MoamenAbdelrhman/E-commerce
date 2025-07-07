package com.project.e_commerce.android.presentation.ui.composable.composableScreen.public

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.achiver.presentation.ui.composable.spacerComposable.SpacerVerticalSmall
import com.project.e_commerce.android.presentation.ui.utail.ErrorPrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.GrayColor80
import com.project.e_commerce.android.presentation.ui.utail.PrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.PrimaryGray
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.smallFontSize
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    leadingIconId: Int? = null,
    trailingIconId: Int? = null,
    isError: Boolean = false,
    isPasswordField: Boolean = false,
    passwordVisible: Boolean = false,
    isNecessary: Boolean = true,
    onPasswordToggle: (() -> Unit)? = null,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
        .border(1.dp, color = if (isError) ErrorPrimaryColor else GrayColor80),
    keyboardType: KeyboardType = KeyboardType.Text,
    ) {
    TextField(
        singleLine = true,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, color = if (isError) ErrorPrimaryColor else GrayColor80),
        trailingIcon = {
            if (isPasswordField && trailingIconId != null && onPasswordToggle != null) {
                Image(
                    painter = painterResource(id = trailingIconId),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable { onPasswordToggle() }
                )
            }
        },
        placeholder = {
            Row {
                Text(
                    text = placeholderText,
                    color = PrimaryGray,
                    fontWeight = FontWeight.Normal,
                    fontSize = smallFontSize
                )
                if(isNecessary)
                Text(
                    text = "*",
                    color = ErrorPrimaryColor,
                    fontWeight = FontWeight.Normal,
                    fontSize = smallFontSize
                )
            }
        },
        leadingIcon = {

            leadingIconId?.let { painterResource(id = it) }?.let {
                Icon(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp),
                    tint = PrimaryGray
                )
            }
        },
        visualTransformation = if (isPasswordField && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = PrimaryColor,
            textColor = if (isError) ErrorPrimaryColor else PrimaryGray,
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorLabelColor = ErrorPrimaryColor,
            errorTrailingIconColor = ErrorPrimaryColor,
            errorLeadingIconColor = ErrorPrimaryColor,
            errorCursorColor = ErrorPrimaryColor,
            errorIndicatorColor = Color.Transparent
        )
    )
    SpacerVerticalSmall()
    errorMessage?.let{if(isError) {
        Text(
            text = errorMessage,
            color = ErrorPrimaryColor,
            fontWeight = FontWeight.Normal,
            fontSize = UnitsApplication.tinyFontSize
        )
    }
    }
}
