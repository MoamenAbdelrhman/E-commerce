package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.material.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Percent
import androidx.compose.runtime.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens

@Composable
fun CartScreen(navController: NavController) {

    val isLoggedIn = remember { mutableStateOf(false) } // Replace with actual login state
    var showLoginPrompt by remember { mutableStateOf(true) }

    if (!isLoggedIn.value && showLoginPrompt) {
        RequireLoginPrompt(
            onLogin = {
                showLoginPrompt = false
                navController.navigate(Screens.LoginScreen.route)
            },
            onSignUp = {
                showLoginPrompt = false
                navController.navigate(Screens.LoginScreen.CreateAccountScreen.route)
            },
            onDismiss = {
                /* لا شيء - يظل ظاهرًا */
            },
            showCloseButton = false
        )
        return
    }


    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem("Coco Mademoiselle", 100.0, R.drawable.perfume3),
                CartItem("Tom Ford Black Orchid", 100.0, R.drawable.perfume2),
                CartItem("Bleu De Chanel", 100.0, R.drawable.perfume1)
            )
        )
    }

    // --- Promo Code States ---
    var promoCode by remember { mutableStateOf("") }
    var isPromoApplied by remember { mutableStateOf(false) }
    var promoMessage by remember { mutableStateOf("") }
    var discountAmount by remember { mutableStateOf(0.0) }

    val primaryColor = Color(0xFFFF6600)
    val secondaryColor = Color(0xFF0066CC)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .imePadding()
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                text = "Cart",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        // Product Items
        cartItems.forEachIndexed { index, item ->
            CartProductCard(item = item, onQuantityChange = { newQty ->
                cartItems = cartItems.toMutableList().also {
                    it[index] = it[index].copy(quantity = newQty)
                }
            })
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Cost Summary (with discount)
        CostSummary(cartItems, discountAmount)

        Spacer(modifier = Modifier.height(12.dp))

        // Promo Code Input + Apply Button
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = promoCode,
                onValueChange = { promoCode = it },
                label = { Text("Enter your code", color = secondaryColor) },
                trailingIcon = {
                    Icon(Icons.Default.Percent, contentDescription = null, tint = secondaryColor)
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                enabled = !isPromoApplied,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = secondaryColor,
                    unfocusedBorderColor = secondaryColor,
                    cursorColor = secondaryColor
                )
            )
            Spacer(Modifier.width(8.dp))
            Button(
                onClick = {
                    if (promoCode.trim().lowercase() == "discount10") {
                        discountAmount = 10.0
                        isPromoApplied = true
                        promoMessage = "Promo code applied successfully!"
                    } else {
                        promoMessage = "Invalid promo code"
                    }
                },
                enabled = !isPromoApplied && promoCode.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = secondaryColor)
            ) {
                Text("Apply", color = Color.White)
            }
        }
        if (promoMessage.isNotBlank()) {
            Text(
                promoMessage,
                color = if (isPromoApplied) secondaryColor else Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 2.dp, bottom = 6.dp)
            )
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Checkout Button
        Button(
            onClick = {
                navController.navigate(Screens.CartScreen.PaymentScreen.route)
            },
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6600)),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(2.dp)
        ) {
            Text("Checkout", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(86.dp))
    }
}

@Composable
fun CartProductCard(item: CartItem, onQuantityChange: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 2)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${item.price}$", color = Color(0xFFFF5722), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        if (item.quantity > 1) onQuantityChange(item.quantity - 1)
                    },
                    modifier = Modifier
                        .background(Color(0xFFE0E0E0), shape = CircleShape)
                        .size(28.dp)
                ) {
                    Text("-", fontWeight = FontWeight.Bold)
                }

                Text(
                    text = item.quantity.toString(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontSize = 16.sp
                )

                IconButton(
                    onClick = { onQuantityChange(item.quantity + 1) },
                    modifier = Modifier
                        .background(Color(0xFFE0E0E0), shape = CircleShape)
                        .size(28.dp)
                ) {
                    Text("+", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun CostSummary(cartItems: List<CartItem>, discountAmount: Double = 0.0) {
    val subtotal = cartItems.sumOf { it.price * it.quantity }
    val shipping = 8.00
    val tax = 0.00
    val total = subtotal + shipping + tax - discountAmount

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF3E0), RoundedCornerShape(10.dp))
            .padding(12.dp)
    ) {
        CostRow("Subtotal", "$${"%.2f".format(subtotal)}", color = Color(0xFFFF5722))
        Spacer(modifier = Modifier.height(8.dp))
        CostRow("Shipping Cost", "$${"%.2f".format(shipping)}", color = Color(0xFFFF5722))
        Spacer(modifier = Modifier.height(8.dp))
        CostRow("Tax", "$${"%.2f".format(tax)}", color = Color(0xFFFF5722))
        if (discountAmount > 0) {
            Spacer(modifier = Modifier.height(8.dp))
            CostRow("Promo discount", "-$${"%.2f".format(discountAmount)}", color = Color(0xFF1B7ACE))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        Spacer(modifier = Modifier.height(8.dp))
        CostRow("Total", "$${"%.2f".format(total.coerceAtLeast(0.0))}", bold = true, color = Color(0xFFFF5722))
    }
}

@Composable
fun CostRow(label: String, value: String, bold: Boolean = false, color: Color = Color.Black) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, fontSize = 14.sp, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal)
        Text(value, fontSize = 14.sp, fontWeight = if (bold) FontWeight.Bold else FontWeight.Normal, color = color)
    }
}

data class CartItem(
    val name: String,
    val price: Double,
    val imageRes: Int,
    var quantity: Int = 1
)

@Preview(showBackground = true)
@Composable
fun PreviewCartScreen() {
    CartScreen(navController = rememberNavController())
}
