package com.project.e_commerce.android.presentation.ui.composable.composableScreen.specific.reels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.composable.composableScreen.public.StarRating
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.SecondaryColor

@Composable
fun RatingCard(
    userName: String,
    rateContent: String,
    rateNumber: Int,
    time: String,
) {
    var isLoved by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(SecondaryColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.first().toString(),
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(userName, color =Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text(rateContent, fontSize = 12.sp, color = BlackColor80)
                Spacer(modifier = Modifier.height(8.dp))
                StarRating(rating = rateNumber, onRatingChange = {})
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(time, fontSize = 12.sp, color = Color.Gray)
            Icon(
                painter = painterResource(if (isLoved) R.drawable.ic_heart_checked else R.drawable.ic_love_un_checked),
                contentDescription = null,
                tint = if (isLoved) Color.Red else Color.Gray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable { isLoved = !isLoved }
            )
        }
    }
}


