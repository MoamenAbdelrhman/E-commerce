package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
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
        "Pending" to 4,
        "Completed" to 6,
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
                .height(48.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back",
                    modifier = Modifier.fillMaxSize()
                        .padding(4.dp)
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

        Spacer(modifier = Modifier.height(16.dp))


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
                    else -> "Pending" to Color.Yellow
                })
            }
        }
    }
}

@Composable
fun OrderItem(status: Pair<String, Color>) {
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

        Text(
            text = "Hanger Shirt",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

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
