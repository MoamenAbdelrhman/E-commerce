package com.project.e_commerce.android.presentation.ui.screens

import android.R.attr.rating
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.utils.GammaEvaluator.evaluate
import com.project.e_commerce.android.R
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import android.util.Size
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.project.e_commerce.android.presentation.ui.navigation.Screens




@Composable
fun TrackOrderScreen(navController: NavHostController) {

    // بيانات المنتج هنا ثابتة أو لاحقًا من ViewModel
    val productName = "Hanger Shirt"
    val productDesc = "Slim Fit, Men's Fashion"
    val productPrice = "$100.00"
    val productImage = R.drawable.img4


    var rating by remember { mutableStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var isDelivered by remember { mutableStateOf(true) }
    val orderStep = 2 // أو من ViewModel: 0 = Order Placed, 1 = On the Way, 2 = Delivered
    val context = LocalContext.current
    var selectedMediaUri by remember { mutableStateOf<Uri?>(null) }
    var videoThumbnail by remember { mutableStateOf<Bitmap?>(null) }

    val pickMediaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedMediaUri = uri
        videoThumbnail = null

        uri?.let {
            if (it.toString().contains("video")) {
                // استخدم MediaMetadataRetriever لدعم جميع الأجهزة
                val retriever = MediaMetadataRetriever()
                try {
                    retriever.setDataSource(context, uri)
                    val bitmap = retriever.getFrameAtTime(2)
                    videoThumbnail = bitmap
                } catch (_: Exception) {
                    videoThumbnail = null
                } finally {
                    retriever.release()
                }
            }
        }
    }

    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .imePadding()
            .padding(horizontal = 16.dp, vertical = 16.dp)
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
                text = "Orders Track ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        // Product Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp, bottom = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(18.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = productImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(18.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    productName,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF172D3F)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    productDesc,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    maxLines = 1
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                productPrice,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6F00),
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Truck Icon & Title
        Icon(
            painter = painterResource(id = R.drawable.ic_truck),
            contentDescription = null,
            tint = Color(0xFF2176F3),
            modifier = Modifier.size(54.dp)
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            "Track Your Order",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 29.sp,
            color = Color(0xFF172D3F)
        )
        Spacer(modifier = Modifier.height(30.dp))

        // Progress Steps
        OrderProgressBarPerfect(currentStep = orderStep)
        Spacer(modifier = Modifier.height(42.dp))


        Button(
            onClick = {
                isDelivered = true
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 48.dp)
                .shadow(6.dp, RoundedCornerShape(24.dp))
        ) {
            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(12.dp))
            Text("Confirm Delivery", fontSize = 19.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }


        // --- Review Section (if Delivered) ---
        if (isDelivered) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                "How was your experience?",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 21.sp,
                color = Color(0xFF172D3F)
            )
            Spacer(modifier = Modifier.height(12.dp))
            // --- Stars Row ---
            Row {
                for (i in 1..5) {
                    Icon(
                        imageVector = if (i <= rating) Icons.Default.Star else Icons.Default.StarBorder,
                        contentDescription = null,
                        tint = Color(0xFFFF6F00),
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { rating = i }
                    )
                }
            }
            Spacer(modifier = Modifier.height(15.dp))

            // ----------- هنا ضَع كتلة المعاينة -----------
            if (selectedMediaUri != null) {
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .padding(top = 12.dp, bottom = 6.dp)
                ) {
                    if (videoThumbnail != null) {
                        Image(
                            bitmap = videoThumbnail!!.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(14.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(14.dp))
                        )
                    } else {
                        Image(
                            painter = rememberAsyncImagePainter(selectedMediaUri),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(14.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(14.dp))
                        )
                    }
                    IconButton(
                        onClick = {
                            selectedMediaUri = null
                            videoThumbnail = null
                        },
                        modifier = Modifier
                            .size(34.dp)
                            .align(Alignment.TopEnd)
                            .offset(x = 10.dp, y = (-10).dp)
                            .background(Color(0xAA222222), CircleShape)
                            .border(1.dp, Color.White.copy(alpha = 0.7f), CircleShape)
                            .zIndex(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Remove Media",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            // حقل الإدخال وزر إضافة صورة/فيديو كما هو
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = reviewText,
                    onValueChange = { reviewText = it },
                    placeholder = { Text("Write a review", color = Color.Gray) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(90.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF2176F3),
                        unfocusedBorderColor = Color(0xFFBCC3C7),
                        backgroundColor = Color.White
                    ),
                    maxLines = 3
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = {
                        pickMediaLauncher.launch("*/*")
                    },
                    modifier = Modifier.size(42.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_photo),
                        contentDescription = "Pick Media",
                        tint = Color(0xFF176DBA),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(28.dp))
            Button(
                onClick = { /* إرسال التقييم */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 12.dp)
                    .shadow(6.dp, RoundedCornerShape(24.dp))
            ) {
                Text("Submit Review", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color.White)
            }
        }

    }
}


