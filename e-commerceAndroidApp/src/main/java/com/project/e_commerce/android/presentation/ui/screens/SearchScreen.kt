import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@Composable
fun SearchScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var sortLowToHigh by remember { mutableStateOf(true) }

    val allProducts = sampleProducts()
    val filteredProducts = allProducts.filter {
        it.name.contains(searchQuery, ignoreCase = true) &&
                (selectedCategory.isEmpty() || it.name.contains(selectedCategory, ignoreCase = true))
    }.let {
        if (sortLowToHigh) it.sortedBy { it.price.toInt() }
        else it.sortedByDescending { it.price.toInt() }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            // Header
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
                Text(
                    text = "Search",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF0066CC),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(Modifier.height(16.dp))
            CustomOutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = "Search Products",
                placeholder = "Type to search..."
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(Modifier.height(12.dp))
            CategoryDropdown(selectedCategory, onCategorySelected = { selectedCategory = it })
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Spacer(Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color(0xFFB6B6B6), RoundedCornerShape(12.dp))
                    .padding(12.dp)
            ) {
                Text("Price: ", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
                Spacer(Modifier.width(8.dp))
                Text(if (sortLowToHigh) "Low to High" else "High to Low", color = Color.Gray)
                Spacer(Modifier.weight(1f))
                Switch(checked = sortLowToHigh, onCheckedChange = { sortLowToHigh = it })
            }
        }

        items(filteredProducts.size) { index ->
            val product = filteredProducts[index]
            ProductCard(product = product) {
                navController.navigate(Screens.ProductScreen.DetailsScreen.route + "/${product.name}")
            }
        }
    }
}





@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(navController = rememberNavController())
}