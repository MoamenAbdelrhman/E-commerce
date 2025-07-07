package com.project.e_commerce.android.presentation.ui.composable.spacerComposable


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication

@Composable
fun SpacerHorizontalTiny() {
    Spacer(modifier = Modifier.width(UnitsApplication.tinyUnit))
}

@Composable
fun SpacerHorizontalSmall() {
    Spacer(modifier = Modifier.width(UnitsApplication.smallUnit))
}

@Composable
fun SpacerHorizontalMedium() {
    Spacer(modifier = Modifier.width(UnitsApplication.mediumUnit))
}

@Composable
fun SpacerHorizontalLarge() {
    Spacer(modifier = Modifier.width(UnitsApplication.largeUnit))
}

@Composable
fun SpacerHorizontalXLarge() {
    Spacer(modifier = Modifier.width(UnitsApplication.xLargeUnit))
}
