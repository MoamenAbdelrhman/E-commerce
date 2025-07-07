package com.project.e_commerce.android.presentation.ui.composable.composableScreen.public

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.achiver.presentation.ui.composable.spacerComposable.SpacerVerticalMedium
import com.project.e_commerce.android.presentation.ui.utail.BlackColor37
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.smallUnit
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable

@Composable
fun CustomizeAlertDialog(
    title: String,
    fontSizeTitle: TextUnit = UnitsApplication.mediumFontSize,
    backgroundColor: Color = Color.White,
    roundedCornerShape: Dp = smallUnit,
    hasDrawable: Boolean = false,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = UnitsApplication.mediumUnit)
        .background(color = backgroundColor, shape = RoundedCornerShape(roundedCornerShape)),
    BodyComposable: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BlackColor37)
            .noRippleClickable {

            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerVerticalMedium()
            BodyComposable()
            if (!hasDrawable) {
                Text(
                    text = title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-24).dp),
                    textAlign = TextAlign.Center,
                    color = BlackColor80,
                    fontSize = fontSizeTitle,
                    fontWeight = FontWeight.Bold
                )
                SpacerVerticalMedium()
            }
        }
    }
}
