package com.project.e_commerce.android.presentation.ui.composable.composableScreen.specific.reels

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.achiver.presentation.ui.composable.spacerComposable.SpacerVerticalSmall
import com.example.achiver.presentation.ui.composable.spacerComposable.SpacerVerticalTiny
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.composable.spacerComposable.SpacerHorizontalSmall
import com.project.e_commerce.android.presentation.ui.composable.spacerComposable.SpacerHorizontalTiny
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.Comment
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.PrimaryGray
import com.project.e_commerce.android.presentation.ui.utail.SecondaryColor
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.largeUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.smallFontSize
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.tinyFontSize
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.tinyUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.titleFontSize
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.xLargeUnit
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.xSmallFontSize
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication.xSmallUnit

@Composable
fun CommentItem(
    userName: String,
    comment: String,
    time: String,
    isLoved: Boolean,
    isReplyShown: Boolean,
    reply: List<Comment> = emptyList(),
    numberOfLoved: Int,
    onClick: () -> Unit
) {
    var localIsLoved by remember { mutableStateOf(isLoved) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(SecondaryColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.first().toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(Modifier.weight(1f)) {
                Text(userName, fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(comment, fontSize = 13.sp, color =BlackColor80 )
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(time, fontSize = 11.sp, color = Color.Gray)
                    Spacer(Modifier.width(18.dp))
                    Text("Reply", fontSize = 11.sp, color = Color.Gray)
                }
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(
                        if (localIsLoved) R.drawable.ic_heart_checked else R.drawable.ic_love_un_checked
                    ),
                    contentDescription = null,
                    tint = if (localIsLoved) Color.Red else Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            localIsLoved = !localIsLoved
                            onClick()
                        }
                )
                Text(numberOfLoved.toString(), fontSize = 11.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
