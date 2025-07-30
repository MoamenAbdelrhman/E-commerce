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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.project.e_commerce.android.R

data class SizeEntry(
    var size: String = "",
    var colors: MutableList<ColorEntry> = mutableListOf()
)

data class ColorEntry(
    var color: String = "",
    var quantity: String = ""
)

data class CategoryBehavior(
    val units: List<String>,
    val enableSize: Boolean,
    val enableColor: Boolean
)

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


    val categoryBehaviors = mapOf(
        "Perfumes" to CategoryBehavior(units = emptyList(), enableSize = true, enableColor = false),
        "Clothing" to CategoryBehavior(units = listOf("XS", "S", "M", "L", "XL"), enableSize = true, enableColor = true),
        "Furniture" to CategoryBehavior(units = emptyList(), enableSize = true, enableColor = false),
        "Electronics" to CategoryBehavior(units = emptyList(), enableSize = false, enableColor = false),
        "Accessories" to CategoryBehavior(units = listOf("one size"), enableSize = true, enableColor = true)
    )

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
//        CategoryDropdown(
//            selectedCategory = selectedCategory,
//            onCategorySelected = { selectedCategory = it }
//        )
        Spacer(modifier = Modifier.height(8.dp))

        var selectedCategory by remember { mutableStateOf("") }
        var selectedCategoryBehavior by remember { mutableStateOf(CategoryBehavior(emptyList(), false, false)) }
        var sizes by remember { mutableStateOf(mutableListOf<String>()) }
        val colorQuantities = remember { mutableStateMapOf<String, MutableMap<String, String>>() }
        val colorOptions = listOf("Red", "Blue", "Black", "White", "Yellow")

        CategoryDropdown(
            selectedCategory = selectedCategory,
            onCategorySelected = {
                selectedCategory = it
                selectedCategoryBehavior = categoryBehaviors[it] ?: CategoryBehavior(emptyList(), false, false)
                sizes.clear()
                colorQuantities.clear()
            }
        )
        Spacer(modifier = Modifier.height(8.dp))


        if (selectedCategoryBehavior.enableSize && selectedCategoryBehavior.enableColor) {
            // --- ملابس/اكسسوارات ---
            var selectedSize by remember { mutableStateOf("") }
            DropdownWithStyle(
                label = "Select Size",
                options = selectedCategoryBehavior.units,
                selectedOption = selectedSize,
                onOptionSelected = { newSize ->
                    selectedSize = newSize
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (selectedSize.isNotBlank()) {
                var selectedColor by remember { mutableStateOf("") }
                var quantityInput by remember { mutableStateOf("") }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DropdownWithStyle(
                        label = "Select Color",
                        options = colorOptions,
                        selectedOption = selectedColor,
                        onOptionSelected = { selectedColor = it },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    OutlinedTextField(
                        value = quantityInput,
                        onValueChange = { if (it.all { char -> char.isDigit() }) quantityInput = it },
                        placeholder = { Text("Qty") },
                        modifier = Modifier.weight(0.6f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done)
                        , colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color(0xFF1B7ACE),
                            unfocusedBorderColor = Color(0xFFB3C1D1),
                            disabledBorderColor = Color(0xFFB3C1D1),
                            backgroundColor = Color.White,
                            disabledTextColor = Color.Black,
                            disabledPlaceholderColor = Color(0xFFB3C1D1)
                        ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                        onClick = {
                        if (selectedColor.isNotBlank() && quantityInput.isNotBlank()) {
                            if (!sizes.contains(selectedSize)) sizes.add(selectedSize)
                            if (colorQuantities[selectedSize] == null) colorQuantities[selectedSize] = mutableMapOf()
                            colorQuantities[selectedSize]?.put(selectedColor, quantityInput)
                            selectedColor = ""
                            quantityInput = ""
                        }
                    }) { Text("Add",color = Color.White) }
                }
            }
        } else if (selectedCategoryBehavior.enableSize) {
            Spacer(modifier = Modifier.height(8.dp))
            // --- عطور/أثاث ...الخ ---
            var customSize by remember { mutableStateOf("") }
            var quantityInput by remember { mutableStateOf("") }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = customSize,
                    onValueChange = { if (it.all { char -> char.isDigit() }) customSize = it },
                    placeholder = { Text("Size (ml)") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                    ,colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF1B7ACE),
                        unfocusedBorderColor = Color(0xFFB3C1D1),
                        disabledBorderColor = Color(0xFFB3C1D1),
                        backgroundColor = Color.White,
                        disabledTextColor = Color.Black,
                        disabledPlaceholderColor = Color(0xFFB3C1D1)
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = quantityInput,
                    onValueChange = { if (it.all { char -> char.isDigit() }) quantityInput = it },
                    placeholder = { Text("Qty") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF1B7ACE),
                        unfocusedBorderColor = Color(0xFFB3C1D1),
                        disabledBorderColor = Color(0xFFB3C1D1),
                        backgroundColor = Color.White,
                        disabledTextColor = Color.Black,
                        disabledPlaceholderColor = Color(0xFFB3C1D1)
                    ),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                    onClick = {
                    if (customSize.isNotBlank() && quantityInput.isNotBlank()) {
                        sizes.add(customSize)
                        colorQuantities[customSize] = mutableMapOf("" to quantityInput)
                        customSize = ""
                        quantityInput = ""
                    }
                }) { Text("Add",color = Color.White) }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // --- العرض النهائي الاحترافي ---
        sizes.forEach { size ->
            if (!selectedCategoryBehavior.enableColor) {
                val qty = colorQuantities[size]?.get("") ?: ""
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .border(1.dp, Color(0xFFB3C1D1), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Size: ", fontWeight = FontWeight.SemiBold, color = Color(0xFF555555))
                    Text(size, modifier = Modifier.padding(horizontal = 4.dp), fontWeight = FontWeight.Bold)
                    Spacer(Modifier.width(12.dp))
                    Text("Qty: ", color = Color.Gray)
                    Text(qty, color = Color(0xFF0066CC), fontWeight = FontWeight.Bold)
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            sizes.remove(size)
                            colorQuantities.remove(size)
                        }
                    ) { Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Red) }
                }
            } else {
                colorQuantities[size]?.forEach { (color, qty) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp)
                            .border(1.dp, Color(0xFFB3C1D1), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text("Size: $size", fontWeight = FontWeight.Medium, color = Color(0xFF174378))
                        Spacer(Modifier.width(12.dp))
                        Text("Color: $color", fontWeight = FontWeight.Normal, color = Color(0xFF238576))
                        Spacer(Modifier.width(12.dp))
                        Text("Qty: $qty", fontWeight = FontWeight.Bold, color = Color(0xFF0066CC))
                        Spacer(Modifier.weight(1f))
                        IconButton(
                            onClick = {
                                colorQuantities[size]?.remove(color)
                                if (colorQuantities[size]?.isEmpty() == true) {
                                    sizes.remove(size)
                                    colorQuantities.remove(size)
                                }
                            }
                        ) { Icon(Icons.Default.Close, contentDescription = "Remove", tint = Color.Red) }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))

        if (!selectedCategoryBehavior.enableSize && !selectedCategoryBehavior.enableColor) {
            Spacer(modifier = Modifier.height(8.dp))
            CustomOutlinedTextField(
                value = productQuantity,
                onValueChange = { productQuantity = it },
                label = "Quantity",
                placeholder = "Enter quantity"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Price & Quantity
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CustomOutlinedTextField(
                value = productPrice,
                onValueChange = { productPrice = it },
                label = "Price",
                placeholder = "Enter price",
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
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
        ) {
            Text("Submit", color = Color.White, fontWeight = Bold)
        }

        Spacer(modifier = Modifier.height(48.dp))
    }

}

