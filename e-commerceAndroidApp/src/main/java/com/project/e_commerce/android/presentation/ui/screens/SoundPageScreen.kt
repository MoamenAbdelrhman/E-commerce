package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity


@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class)
@Composable
fun SoundPageScreen(navController: NavHostController) {

    var isFavorite by remember { mutableStateOf(false) }

    val soundImage = painterResource(id = R.drawable.electronics) // أو أي صورة مناسبة
    val soundTitle = "Original Sound"
    val soundAuthor = "kfbx"
    val postsCount = "5K posts"

    val posts = listOf(
        SoundPost(painterResource(id = R.drawable.img1), isImage = false),
        SoundPost(painterResource(id = R.drawable.img2), isImage = true),
        SoundPost(painterResource(id = R.drawable.img3), isImage = false),
        SoundPost(painterResource(id = R.drawable.img4), isImage = true),
        SoundPost(painterResource(id = R.drawable.img5), isImage = false),
        SoundPost(painterResource(id = R.drawable.img1), isImage = false),
        SoundPost(painterResource(id = R.drawable.img2), isImage = true),
        SoundPost(painterResource(id = R.drawable.img3), isImage = false),
        SoundPost(painterResource(id = R.drawable.img4), isImage = true),
        SoundPost(painterResource(id = R.drawable.img5), isImage = false),
//        SoundPost(painterResource(id = R.drawable.img1), isImage = false),
//        SoundPost(painterResource(id = R.drawable.img2), isImage = true),
//        SoundPost(painterResource(id = R.drawable.img3), isImage = false),
//        SoundPost(painterResource(id = R.drawable.img4), isImage = true),
//        SoundPost(painterResource(id = R.drawable.img5), isImage = false),
    )

    val headerHeight = 56.dp

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

        // Header ثابت بالأعلى
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .background(Color.White)
        ) {
            // Header (Box Music)
            Box(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp, end = 10.dp, bottom = 6.dp)
                    .fillMaxSize()
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
                    text = "Music",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF0066CC),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight)
                .verticalScroll(rememberScrollState())
        ) {

            SoundHeaderSection(
                soundImage = soundImage,
                soundTitle = soundTitle,
                soundAuthor = soundAuthor,
                postsCount = postsCount
            )
            /*   Row(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 16.dp, vertical = 6.dp),
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Box(
                       modifier = Modifier
                           .size(52.dp)
                           .clip(RoundedCornerShape(9.dp))
                           .background(Color.Black)
                           .clickable { isPlaying = !isPlaying }
                   ) {
                       Image(
                           painter = soundImage,
                           contentDescription = null,
                           contentScale = ContentScale.Crop,
                           modifier = Modifier.fillMaxSize()
                       )
                       // أيقونة Play وسط الصورة
                       Box(
                           modifier = Modifier
                               .size(52.dp)
                               .align(Alignment.Center)
                               .background(Color(0x66000000), RoundedCornerShape(9.dp))
                       ) {
                           Icon(
                               painter = painterResource(
                                   id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                               ),
                               contentDescription = null,
                               tint = Color.White,
                               modifier = Modifier
                                   .size(16.dp)
                                   .align(Alignment.Center)
                           )
                       }
                   }

                   Spacer(modifier = Modifier.width(13.dp))
                   Column(
                       verticalArrangement = Arrangement.Center
                   ) {
                       Text(
                           text = soundTitle,
                           fontWeight = FontWeight.Bold,
                           fontSize = 19.sp,
                           color = Color.Black
                       )
                       Spacer(Modifier.height(2.dp))
                       Row(verticalAlignment = Alignment.CenterVertically) {
                           Text(
                               text = soundAuthor,
                               fontSize = 14.sp,
                               color = Color(0xFF757575),
                               fontWeight = FontWeight.Medium
                           )
                           Icon(
                               painter = painterResource(id = R.drawable.ic_arrow_forward), // أيقونة سهم صغيرة (يفضل vector)
                               contentDescription = null,
                               tint = Color(0xFF757575),
                               modifier = Modifier
                                   .size(14.dp)
                                   .padding(start = 2.dp, end = 1.dp)
                           )
                       }
                       Spacer(Modifier.height(2.dp))
                       Text(
                           text = postsCount,
                           fontSize = 13.sp,
                           color = Color(0xFFB0B5C0),
                           fontWeight = FontWeight.Medium
                       )
                   }
               }*/

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp)
                    .height(42.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = if (isFavorite) Color(0xFFffc107) else Color(0xFFF7F7F7),
                    contentColor = if (isFavorite) Color.White else Color.Black
                ),
                border = BorderStroke(1.dp, if (isFavorite) Color(0xFFffc107) else Color(0xFFF7F7F7))
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = if (isFavorite) Color.White else Color.Black
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    "Add to Favorites",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(6.dp))


            val configuration = LocalConfiguration.current
            val density = LocalDensity.current
            val columns = 3
            val itemSpacing = 6.dp

            val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }
            val itemSpacingPx = with(density) { itemSpacing.toPx() }
            val imageSizePx = (screenWidthPx - itemSpacingPx * (columns + 2)) / columns
            val imageSizeDp = with(density) { imageSizePx.toDp() }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
                verticalArrangement = Arrangement.spacedBy(itemSpacing)
            ) {
                posts.forEach { post ->
                    Box(
                        modifier = Modifier
                            .size(imageSizeDp)
                            .clip(RoundedCornerShape(7.dp))
                            .background(Color(0xFFF2F2F2))
                    ) {
                        Image(
                            painter = post.painter,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        if (post.isImage) {
                            Icon(
                                painter = painterResource(id = R.drawable.images),
                                contentDescription = "Image",
                                tint = Color.White,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .size(24.dp)
                                    .align(Alignment.TopEnd)
                                    .background(Color(0xAA222222), shape = CircleShape)
                                    .padding(4.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(68.dp))
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 20.dp, start = 18.dp, end = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { /* Add to Story */ },
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color(0xFF0066CC),
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.elevation(2.dp)
            ) {
                Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(20.dp),
                    tint = Color.White)
                Spacer(modifier = Modifier.width(7.dp))
                Text("Share", fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { /* Use Sound */ },
                modifier = Modifier.weight(1f).height(42.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFF6F00),
                    contentColor = Color.White
                )
            ) {
                Icon(Icons.Default.MusicNote, contentDescription = null, modifier = Modifier.size(20.dp),tint = Color.White)
                Spacer(modifier = Modifier.width(7.dp))
                Text("Use sound", fontSize = 15.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

data class SoundPost(
    val painter: Painter,
    val isImage: Boolean
)





@Composable
fun SoundHeaderSection(
    soundImage: Painter,
    soundTitle: String,
    soundAuthor: String,
    postsCount: String,
) {
    var isPlaying by remember { mutableStateOf(false) }
    var rotation by remember { mutableStateOf(0f) }

    val infiniteTransition = rememberInfiniteTransition(label = "rotate")
    val animatedRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "spin"
    )

    val actualRotation = if (isPlaying) animatedRotation else 0f

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radiusStep = size.minDimension / 12f
                val ringColor = Color(0xFF00101f)
                val strokeColor = Color(0xFF224362)

                for (i in 6 downTo 1) {
                    val outer = radiusStep * i
                    val inner = outer - 2f
                    drawCircle(color = ringColor, radius = outer)
                    drawCircle(color = strokeColor, radius = inner, style = Stroke(width = 2f))
                }
            }

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color(0xFF007BFF), Color(0xFF0056D2))
                        )
                    )
                    .padding(5.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            rotationZ = actualRotation
                        }
                        .clip(CircleShape)
                        .background(Color.White)
                        .clickable { isPlaying = !isPlaying },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = soundImage,
                        contentDescription = "Sound image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                    )
                }

                // الأيقونة ثابتة غير قابلة للدوران
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.35f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (isPlaying) R.drawable.ic_pause else R.drawable.ic_play
                        ),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(text = soundTitle, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = soundAuthor, fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(text = postsCount, fontSize = 13.sp, color = Color(0xFFB0B5C0), fontWeight = FontWeight.Medium)
    }
}


@Preview(showBackground = true, widthDp = 380, heightDp = 700)
@Composable
fun PreviewSoundPageScreen() {
    SoundPageScreen(
        navController = rememberNavController(),
    )
}
