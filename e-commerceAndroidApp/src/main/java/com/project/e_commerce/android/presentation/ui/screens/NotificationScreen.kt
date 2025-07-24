package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.common.collect.Multimaps.index
import com.project.e_commerce.android.R

data class NotificationItem(
    val title: String,
    val desc: String,
    val time: String,
    var isRead: Boolean = false
)


@Composable
fun NotificationScreen(navController: NavHostController) {

    val initialNotifications = listOf(
        NotificationItem("Order Shipped", "Your order #1325 is on the way. Expected delivery: Thursday.", "22feb ,10:00 am", isRead = false),
        NotificationItem("Item Left in Cart", "Smart Watch X3\" is still waiting in your cart. Check out before it’s gone!", "22feb ,10:00 am", isRead = true),
        NotificationItem("Wishlist Discount", "Wireless Earbuds Pro\" is now 20% OFF — just for you!", "22feb ,10:00 am", isRead = false)
    )
    val notifications = remember { mutableStateListOf<NotificationItem>().apply { addAll(initialNotifications) } }

    val todayNotifications = remember { mutableStateListOf(
        NotificationItem("Order Shipped", "Your order #1325 is on the way. Expected delivery: Thursday.", "22feb ,10:00 am", isRead = false),
        NotificationItem("Wishlist Discount", "Wireless Earbuds Pro\" is now 20% OFF — just for you!", "21feb ,2:00 pm", isRead = false),
        NotificationItem("Item Left in Cart", "Smart Watch X3\" is still waiting in your cart. Check out before it’s gone!", "22feb ,10:00 am", isRead = true),
        NotificationItem("Wishlist Discount", "Wireless Earbuds Pro\" is now 20% OFF — just for you!", "21feb ,2:00 pm", isRead = false),
        ) }
    val yesterdayNotifications = remember { mutableStateListOf(
        NotificationItem("Order Shipped", "Your order #1325 is on the way. Expected delivery: Thursday.", "22feb ,10:00 am", isRead = true),
        NotificationItem("Item Left in Cart", "Smart Watch X3\" is still waiting in your cart. Check out before it’s gone!", "22feb ,10:00 am", isRead = false),
        NotificationItem("Wishlist Discount", "Wireless Earbuds Pro\" is now 20% OFF — just for you!", "21feb ,2:00 pm", isRead = true)
    ) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            androidx.compose.material3.IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (-20).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFF0066CC),
                    modifier = Modifier.padding(10.dp)
                )
            }
            Text(
                text = "Notification",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        if (notifications.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(Color(0xFF1B7ACE), shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_no_notification),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "NO NOTIFICATIONS",
                        color = Color.Black,
                        fontWeight = FontWeight.Black,
                        fontSize = 16.sp
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Today", fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            todayNotifications.forEachIndexed { index, notification ->
                NotificationCard(notification) {
                    if (!notification.isRead) {
                        todayNotifications[index] = notification.copy(isRead = true)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Yesterday", fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(bottom = 8.dp))
            yesterdayNotifications.forEachIndexed { index, notification ->
                NotificationCard(notification) {
                    if (!notification.isRead) {
                        yesterdayNotifications[index] = notification.copy(isRead = true)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "See all",
                color = Color(0xFF1B7ACE),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun NotificationCard(notification: NotificationItem, onClick: () -> Unit) {
    val backgroundColor = if (notification.isRead) Color(0xFFF2F2F2) else Color(0xFFECF6FF)
    val borderColor = if (notification.isRead) Color.Transparent else Color(0xFF0066CC)
    val titleColor = if (notification.isRead) Color(0xFF1B7ACE) else Color(0xFF0066CC)
    val fontWeight = if (notification.isRead) FontWeight.Normal else FontWeight.Bold

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .border(
                width = if (notification.isRead) 0.dp else 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(enabled = !notification.isRead) { onClick() }
            .padding(12.dp)
            .padding(bottom = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_bag),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .size(32.dp)
                .padding(end = 8.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(notification.title, color = titleColor, fontWeight = fontWeight, fontSize = 14.sp)
                Text(notification.time, color = Color.Gray, fontSize = 10.sp)
            }
            Text(notification.desc, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(top = 4.dp))
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNotificationScreen() {
    val navController = rememberNavController()
    NotificationScreen(navController = navController)
}
