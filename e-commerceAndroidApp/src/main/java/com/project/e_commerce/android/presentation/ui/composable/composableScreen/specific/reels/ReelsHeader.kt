package com.project.e_commerce.android.presentation.ui.composable.composableScreen.specific.reels

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable

@Composable
fun ReelsHeader(
    onClickSearch: ()-> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("ريلز", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 21.sp)
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            tint = Color.White,
            modifier = Modifier.size(24.dp).noRippleClickable { onClickSearch() },
            contentDescription = null
        )
    }
}
