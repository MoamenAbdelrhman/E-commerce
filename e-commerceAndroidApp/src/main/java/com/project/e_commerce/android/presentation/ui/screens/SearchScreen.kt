package com.project.e_commerce.android.presentation.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.RangeSlider
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.navigation.Screens
import com.project.e_commerce.android.presentation.ui.screens.CategoryDropdown
import com.project.e_commerce.android.presentation.ui.screens.CustomOutlinedTextField
import com.project.e_commerce.android.presentation.ui.screens.ProductCard
import com.project.e_commerce.android.presentation.ui.screens.createAccountScreen.CreateAnAccountScreen
import com.project.e_commerce.android.presentation.ui.screens.sampleProducts
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.TextField
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.focus.focusRequester
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var sortLowToHigh by remember { mutableStateOf(true) }

    // للتحكم بظهور الـ BottomSheets
    var showFilterSheet by remember { mutableStateOf(false) }
    var showPriceSheet by remember { mutableStateOf(false) }
    var showRatingSheet by remember { mutableStateOf(false) }

    // قيم الفلاتر
    var selectedCategories by remember { mutableStateOf(listOf<String>()) }
    var selectedBrands by remember { mutableStateOf(listOf<String>()) }
    var priceRange by remember { mutableStateOf(0f..1000f) }
    var selectedRating by remember { mutableStateOf(0) }

    val recentSearches = listOf("Hoodies", "Trousers", "Blue jeans", "Watches")
    val recentlyViewed = listOf(R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img2)

    val allProducts = sampleProducts()
    val filteredProducts = allProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true) &&
                (selectedCategory.isEmpty() || it.name.contains(selectedCategory, ignoreCase = true))
    }.let {
        if (sortLowToHigh) it.sortedBy { it.price.toInt() }
        else it.sortedByDescending { it.price.toInt() }
    }

    val focusManager = LocalFocusManager.current
    val textFieldFocusRequester = remember { androidx.compose.ui.focus.FocusRequester() }

    LaunchedEffect(Unit) {
        textFieldFocusRequester.requestFocus()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Spacer(Modifier.height(16.dp))

        // Search Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Back icon
            IconButton(onClick = { navController.popBackStack() },
                modifier = Modifier.offset(x = (-12).dp)) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color(0xFF1B7ACE),
                    modifier = Modifier.size(26.dp)
                )
            }
            // Search Box

            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = {
                    androidx.compose.material3.Text(
                        "Search reels or users...",
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFFF6F00),
                        modifier = Modifier.clickable {
                            focusManager.clearFocus()
                        }
                    )
                },
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .offset(x = (-6).dp)
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF0F0F0))
                    .focusRequester(textFieldFocusRequester)
                ,
                colors = androidx.compose.material.TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFFF6F00),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0),
                    cursorColor = Color(0xFFFF6F00)
                ),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                )
            )


            /*BasicTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                singleLine = true,
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                modifier = Modifier
                    .offset(x = (-12).dp)
                    .weight(1f)
                    .height(48.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF0F0F0))
                    .padding(horizontal = 8.dp)
                    .focusRequester(textFieldFocusRequester) ,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { focusManager.clearFocus() }
                ),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
                        Spacer(Modifier.width(8.dp))
                        Box(Modifier.weight(1f)) {
                            if (searchQuery.isEmpty()) {
                                Text("Search products...", color = Color.Gray, fontSize = 16.sp)
                            }
                            innerTextField()
                        }
                    }
                }
            )

            // Search text
            Text(
                text = "Search",
                color = Color(0xFFFF6F00),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .clickable {
                        focusManager.clearFocus()
                    }
            )*/
        }



        Spacer(Modifier.height(16.dp))

        if (searchQuery.isBlank()) {
            // Recent Search
            Text("Recent search", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))
            Column {
                recentSearches.forEach {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(it, color = Color.Black)
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Recently viewed
            Text("Recently viewed", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = Color(0xFF1B7ACE))
            Spacer(Modifier.height(8.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(items = recentlyViewed) { img ->
                    Image(
                        painter = painterResource(id = img),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF8F8F8))
                    )
                }
            }
        } else {
            // Results & Filters
            Text("${filteredProducts.size} Results found", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Gray)
            Spacer(Modifier.height(12.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                item { FilterChip("Filter") { showFilterSheet = true } }
                item { FilterChip("Price") { showPriceSheet = true } }
                item { FilterChip("Rating") { showRatingSheet = true } }
                item { FilterChip(if (sortLowToHigh) "Low to High" else "High to Low") { sortLowToHigh = !sortLowToHigh } }
            }

            Spacer(Modifier.height(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(700.dp)
            ) {
                items(filteredProducts.size) { index ->
                    val product = filteredProducts[index]
                    ProductCard(product = product) {
                        navController.navigate(Screens.ProductScreen.DetailsScreen.route + "/${product.name}")
                    }
                }
            }
        }

        // BottomSheets
        if (showFilterSheet) {
            ModalBottomSheet(onDismissRequest = { showFilterSheet = false }) {
                FilterBottomSheet(
                    selectedCategories = selectedCategories,
                    onCategorySelected = { category ->
                        selectedCategories = selectedCategories.toggle(category)
                    },
                    selectedBrands = selectedBrands,
                    onBrandSelected = { brand ->
                        selectedBrands = selectedBrands.toggle(brand)
                    },
                    onClose = { showFilterSheet = false }
                )
            }
        }

        if (showPriceSheet) {
            ModalBottomSheet(onDismissRequest = { showPriceSheet = false }) {
                PriceBottomSheet(
                    priceRange = priceRange,
                    onPriceChange = { priceRange = it },
                    onClose = { showPriceSheet = false }
                )
            }
        }

        if (showRatingSheet) {
            ModalBottomSheet(onDismissRequest = { showRatingSheet = false }) {
                RatingBottomSheet(
                    selectedRating = selectedRating,
                    onRatingSelected = { selectedRating = it },
                    onClose = { showRatingSheet = false }
                )
            }
        }
    }
}

