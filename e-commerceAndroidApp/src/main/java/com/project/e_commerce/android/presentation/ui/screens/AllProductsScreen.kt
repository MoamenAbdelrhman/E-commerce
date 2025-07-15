package com.project.e_commerce.android.presentation.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens

@Composable
fun AllProductsScreen(
    navController: NavHostController,
    products: List<Product>,
    title: String = "Featured Products"
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top AppBar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .offset(x = (-18).dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFF0066CC),
                    modifier = Modifier.padding(9.dp)
                )
            }
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
                    .offset(x = (8).dp),
                textAlign = TextAlign.Center,
                color = Color(0xFF0066CC)
            )
            Row {
                IconButton(onClick = { navController.navigate(Screens.ProductScreen.SearchScreen.route) },
                    modifier = Modifier.offset(x = (12).dp)) {
                    Icon(Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                        modifier = Modifier.size(28.dp)
                    )
                }
                IconButton(modifier = Modifier.offset(x = (12).dp),
                    onClick = { navController.navigate(Screens.ProfileScreen.NotificationScreen.route) }) {
                    Box(
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification_icon),
                            contentDescription = "Notifications",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(28.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(0xFFFF3D00), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "1",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

        // المنتجات Grid
        androidx.compose.foundation.lazy.grid.LazyVerticalGrid(
            columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(products.size) { index ->
                val product = products[index]
                BagProductCard(product = product) {
                    navController.navigate(Screens.ProductScreen.DetailsScreen.route + "/${product.name}")
                }
            }
        }
    }
}


@Composable
fun BagProductCard(product: Product, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFF2F2F2), RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .padding(bottom = 8.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = product.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // زر عربة التسوق دائري
                IconButton(
                    onClick = { /* أضف للعربة */ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
//                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color(0xFF0B74DA),
                        modifier = Modifier
                            .size(28.dp)
                            .background(
                                Color(0xFFEFF6FB),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(5.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = product.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color(0xFF222222),
                maxLines = 2,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "4000+ added to cart", // اجعلها dynamic حسب منتجك
                color = Color(0xFF888888),
                fontSize = 13.sp,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "EGP${product.price}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF222222)
                )
                Text(
                    text = "EGP278.20",
                    fontSize = 12.sp,
                    color = Color(0xFFB0B0B0),
                    textDecoration = TextDecoration.LineThrough
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AllProductsScreenPreview() {
    val navController = rememberNavController()
    val sample = sampleProducts()
    AllProductsScreen(navController = navController, products = sample, title = "Featured Products")
}