package com.project.e_commerce.android.presentation.ui.screens.reelsScreen

import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.composable.composableScreen.public.VideoPlayer
import com.project.e_commerce.android.presentation.ui.composable.composableScreen.specific.reels.CommentItem
import com.project.e_commerce.android.presentation.ui.composable.composableScreen.specific.reels.RatingCard
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.GrayColor60
import com.project.e_commerce.android.presentation.ui.utail.PrimaryColor
import com.project.e_commerce.android.presentation.ui.utail.noRippleClickable
import com.project.e_commerce.android.presentation.viewModel.reelsScreenViewModel.ReelsScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReelsView(navController: NavHostController) {
    val viewModel: ReelsScreenViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    var isSelectedComments by remember { mutableStateOf(true) }
    var isSelectedRatings by remember { mutableStateOf(false) }

    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
    )
    val addToCartSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val shareLauncher: ManagedActivityResultLauncher<Intent, ActivityResult> =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    LaunchedEffect(state) {
        Log.d("STATE", "State size: ${state.size}")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        ModalBottomSheetLayout(
            sheetState = addToCartSheetState,
            sheetContent = {
                AddToCartBottomSheet(
                    onClose = {
                        coroutineScope.launch { addToCartSheetState.hide() }
                    },
                    productName = state.firstOrNull()?.contentDescription ?: "Sample Product",
                    productPrice = "$29.99",
                    onAddToCart = { color, size, quantity ->
                        // Logic to add to cart
                        coroutineScope.launch { addToCartSheetState.hide() }
                    }
                )
            },
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            sheetBackgroundColor = Color.White,
            sheetElevation = 8.dp
        ) {
            ModalBottomSheetLayout(
                modifier = Modifier.background(Color.Black),
                sheetState = modalBottomSheetState,
                sheetContent = {
                    BottomSheetContent(
                        state = state,
                        isSelectedComments = isSelectedComments,
                        isSelectedRatings = isSelectedRatings,
                        onCommentTabClick = {
                            isSelectedComments = true
                            isSelectedRatings = false
                        },
                        onRatingTabClick = {
                            isSelectedComments = false
                            isSelectedRatings = true
                        },
                        onCloseClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                            }
                        },
                        viewModel = viewModel
                    )
                },
                sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                sheetBackgroundColor = Color.White,
                sheetElevation = 8.dp
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    ReelsList(
                        onClickCommentButton = {
                            coroutineScope.launch {
                                modalBottomSheetState.show()
                            }
                        },
                        reelsList = state,
                        viewModel = viewModel,
                        onClickMoreButton = {
                            viewModel.onClickMoreButton(shareLauncher)
                        },
                        onClickCartButton = {
                            coroutineScope.launch {
                                addToCartSheetState.show()
                            }
                        }
                    )

                    ReelsTopHeader(
                        onClickSearch = { viewModel.onClickSearchButton(navController) },
                        modifier = Modifier.align(Alignment.TopCenter)
                    )
                }
            }
        }

    }
}