@Composable
fun OrderProgressBar2(currentStep: Int) {
    val orange = Color(0xFFFF9800)
    val blue = Color(0xFF2176F3)
    val inActiveColor = Color(0xFFBCC3C7)
    val labels = listOf("Order Placed", "On the Way", "Delivery")
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // الدائرة الأولى (Order Placed)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(19.dp)
                    .background(if (currentStep >= 0) orange else inActiveColor, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                labels[0],
                color = if (currentStep >= 0) Color(0xFF172D3F) else inActiveColor,
                fontWeight = if (currentStep == 0) FontWeight.Bold else FontWeight.Medium,
                fontSize = 14.sp
            )
        }
        // الخط بين الأولى والثانية (Gradient)
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(5.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(orange, blue)
                    ),
                    shape = RoundedCornerShape(2.dp)
                )
        )

        // الدائرة الثانية (On the Way)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(19.dp)
                    .background(if (currentStep >= 1) blue else inActiveColor, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                labels[1],
                color = if (currentStep >= 1) Color(0xFF172D3F) else inActiveColor,
                fontWeight = if (currentStep == 1) FontWeight.Bold else FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        // الخط بين الثانية والثالثة (ثابت رمادي)
        Box(
            modifier = Modifier
                .width(60.dp)
                .height(5.dp)
                .background(inActiveColor, shape = RoundedCornerShape(2.dp))
        )

        // الدائرة الثالثة (Delivery)
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(19.dp)
                    .background(inActiveColor, CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                labels[2],
                color = inActiveColor,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}



@Composable
fun ProductHeader() {

    // يمكنك تعديل هذه القيم لاحقًا أو ربطها بـ ViewModel
    val productName = "Hanger Shirt"
    val productDesc = "Slim Fit, Men's Fashion"
    val productPrice = "$100.00"
    val productImage = R.drawable.img4

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, end = 8.dp, bottom = 18.dp), // بداية متوازية مع الايقونات
        verticalAlignment = Alignment.CenterVertically
    ) {
        // الصورة داخل بوكس
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(18.dp)), // خلفية فاتحة مثل أيقونات الهيدر
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = productImage),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp) // حجم الصورة أصغر قليلًا من البوكس
                    .clip(RoundedCornerShape(18.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        // اسم ووصف المنتج
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                productName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF172D3F)
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                productDesc,
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 1
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // السعر
        Text(
            productPrice,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF6F00),
            fontSize = 18.sp
        )
    }
}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun OrderProgressBarPerfect(currentStep: Int) {
    val orange = Color(0xFFFF9800)
    val blue = Color(0xFF2176F3)
    val inActiveColor = Color(0xFFBCC3C7)
    val labels = listOf("Order Placed", "On the Way", "Delivery")
    val circleSize = 19.dp
    val lineHeight = 4.dp
    val backgroundColor = Color.White
    val textVerticalSpacing = 8.dp

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        val density = LocalDensity.current
        val stepCount = labels.size
        val boxWidthPx = with(density) { maxWidth.toPx() }
        val circleSizePx = with(density) { circleSize.toPx() }
        val spacePx = (boxWidthPx - (circleSizePx * stepCount)) / (stepCount - 1)
        val yCenter = circleSizePx / 2

        // 1. رسم الخط والدوائر
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(circleSize)
        ) {
            // الخط المتصل
            drawLine(
                brush = Brush.horizontalGradient(
                    colors = listOf(orange, blue, inActiveColor)
                ),
                start = Offset(circleSizePx / 2, yCenter),
                end = Offset(size.width - circleSizePx / 2, yCenter),
                strokeWidth = lineHeight.toPx(),
                cap = StrokeCap.Round
            )
            // الدوائر
            for (i in 0 until stepCount) {
                val x = circleSizePx / 2 + i * (circleSizePx + spacePx)
                when (i) {
                    0 -> drawCircle(
                        color = orange,
                        radius = circleSizePx / 2,
                        center = Offset(x, yCenter)
                    )
                    1 -> drawCircle(
                        color = blue,
                        radius = circleSizePx / 2,
                        center = Offset(x, yCenter)
                    )
                    2 -> {
                        drawCircle(
                            color = backgroundColor,
                            radius = circleSizePx / 2,
                            center = Offset(x, yCenter)
                        )
                        drawCircle(
                            color = inActiveColor,
                            radius = circleSizePx / 2,
                            center = Offset(x, yCenter),
                            style = Stroke(width = 2.dp.toPx())
                        )
                    }
                }
            }
        }

        // النصوص تحت الدوائر بدقة (باستخدام absoluteOffset)
        labels.forEachIndexed { i, label ->
            val x = circleSizePx / 2 + i * (circleSizePx + spacePx)
            Box(
                modifier = Modifier
                    .absoluteOffset {
                        IntOffset(
                            x = (x - 38.dp.toPx()).toInt(), // 38.dp هو نصف عرض Box النص (76dp)
                            y = circleSize.roundToPx() + textVerticalSpacing.roundToPx()
                        )
                    }
                    .width(76.dp), // عرض يكفي الكلمة وتظهر تحت الدائرة تماماً
                contentAlignment = Alignment.TopCenter
            ) {
                Text(
                    label,
                    color = if (i < 2) Color(0xFF172D3F) else inActiveColor,
                    fontWeight = if (currentStep == i) FontWeight.Bold else FontWeight.Medium,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    maxLines = 2
                )
            }
        }
    }
}




@Preview(showBackground = true, widthDp = 410, heightDp = 870)
@Composable
fun TrackOrderScreenPreview() {

    val navController = rememberNavController()
    TrackOrderScreen(navController = navController)
}