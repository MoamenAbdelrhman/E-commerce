package com.project.e_commerce.android.presentation.ui.screens

import android.R.attr.rating
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.graphics.graphicsLayer
import com.project.e_commerce.android.presentation.ui.navigation.Screens


@Composable
fun ProductScreen(navController: NavHostController) {
    val allFeaturedProducts = sampleProducts()
    val bestSellers = sampleProducts()
    val categories = getCategories()

    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredProducts = allFeaturedProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = PaddingValues(bottom = 72.dp)
    ) {
        item {
            TopBarWithCart()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Box(modifier = Modifier.clickable {
                navController.navigate(Screens.ProductScreen.SearchScreen.route)
            }) {
                SearchBar(searchQuery = searchQuery, onQueryChanged = {
                    searchQuery = it
                })
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            SalesCard()
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(
                        category = category,
                        isSelected = category.name == selectedCategory,
                        onClick = { selectedCategory = category.name }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            ProductSection(
                title = "Featured Products",
                products = filteredProducts,
                navController = navController
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            ProductSection(
                title = "Best Sellers",
                products = bestSellers,
                navController = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun TopBarWithCart() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Products",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )

    }
}
@Composable
fun SearchBar(searchQuery: String, onQueryChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFFFE57F), Color(0xFFFFC107))
                )
            )
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onQueryChanged,
            placeholder = { Text("Search...", color = Color.Gray) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            enabled = false, // هذا يمنع الكتابة داخله
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            textStyle = TextStyle(color = Color.Black)
        )
    }
}



@Composable
fun SalesCard() {
    Image(
        painter = painterResource(id = R.drawable.ic_sales), // استخدم صورة الـ Sales
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun <T> SectionWithHorizontalList(
    title: String,
    items: List<T>,
    itemContent: @Composable (T) -> Unit
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("See All", color = Color.Blue, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .height(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (isSelected) Color(0xFFFF6F00) else Color(0xFF757575))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = category.imageResId),
            contentDescription = category.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .height(85.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = category.name,
                fontSize = 12.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}



@Composable
fun ProductSection(title: String, products: List<Product>, navController: NavHostController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("See All", color = Color.Blue, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                ProductCard(product = product) {
                    navController.navigate(Screens.ProductScreen.DetailsScreen.route + "/${product.name}")
                }
            }
        }
    }
}


@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(8.dp),
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)

        ) {
            Image(
                painter = painterResource(id = product.image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = { isFavorite = !isFavorite },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(6.dp)
                    .background(Color.White.copy(alpha = 0.8f), CircleShape)
                    .size(28.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(product.name, fontSize = 14.sp, maxLines = 1)

        Spacer(modifier = Modifier.height(4.dp))

        // تقييم النجوم مع الرقم
        RatingBar(rating = product.rating)


        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${product.price}$",
                color = Color(0xFFFF5722),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Add to cart",
                tint = Color(0xFF0B74DA),
                modifier = Modifier
                    .size(24.dp)
                    .background(
                        Color(0xFFEFF6FB),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun RatingBar(rating: Double) {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5
    val totalStars = 5

    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }

        if (hasHalfStar) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clipToBounds()
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier
                        .matchParentSize()
                        .graphicsLayer {
                            clip = true
                            shape = androidx.compose.foundation.shape.GenericShape { size, _ ->
                                moveTo(0f, 0f)
                                lineTo(size.width / 2, 0f)
                                lineTo(size.width / 2, size.height)
                                lineTo(0f, size.height)
                                close()
                            }
                        }
                )

                Icon(
                    imageVector = Icons.Filled.StarBorder,
                    contentDescription = null,
                    tint = Color(0xFFFFC107),
                    modifier = Modifier.matchParentSize()
                )
            }
        }

        val remaining = totalStars - fullStars - if (hasHalfStar) 1 else 0
        repeat(remaining) {
            Icon(
                imageVector = Icons.Filled.StarBorder,
                contentDescription = null,
                tint = Color(0xFFFFC107),
                modifier = Modifier.size(16.dp)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = rating.toString(),
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}



data class Product(
    val name: String,
    val image: Int,
    val price: String,
    val rating: Double
)

fun sampleProducts() = listOf(
    Product("Coco Noir Chanel", R.drawable.perfume1, "100", 4.8),
    Product("Bleu De Chanel", R.drawable.perfume2, "100", 4.0),
    Product("Coco Mademoiselle", R.drawable.perfume3, "100", 3.5),
    Product("Tom Ford Black", R.drawable.perfume4, "100", 5.0)
)

data class Category(
    val name: String,
    val imageResId: Int
)
fun getCategories(): List<Category> {
    return listOf(
        Category("Perfumes", R.drawable.perfumes),
        Category("Clothing", R.drawable.clothing),
        Category("Furniture", R.drawable.furniture),
        Category("Electronics", R.drawable.electronics),
        Category("Accessories", R.drawable.accessories)
    )
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductScreenPreview() {
    val navController = rememberNavController()
    ProductScreen(navController)
}