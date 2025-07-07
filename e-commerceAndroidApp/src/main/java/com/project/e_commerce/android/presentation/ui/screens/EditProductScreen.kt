package com.project.e_commerce.android.presentation.ui.screens


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.project.e_commerce.android.R


@Composable
fun EditProductScreen(navController: NavHostController, productId: String) {
    var productName by remember { mutableStateOf("Old Product Name") }
    var productDescription by remember { mutableStateOf("Old description") }
    var selectedCategory by remember { mutableStateOf("Clothing") }
    var productPrice by remember { mutableStateOf("100") }
    var productQuantity by remember { mutableStateOf("20") }
    var productTags by remember { mutableStateOf("#fashion") }
    val imageUris = remember { mutableStateListOf<Uri>() }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        if (uris != null) imageUris.addAll(uris)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            androidx.compose.material3.IconButton(
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
                text = "Edit Product",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(0xFFCCCCCC), RoundedCornerShape(8.dp))
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.upload_icon),
                    contentDescription = null,
                    tint = Color(0xFF0066CC),
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text("Upload photos", fontSize = 16.sp, color = Color(0xFF0066CC))
                Text("Format: .jpeg, .png & Max file size: 25 MB", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { launcher.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0066CC))
                ) {
                    Text("Browse files", color = Color.White)
                }
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    imageUris.forEach { uri ->
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .padding(4.dp)
                        ) {
                            AsyncImage(
                                model = uri,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(8.dp))
                            )

                            IconButton(
                                onClick = { imageUris.remove(uri) },
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }

            }
        }

        Row(Modifier.horizontalScroll(rememberScrollState())) {
            imageUris.forEach { uri ->
                Box(
                    modifier = Modifier.size(100.dp).padding(4.dp)
                ) {
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
                    )
                    IconButton(
                        onClick = { imageUris.remove(uri) },
                        modifier = Modifier.align(Alignment.TopEnd).size(24.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null, tint = Color.Red)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        CustomOutlinedTextField(value = productName, onValueChange = { productName = it }, label = "Product Name", placeholder = "Edit product name")
        Spacer(modifier = Modifier.height(8.dp))
        CustomOutlinedTextField(value = productDescription, onValueChange = { productDescription = it }, label = "Description", placeholder = "Edit description", minLines = 4)
        Spacer(modifier = Modifier.height(8.dp))
        CategoryDropdown(selectedCategory = selectedCategory, onCategorySelected = { selectedCategory = it })

        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CustomOutlinedTextField(value = productPrice, onValueChange = { productPrice = it }, label = "Price", placeholder = "Edit price", modifier = Modifier.weight(1f))
            CustomOutlinedTextField(value = productQuantity, onValueChange = { productQuantity = it }, label = "Quantity", placeholder = "Edit quantity", modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))
        CustomOutlinedTextField(value = productTags, onValueChange = { productTags = it }, label = "Tags", placeholder = "#tags")

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { /* save changes */ },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Save Changes", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewEditProductScreen() {
    val navController = rememberNavController()
    EditProductScreen(navController = navController, productId = "123")
}