@Composable
fun DropdownWithStyle(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier.clickable { expanded = true }) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            enabled = false,
            placeholder = { Text(label, color = Color(0xFFB3C1D1)) },
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = null, modifier = Modifier.rotate(if (expanded) 180f else 0f))
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
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach {
                DropdownMenuItem(onClick = {
                    onOptionSelected(it)
                    expanded = false
                }) {
                    Text(it)
                }
            }
        }
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
fun MultiSelectChips(
    label: String,
    options: List<String>,
    selectedItems: Set<String>,
    onChange: (Set<String>) -> Unit
) {
    Column {
        Text(label, fontWeight = FontWeight.Medium, color = Color(0xFF0066CC))
        Spacer(Modifier.height(4.dp))
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            options.forEach { item ->
                val isSelected = selectedItems.contains(item)
                Box(
                    Modifier
                        .padding(end = 6.dp)
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Color(0xFF1B7ACE) else Color(0xFFB3C1D1),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .background(if (isSelected) Color(0x221B7ACE) else Color.White, shape = RoundedCornerShape(16.dp))
                        .clickable {
                            onChange(
                                if (isSelected) selectedItems - item else selectedItems + item
                            )
                        }
                        .padding(horizontal = 14.dp, vertical = 7.dp)
                ) {
                    Text(item, color = if (isSelected) Color(0xFF1B7ACE) else Color.Gray)
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
