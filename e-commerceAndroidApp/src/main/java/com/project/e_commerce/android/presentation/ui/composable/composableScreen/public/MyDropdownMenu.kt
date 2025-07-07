package com.project.e_commerce.android.presentation.ui.composable.composableScreen.public

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.project.e_commerce.android.R
import com.project.e_commerce.android.presentation.ui.utail.BlackColor80
import com.project.e_commerce.android.presentation.ui.utail.GrayColor80
import com.project.e_commerce.android.presentation.ui.utail.UnitsApplication

@Composable
fun MyDropdownMenu(
    items: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(0) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, color = GrayColor80)
                .clickable { expanded = true }
                .padding(
                    UnitsApplication.mediumUnit
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = items[selectedIndex]
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_left_arrow),
                contentDescription = null,
                tint = BlackColor80,
                modifier = Modifier
                    .size(UnitsApplication.mediumUnit)
                    .rotate(90f)
            )

        }

        DropdownMenu(
            containerColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = UnitsApplication.mediumUnit),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = UnitsApplication.mediumUnit),
                    onClick = {
                        selectedIndex = index
                        onSelected(item)
                        expanded = false
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = UnitsApplication.tinyUnit),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item)
                    }

                }
            }
        }
    }
}