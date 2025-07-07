package com.project.e_commerce.android.presentation.ui.composable.composableScreen.public

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.utail.GrayColor80
import com.project.e_commerce.android.presentation.ui.utail.PrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.largeUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.tinyUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.xxLargeUnit
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable

@Composable
fun StarRating(
    rating: Int,
    onRatingChange: (Int) -> Unit,
    isClickable: Boolean = false
) {
    Row(
        modifier = if (isClickable) Modifier.wrapContentSize() else Modifier.fillMaxWidth()
    ) {
        for (i in 1..5) {
            Icon(
                painter = painterResource(
                    id =  R.drawable.ic_star),
                contentDescription = "Star $i",
                modifier = Modifier
                    .size(
                        if (isClickable) xxLargeUnit else largeUnit
                    )
                    .padding(horizontal = tinyUnit)
                    .noRippleClickable {
                        onRatingChange(i)
                    },
                tint = if (i <= rating) PrimaryColor else GrayColor80
            )
        }
    }
}