@Composable
fun ReelsTopHeader(
    onClickSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .statusBarsPadding(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            HeaderTab(
                text = "Explore",
                isSelected = true
            )
            HeaderTab(
                text = "Following",
                isSelected = false
            )
            HeaderTab(
                text = "For you",
                isSelected = false
            )
        }

        IconButton(
            onClick = onClickSearch,
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.3f),
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun HeaderTab(
    text: String,
    isSelected: Boolean
) {
    Text(
        text = text,
        color = if (isSelected) Color.White else Color.White.copy(alpha = 0.6f),
        fontSize = 16.sp,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReelsList(
    onClickCommentButton: () -> Unit,
    viewModel: ReelsScreenViewModel,
    onClickCartButton: () -> Unit,
    onClickMoreButton: () -> Unit,
    reelsList: List<Reels>
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { reelsList.size })
    val currentPage = pagerState.currentPage

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Box(modifier = Modifier.fillMaxSize()) {
            Log.i("PAGER_PAGE", page.toString())
            val isCurrentPage = page == currentPage
            var playbackStarted by remember { mutableStateOf(false) }

            VideoPlayer(
                uri = reelsList[page].video,
                isPlaying = isCurrentPage,
                onPlaybackStarted = {
                    playbackStarted = true
                }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.4f)
                            )
                        )
                    )
            )

            ReelContent(
                reel = reelsList[page],
                viewModel = viewModel,
                onClickCommentButton = onClickCommentButton,
                onClickMoreButton = onClickMoreButton,
                onClickCartButton = onClickCartButton,
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
fun ReelContent(
    reel: Reels,
    viewModel: ReelsScreenViewModel,
    onClickCommentButton: () -> Unit,
    onClickCartButton: () -> Unit,
    onClickMoreButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            UserInfo(reel = reel)
            Spacer(modifier = Modifier.height(8.dp))
            ReelDescription(description = reel.contentDescription)
            Spacer(modifier = Modifier.height(12.dp))
        }

        InteractionButtons(
            reel = reel,
            onClickLoveButton = { viewModel.onClackLoveReelsButton(reel.id) },
            onClickCommentButton = onClickCommentButton,
            onClickCartButton = onClickCartButton,
            onClickMoreButton = onClickMoreButton
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun UserInfo(reel: Reels) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            model = reel.userImage,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(2.dp, Color.White, CircleShape),
            contentDescription = "User Avatar"
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = reel.userName,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextButton(
            onClick = { },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Transparent,
                contentColor = Color.White
            ),
            modifier = Modifier
                .height(28.dp)
                .border(1.dp, Color.White, RoundedCornerShape(14.dp))
        ) {
            Text(
                text = "Follow +",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun ReelDescription(description: String) {
    Text(
        text = description,
        color = Color.White,
        fontSize = 14.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        lineHeight = 20.sp
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun InteractionButtons(
    reel: Reels,
    onClickLoveButton: () -> Unit,
    onClickCommentButton: () -> Unit,
    onClickCartButton: () -> Unit,
    onClickMoreButton: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        InteractionButton(
            icon = if (reel.love.isLoved) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            count = (reel.love.number + 10).toString(),
            tint = if (reel.love.isLoved) Color.Red else Color.White,
            onClick = onClickLoveButton
        )

        InteractionButton(
            icon = Icons.Default.Comment,
            count = reel.numberOfComments.toString(),
            onClick = onClickCommentButton
        )

        InteractionButton(
            icon = Icons.Default.ShoppingCart,
            count = reel.numberOfCart.toString(),
            onClick = onClickCartButton
        )

        InteractionButton(
            icon = Icons.Default.Share,
            count = "Share",
            onClick = onClickMoreButton
        )
    }
}

@Composable
fun InteractionButton(
    icon: ImageVector,
    count: String,
    tint: Color = Color.White,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.noRippleClickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = count,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetContent(
    state: List<Reels>,
    isSelectedComments: Boolean,
    isSelectedRatings: Boolean,
    onCommentTabClick: () -> Unit,
    onRatingTabClick: () -> Unit,
    onCloseClick: () -> Unit,
    viewModel: ReelsScreenViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.85f)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // indicator
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(40.dp)
                .height(4.dp)
                .background(Color.LightGray, RoundedCornerShape(2.dp))
        )
        Spacer(Modifier.height(16.dp))

        // Tabs and close
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Comments",
                    color = if (isSelectedComments) PrimaryColor else BlackColor80,
                    fontWeight = if (isSelectedComments) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { onCommentTabClick() }
                )
                Text(
                    text = "Ratings",
                    color = if (isSelectedRatings) PrimaryColor else BlackColor80,
                    fontWeight = if (isSelectedRatings) FontWeight.Bold else FontWeight.Normal,
                    modifier = Modifier.clickable { onRatingTabClick() }
                )
            }
            IconButton(onClick = onCloseClick) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        }

        Spacer(Modifier.height(12.dp))

        if (isSelectedComments) {
            CommentsContent(
                comments = state.firstOrNull()?.comments ?: emptyList(),
                newComment = state.firstOrNull()?.newComment?.comment ?: "",
                onCommentChange = { viewModel.onWriteNewComment(it) },
                onLoveComment = { commentId, reelsId ->
                    viewModel.onLoveComment(commentId, reelsId)
                },
                onClickAddComment = { }
            )
        } else {
            RatingsContent(
                ratings = state.firstOrNull()?.ratings ?: emptyList(),
                newRating = "", // ضع هنا لو لديك state في الـ ViewModel
                onRatingChange = {},
                onClickAddRating = {}
            )
        }
    }
}


