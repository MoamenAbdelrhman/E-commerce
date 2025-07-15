package com.project.e_commerce.android.presentation.ui.screens

import android.media.MediaMetadataRetriever
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
fun AddNewContentScreen(navController: NavHostController) {
    var reelTitle by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }
    var productQuantity by remember { mutableStateOf("") }
    var productTags by remember { mutableStateOf("") }
//    var showDialog by remember { mutableStateOf(false) }

    val reelVideoUri = remember { mutableStateOf<Uri?>(null) }
    val productImageUris = remember { mutableStateListOf<Uri>() }

    val reelLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        reelVideoUri.value = uri
    }

    val imageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
        if (uris != null) {
            val uniqueUris = uris.filterNot { it in productImageUris }
            productImageUris.addAll(uniqueUris)
        }
    }

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
                text = "Add New Product ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Reel
        Text("Upload Product Reel", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(Modifier.height(8.dp))
        if (reelVideoUri.value != null) {
            Row(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = reelTitle,
                        onValueChange = { reelTitle = it },
                        placeholder = { Text("Add description...", color = Color.LightGray) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth().height(150.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = { reelTitle += "#" },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF0F0F0)),
                        shape = RoundedCornerShape(8.dp),
                        elevation = null,
                        modifier = Modifier
                            .defaultMinSize(minHeight = 36.dp)
                    ) {
                        Text("# Hashtags", color = Color.Black)
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Box(
                    modifier = Modifier
                        .size(width = 140.dp, height = 200.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE0E0E0))
                ) {
                    val context = LocalContext.current
                    val bitmap = remember(reelVideoUri.value) {
                        val retriever = MediaMetadataRetriever()
                        retriever.setDataSource(context, reelVideoUri.value)
                        val frame = retriever.getFrameAtTime(1000000)
                        retriever.release()
                        frame
                    }

                    bitmap?.let {
                        Image(
                            bitmap = it.asImageBitmap(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Text(
                        "Preview",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                    )

                    IconButton(
                        onClick = { reelVideoUri.value = null },
                        modifier = Modifier.align(Alignment.TopEnd).padding(end= 4.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Red)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        } else {
            UploadBox(
                height = 160.dp,
                text = "Upload video",
                buttonText = "Browse files",
                note = "Max 60 seconds, MP4/MOV, Max size 50MB",
                onClick = { reelLauncher.launch("video/*") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Product Images
        Text("Upload Product Images", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(Modifier.height(8.dp))
        if (productImageUris.isNotEmpty()) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(count = productImageUris.size) { index ->
                    val uri = productImageUris[index]
                    SelectedImage(uri = uri, onRemove = { productImageUris.remove(uri) })
                }

                item {
                    Button(
                        onClick = { imageLauncher.launch("image/*") },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7ACE)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(vertical = 25.dp)
                    ) {
                        Text("+ Add", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        } else {
            UploadBox(
                height = 160.dp,
                text = "Upload photos",
                buttonText = "Browse files",
                note = "Format: .jpeg, .png & Max file size: 25 MB",
                onClick = { imageLauncher.launch("image/*") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Product Name
        CustomOutlinedTextField(value = productName, onValueChange = { productName = it }, label = "Product Name", placeholder = "Enter product name")
        Spacer(modifier = Modifier.height(8.dp))

        // Description
        CustomOutlinedTextField(value = description, onValueChange = { description = it }, label = "Description", placeholder = "Add description", minLines = 4)
        Spacer(modifier = Modifier.height(8.dp))

        // Category
        Text("Category", fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF0066CC))
        Spacer(modifier = Modifier.height(4.dp))
        CategoryDropdown(
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Price & Quantity
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CustomOutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = "Price",
                placeholder = "Enter price",
                modifier = Modifier.weight(1f)
            )
            CustomOutlinedTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                label = "No. of available items",
                placeholder = "Enter number",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
            elevation = ButtonDefaults.elevation(defaultElevation = 6.dp)
        ) {
            Text("Submit", color = Color.White, fontWeight = Bold)
        }

        Spacer(modifier = Modifier.height(48.dp))
    }

}


@Composable
fun CategoryDropdown(
    selectedCategory: String?,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf("Perfumes", "Clothing", "Furniture", "Electronics", "Accessories")
    var expanded by remember { mutableStateOf(false) }

    Box {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true } // هنا التفاعل
        ) {
            OutlinedTextField(
                value = selectedCategory ?: "",
                onValueChange = {},
                readOnly = true,
                enabled = false, // تمنع التفاعل الافتراضي مع الحقل
                placeholder = { Text("Select Category", color = Color(0xFFB3C1D1)) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        modifier = Modifier.rotate(if (expanded) 180f else 0f)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF1B7ACE),
                    unfocusedBorderColor = Color(0xFFB3C1D1),
                    disabledBorderColor = Color(0xFFB3C1D1),
                    backgroundColor = Color.White,
                    disabledTextColor = Color.Black,
                    disabledPlaceholderColor = Color(0xFFB3C1D1)
                ),
                singleLine = true
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                ) {
                    Text(category)
                }
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        Text(label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF0066CC))
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder, color = Color(0xFF114B7F)) },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = (minLines * 24).dp),
            shape = RoundedCornerShape(8.dp),
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            )
        )
    }
}


@Composable
fun UploadBox(
    height: Dp,
    text: String,
    buttonText: String,
    note: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .border(1.dp, Color(0xFFB6B6B6), RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = R.drawable.upload_icon),
                contentDescription = null,
                tint = Color(0xFF0066CC),
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(text, color = Color.Gray, fontSize = 14.sp)
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7ACE)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(buttonText, color = Color.White)
            }
            Spacer(Modifier.height(8.dp))
            Text(note, color = Color.Gray, fontSize = 11.sp)
        }
    }
}

@Composable
fun SelectedImage(uri: Uri, onRemove: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(8.dp))
        )
        IconButton(
            onClick = onRemove,
            modifier = Modifier.align(Alignment.TopEnd).size(24.dp)
        ) {
            Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Red)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddNewContentScreen() {
    AddNewContentScreen(navController = rememberNavController())
}
