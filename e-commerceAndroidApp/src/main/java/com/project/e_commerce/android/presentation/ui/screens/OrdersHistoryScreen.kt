package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R


// OrdersHistoryScreen.kt
@Composable
fun OrdersHistoryScreen(navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        "All" to 12,
        "Completed" to 6,
        "Pending" to 4,
        "Canceled" to 2
    )


    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
    ) {

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
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

            androidx.compose.material.Text(
                text = "Orders History",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        OrdersTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it }
        )

        Spacer(modifier = Modifier.height(8.dp))


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(50.dp)
            )
            Text(
                text = "Item",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Status",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(125.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Total",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.Center
            )

        }

        Spacer(modifier = Modifier.height(8.dp))



        Column {
            repeat(10) {
                OrderItem(status = when (it % 4) {
                    0 -> "Pending" to Color.Yellow
                    1 -> "Delivered" to Color.Green
                    2 -> "Canceled" to Color.Red
                    else -> "Delivered" to Color.Green
                })
            }
        }
    }
}

/*
@Composable
fun OrderItem(
    orderId: String = "NEGE0054632456",
    productImage: Int = R.drawable.perfume4,
    title: String = "Tom Ford Black Orchid",
    description: String = "Black Orchid Eau de Parfum opens with aphrodisiac black truffle and sparkling pr...",
    status: Pair<String, Color>,
    date: String = "on Tuesday, 13th Dec, 2025",
    price: String = "100 $",
    onUploadVideo: () -> Unit = {},
    onReviewProduct: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(4.dp, RoundedCornerShape(18.dp))
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE9E9E9), RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column {
            // Order ID
            Text(
                buildString {
                    append("Order ID ")
                    append(orderId)
                },
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFFB0B5C0)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                // صورة المنتج (على اليسار)
                Image(
                    painter = painterResource(id = productImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(10.dp))

                // الأعمدة على اليمين
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF181D23),
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFFB0B5C0),
                        fontSize = 13.sp,
                        maxLines = 2
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    // الحالة/التاريخ/السعر
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = status.first,
                                color = status.second,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = date,
                                color = Color(0xFF181D23),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = price,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF6F00),
                            fontSize = 16.sp
                        )
                    }
                }
            }

            // الأزرار فقط عند Delivered
            if (status.first == "Delivered") {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Share your experience",
                    color = Color(0xFF181D23),
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
                ) {
                    OutlinedButton(
                        onClick = onReviewProduct,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color(0xFFFF6F00)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color(0xFFFFF3E7),
                            contentColor = Color(0xFFFF6F00)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp), // Padding للزر نفسه
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 3.dp,
                            pressedElevation = 2.dp
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                            contentDescription = "Review Product",
                            tint = Color(0xFFFF6F00),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "Review Product",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    OutlinedButton(
                        onClick = onUploadVideo,
                        shape = RoundedCornerShape(10.dp),
                        border = BorderStroke(1.dp, Color(0xFF176DBA)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = Color(0xFFEFF6FA),
                            contentColor = Color(0xFF176DBA)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(45.dp),
                        contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 3.dp,
                            pressedElevation = 2.dp
                        )
                    )  {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_video_camera),
                            contentDescription = "Upload Video",
                            tint = Color(0xFF176DBA),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            "Upload Video",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}*/



@Composable
fun OrderItem(status: Pair<String, Color>, onUploadVideo: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // صورة المنتج
        Image(
            painter = painterResource(id = R.drawable.perfume3),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(8.dp))

        // اسم المنتج + زر الفيديو إن وجد
        Column(
            modifier = Modifier.width(130.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hanger Shirt",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
            // زر رفع فيديو يظهر فقط مع Delivered
            if (status.first == "Delivered") {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .border(1.dp, Color(0xFF176DBA), RoundedCornerShape(26))
                        .clip(RoundedCornerShape(16))
                        .clickable { onUploadVideo() }
                        .background(Color(0xFFEFF6FA))
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_video_camera),
                        contentDescription = "Upload Video",
                        tint = Color(0xFF176DBA),
                        modifier = Modifier.size(18.dp),
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Upload video",
                        color = Color(0xFF176DBA),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                }
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = status.first, color = status.second, fontWeight = FontWeight.Bold)
            Text("5 days left", fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "100 $",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
        Spacer(modifier = Modifier.width(8.dp))
    }
}



@Composable
fun OrdersTabRow(
    tabs: List<Pair<String, Int>>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.White,
        edgePadding = 4.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                height = 2.dp,
                color = Color(0xFF0066CC)
            )
        }
    ) {
        tabs.forEachIndexed { index, (title, count) ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = "$title ($count)",
                        color = if (selectedTabIndex == index) Color.Black else Color.Gray
                    )
                }
            )
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewOrdersHistoryScreen() {
    val navController = rememberNavController()
    OrdersHistoryScreen(navController = navController)
}