@Composable
fun CommentsContent(
    comments: List<Comment>,
    newComment: String,
    onCommentChange: (String) -> Unit,
    onLoveComment: (String, String) -> Unit,
    onClickAddComment : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(comments) { comment ->
                    CommentItem(
                        userName = comment.userName,
                        comment = comment.comment,
                        time = comment.time,
                        isLoved = comment.isLoved,
                        numberOfLoved = 5,
                        isReplyShown = comment.isReplyShown,
                        onClick = {
                            onLoveComment(comment.id, comment.id)
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TextField(
                value = newComment,
                onValueChange = onCommentChange,
                placeholder = { Text("Add Comment") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(onClick = onClickAddComment) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}


@Composable
fun RatingsContent(
    ratings: List<Ratings>,
    newRating: String,
    onRatingChange: (String) -> Unit,
    onClickAddRating: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        // Summary box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "4.9 ⭐",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )
            Text(
                text = "(32 Rates)",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Spacer(Modifier.height(8.dp))

        // Ratings list
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 8.dp)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(ratings) { rating ->
                    RatingCard(
                        userName = rating.userName,
                        rateContent = rating.review,
                        rateNumber = rating.rate,
                        time = "12 h"
                    )
                    Spacer(Modifier.height(8.dp))
                }
            }
        }

        // Add Rate input fixed at bottom
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TextField(
                value = newRating,
                onValueChange = onRatingChange,
                placeholder = { Text("Add Rate") },
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(onClick = onClickAddRating) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null,
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}




@Composable
fun AddToCartBottomSheet(
    onClose: () -> Unit,
    productName: String,
    productPrice: String,
    productDescription: String = "Upgrade Your Wardrobe With This Premium Item — Combining Comfort, Style, And Durability.",
    rating: Double = 4.8,
    onAddToCart: (String, String, Int) -> Unit
) {
    var selectedColor by remember { mutableStateOf("Orange") }
    var selectedSize by remember { mutableStateOf("S") }
    var quantity by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Close Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onClose) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_exit),
                    contentDescription = "Close",
                    tint = BlackColor80
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Product Name
        Text(
            text = productName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rating
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .background(Color(0xFFFFF8E1), RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text("$rating", color = Color(0xFFFFC107), fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = productDescription,
            color = Color.Gray,
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Size Selection
        Text("Select Size :", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("XS", "S", "L", "M", "XL").forEach { size ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selectedSize == size) Color(0xFF4CAF50) else Color(0xFFBDBDBD))
                        .clickable { selectedSize = size }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(size, color = Color.White, fontSize = 13.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Color Selection
        Text("Select Color :", fontWeight = FontWeight.Bold, fontSize = 15.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("Orange", "Blue", "Yellow", "Green", "Purple").forEach { color ->
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(
                            when (color) {
                                "Orange" -> Color(0xFFFF9800)
                                "Blue" -> Color(0xFF1565C0)
                                "Yellow" -> Color(0xFFFFEB3B)
                                "Green" -> Color(0xFF37474F)
                                "Purple" -> Color(0xFF9C27B0)
                                else -> Color.Gray
                            }
                        )
                        .border(
                            width = if (selectedColor == color) 2.dp else 0.dp,
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clickable { selectedColor = color }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Price
        Text(
            "$productPrice$",
            color = Color(0xFFFF6F00),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quantity Selection
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .border(1.dp, Color(0xFFBDBDBD), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                IconButton(onClick = { if (quantity > 0) quantity-- }) {
                    Text("-", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0B74DA))
                }
                Text(
                    "$quantity",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
                IconButton(onClick = { quantity++ }) {
                    Text("+", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0B74DA))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add to Cart Button
        Button(
            onClick = { onAddToCart(selectedColor, selectedSize, quantity) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00))
        ) {
            Text(
                text = "Add to Cart",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}


@Composable
fun ColorOption(color: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(
                color = when (color) {
                    "Red" -> Color.Red
                    "Blue" -> Color.Blue
                    "Green" -> Color.Green
                    else -> Color.Gray
                },
                shape = CircleShape
            )
            .border(2.dp, if (isSelected) PrimaryColor else Color.Transparent, CircleShape)
            .noRippleClickable(onClick)
    )
}

@Composable
fun SizeOption(size: String, isSelected: Boolean, onClick: () -> Unit) {
    Text(
        text = size,
        modifier = Modifier
            .border(1.dp, if (isSelected) PrimaryColor else Color.Gray, RoundedCornerShape(4.dp))
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .noRippleClickable(onClick),
        color = if (isSelected) PrimaryColor else Color.Black,
        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun ReelsViewPreview() {
    ReelsView(navController = rememberNavController())
}