fun List<String>.toggle(item: String): List<String> {
    return if (contains(item)) this - item else this + item
}

@Composable
fun FilterChip(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, Color(0xFF0066CC), RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text, color = Color(0xFF0066CC), fontSize = 13.sp)
    }
}

@Composable
fun StyledBottomSheetContent(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
    onApply: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        content()
        Spacer(Modifier.height(24.dp))
        Button(
            modifier = Modifier.fillMaxWidth().height(48.dp).padding(horizontal = 18.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onApply,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7ACE)),
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
        ) {
            Text("Apply", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

// Filter
@Composable
fun FilterBottomSheet(
    selectedCategories: List<String>,
    onCategorySelected: (String) -> Unit,
    selectedBrands: List<String>,
    onBrandSelected: (String) -> Unit,
    onClose: () -> Unit
) {
    val categories = listOf("T-shirts", "Jackets", "Jeans")
    val brands = listOf("Nike", "Adidas", "Zara")

    StyledBottomSheetContent(
        title = "Filter",
        onApply = onClose,
        content = {
        Text("Categories", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(Modifier.height(8.dp))
        categories.forEach { category ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable { onCategorySelected(category) }
            ) {
                Checkbox(
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1B7ACE)),
                    checked = selectedCategories.contains(category),
                    onCheckedChange = { onCategorySelected(category)}
                )
                Text(category)
            }
        }

        Spacer(Modifier.height(16.dp))
        Text("Brands", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(Modifier.height(8.dp))
        brands.forEach { brand ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onBrandSelected(brand) }
                    .padding(vertical = 6.dp)
            ) {
                Checkbox(
                    checked = selectedBrands.contains(brand),
                    onCheckedChange = { onBrandSelected(brand) }
                )
                Text(brand)
            }
        }
    }
    )
}

// Price
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PriceBottomSheet(
    priceRange: ClosedFloatingPointRange<Float>,
    onPriceChange: (ClosedFloatingPointRange<Float>) -> Unit,
    onClose: () -> Unit
) {
    var currentRange by remember { mutableStateOf(priceRange) }

    StyledBottomSheetContent(
        title = "Select Price Range",
        onApply = { onPriceChange(currentRange); onClose() },
        content =
            {
        RangeSlider(
            value = currentRange,
            onValueChange = { currentRange = it },
            valueRange = 0f..1000f
        )
        Text(
            "From \$${currentRange.start.toInt()} to \$${currentRange.endInclusive.toInt()}",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
    )
}

// Rating
@Composable
fun RatingBottomSheet(
    selectedRating: Int,
    onRatingSelected: (Int) -> Unit,
    onClose: () -> Unit
) {
    val ratings = (5 downTo 1).toList()

    StyledBottomSheetContent(
        title = "Select Minimum Rating",
        onApply = onClose,
        content =
            {
        ratings.forEach { stars ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onRatingSelected(stars) }
            ) {
                repeat(stars) {
                    Icon(Icons.Default.StarRate, contentDescription = null, tint = Color(0xFFFFC107))
                }
                Spacer(Modifier.weight(1f))
                if (selectedRating == stars) {
                    Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF0066CC))
                }
            }
        }
    }
    )
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}