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
import com.project.e_commerce.android.R

@Composable
fun AddNewReelScreen(navController: NavHostController) {
    var title by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var category by remember { mutableStateOf("") }
    var visibility by remember { mutableStateOf("Private") }
    var showDialog by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("") }
    val videoUri = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        videoUri.value = uri
    }

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
                text = "Add New Reel",
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
                .height(160.dp)
                .border(
                    BorderStroke(1.dp, Color(0xFFB6B6B6)),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.upload_icon),
                    contentDescription = null,
                    tint = Color(0xFF0066CC),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("choose from gallery", color = Color.Gray, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { launcher.launch("video/*") },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF1B7ACE)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Upload video", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Max 60 seconds, MP4/MOV, Max size 50MB",
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }
        }

        videoUri.value?.let { uri ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
            ) {
                Text(
                    "Selected video: $uri",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color(0xFF1B7ACE)
                )

                IconButton(
                    onClick = { videoUri.value = null },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove video",
                        tint = Color.Red
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Title Field
        Text("Title", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            placeholder = { Text("Add title to your video", color = Color(0xFF114B7F)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Description Field
        Text("Description (optional)", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            placeholder = { Text("Add description", color = Color(0xFF114B7F)) },
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                ,
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF1B7ACE),
                unfocusedBorderColor = Color(0xFFB3C1D1),
                cursorColor = Color(0xFF174378),
                backgroundColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Category Dropdown (simplified)
        Text("Category", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Spacer(modifier = Modifier.height(4.dp))

        CategoryDropdown(selectedCategory, onCategorySelected = { selectedCategory = it })

        Spacer(modifier = Modifier.height(20.dp))

        // Visibility Options
        Text("Visibility", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Row(verticalAlignment = Alignment.CenterVertically) {
            listOf("Private", "Anyone", "Followers only").forEach {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 12.dp)
                ) {
                    RadioButton(
                        selected = visibility == it,
                        onClick = { visibility = it },
                        colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF0066CC))
                    )
                    Text(it, fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Action Buttons
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { /* Save Draft */ },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF706C6C))
            ) {
                Text("Save as a draft", color = Color.White)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00))
            ) {
                Text("Post reel", color = Color.White, fontWeight = Bold)
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }

    // Confirmation Dialog
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(onClick = { showDialog = false }) {
                            Icon(Icons.Default.Close, contentDescription = null)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Are you sure you want to Post this Reel?",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { showDialog = false },
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Cancel")
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Button(
                            onClick = {
                                // Submit logic
                                showDialog = false
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(45.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6F00)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Post", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAddNewReelScreen() {
    val navController = rememberNavController()
    AddNewReelScreen(navController = navController)
}

