package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.reelsScreen.ReelsTopHeader
import com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel.ReelsScreenViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExploreScreenWithHeader(
    items: List<ExploreItem>,
    navController: NavHostController
) {
    var selectedTab by remember { mutableStateOf("Explore") }
    val headerHeight = 60.dp

    Box(Modifier.fillMaxSize()) {
        // الشبكة القابلة للتمرير مع paddingTop = headerHeight
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight, bottom = 40.dp)
                .verticalScroll(rememberScrollState()) // لو ExploreMasonryGrid لا يدعم scroll
        ) {
            ExploreMasonryGrid(items)
            Spacer(modifier = Modifier.height(50.dp))
        }

        // Header ثابت بالأعلى دائماً
        ReelsTopHeader(
            onClickSearch = { navController.navigate(Screens.ReelsScreen.SearchReelsAndUsersScreen.route) },
            selectedTab = selectedTab,
            onTabChange = { tab ->
                if (tab == "For you" || tab == "Following") {
                    navController.popBackStack()
                } else {
                    selectedTab = tab
                }
            },
            onClickExplore = { selectedTab = "Explore" },
            headerStyle = HeaderStyle.WHITE_BLACK_TEXT,
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .zIndex(1f)
        )
    }
}

@Composable
fun ExploreGridItem(
    imageRes: Int,
    isVideo: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        androidx.compose.foundation.Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )

        // أيقونة البيل السوداء
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .background(Color.Black.copy(alpha = 0.95f), shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                imageVector = if (isVideo) Icons.Default.Videocam else Icons.Default.Image,
                contentDescription = if (isVideo) "Video" else "Image",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
@Composable
fun ExploreMasonryGrid(
    items: List<ExploreItem>,
    modifier: Modifier = Modifier
) {
    // وزع العناصر على 3 أعمدة
    val columns = List(3) { mutableListOf<ExploreItem>() }
    items.forEachIndexed { index, item ->
        columns[index % 3].add(item)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        columns.forEach { columnItems ->
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                columnItems.forEach { item ->
                    val randomHeight = listOf(130.dp, 170.dp, 120.dp).random()
                    ExploreGridItem(
                        imageRes = item.imageRes,
                        isVideo = item.isVideo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(randomHeight)
                    )
                }
            }
        }
    }
}

enum class HeaderStyle {
    TRANSPARENT_WHITE_TEXT,
    WHITE_BLACK_TEXT
}


data class ExploreItem(
    val imageRes: Int,
    val isVideo: Boolean
)

val exploreItems = listOf(
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img2, true),
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img2, false),
    ExploreItem(R.drawable.img3, true),
    ExploreItem(R.drawable.perfume1, true),
    ExploreItem(R.drawable.img3, true),
    ExploreItem(R.drawable.perfume1, false),
    ExploreItem(R.drawable.img3, false),
    ExploreItem(R.drawable.perfume2, false),
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img3, true),
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img2, true),
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img2, false),
    ExploreItem(R.drawable.img3, true),
    ExploreItem(R.drawable.perfume1, true),
    ExploreItem(R.drawable.img3, true),
    ExploreItem(R.drawable.perfume1, false),
    ExploreItem(R.drawable.img3, false),
    ExploreItem(R.drawable.perfume2, false),
    ExploreItem(R.drawable.img4, false),
    ExploreItem(R.drawable.img3, true)
)


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewExploreScreenWithHeader() {
    val navController = rememberNavController()
    ExploreScreenWithHeader(items = exploreItems, navController = navController)
}