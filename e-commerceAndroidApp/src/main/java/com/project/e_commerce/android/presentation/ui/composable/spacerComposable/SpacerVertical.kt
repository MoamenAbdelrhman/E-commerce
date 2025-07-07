package com.example.achiver.presentation.ui.composable.spacerComposable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication

@Composable
fun SpacerVerticalTiny() {
    Spacer(modifier = Modifier.height(UnitsApplication.tinyUnit))
}

@Composable
fun SpacerVerticalSmall() {
    Spacer(modifier = Modifier.height(UnitsApplication.smallUnit))
}

@Composable
fun SpacerVerticalMedium() {
    Spacer(modifier = Modifier.height(UnitsApplication.mediumUnit))
}

@Composable
fun SpacerVerticalLarge() {
    Spacer(modifier = Modifier.height(UnitsApplication.largeUnit))
}

@Composable
fun SpacerVerticalXLarge() {
    Spacer(modifier = Modifier.height(UnitsApplication.xLargeUnit))
}

@Composable
fun SpacerVerticalXXLarge() {
    Spacer(modifier = Modifier.height(UnitsApplication.xxLargeUnit))
}


@Composable
fun SpacerVerticalXXXLarge() {
    Spacer(modifier = Modifier.height(UnitsApplication.xxxLargeUnit))
}