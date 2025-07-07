package com.project.e_commerce.android.presentation.ui.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.material.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R

@Composable
fun PaymentScreen(navController: NavController, cartItems: List<CartItem> = emptyList()) {
    var cardHolder by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var selectedMethod by remember { mutableStateOf("Mastercard") }

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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }

            Text(
                text = "Payment",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Payment Methods
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PaymentMethodButton(
                name = "Mastercard",
                selected = selectedMethod == "Mastercard",
                onClick = { selectedMethod = "Mastercard" },
                modifier = Modifier.weight(1f)
            )
            PaymentMethodButton(
                name = "Paypal",
                selected = selectedMethod == "Paypal",
                onClick = { selectedMethod = "Paypal" },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Form Fields
        PaymentFieldWithLabel(Icons.Default.Person, "Cardholder Name", "e.g. Mohamed", cardHolder) {
            cardHolder = it
        }

        Spacer(modifier = Modifier.height(6.dp))
        PaymentFieldWithLabel(Icons.Default.CreditCard, "Card Number", "XXXX XXXX XXXX XXXX", cardNumber) {
            cardNumber = it
        }

        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
            PaymentFieldWithLabel(
                Icons.Default.Lock, "Cvv", "785", cvv,
                modifier = Modifier.weight(1f)
            ) { cvv = it }

            PaymentFieldWithLabel(
                Icons.Default.DateRange, "Expiry date", "MM/YY", expiryDate,
                modifier = Modifier.weight(1f)
            ) { expiryDate = it }
        }

        Spacer(modifier = Modifier.height(16.dp))
        CostSummary(cartItems)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Confirm payment */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6600)),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 8.dp,   // قوة الظل العادية
                pressedElevation = 12.dp,   // عند الضغط على الزر
                hoveredElevation = 10.dp
            )
        ) {
            Text("Pay Now", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}



@Composable
fun PaymentFieldWithLabel(
    icon: ImageVector,
    label: String,
    placeholder: String,
    value: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier.padding(bottom = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color(0xFF0B74DA))
            Spacer(modifier = Modifier.width(6.dp))
            Text(label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color(0xFF174378))
        }
        Spacer(modifier = Modifier.height(6.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            ),
            shape = RoundedCornerShape(10.dp)
        )
    }
}


@Composable
fun PaymentMethodButton(
    name: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor = if (selected) Color(0xFF0B74DA) else Color.White
    val textColor = if (selected) Color.White else Color.Black
    val borderColor = if (selected) Color.Transparent else Color(0xFF0B74DA)

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = bgColor),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier.height(48.dp)
    ) {
        Image(
            painter = painterResource(
                id = if (name == "Mastercard") R.drawable.mastercard_logo else R.drawable.paypal_logo
            ),
            contentDescription = name,
            modifier = Modifier
                .width(24.dp)
                .height(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(name, color = textColor, fontWeight = FontWeight.SemiBold)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPaymentScreen() {
    val fakeCart = listOf(
        CartItem("Coco Mademoiselle", 100.0, R.drawable.perfume3, 2),
        CartItem("Tom Ford Black Orchid", 100.0, R.drawable.perfume2, 1)
    )

    PaymentScreen(navController = rememberNavController(), cartItems = fakeCart)
}