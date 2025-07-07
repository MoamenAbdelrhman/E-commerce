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
fun EditReelScreen(navController: NavHostController, reelId: String) {
    var title by remember { mutableStateOf("Old Reel Title") }
    var description by remember { mutableStateOf("Old description") }
    var category by remember { mutableStateOf("Perfumes") }
    var visibility by remember { mutableStateOf("Private") }
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
                text = "Edit Reel",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color(0xFF0066CC),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        // باقي الحقول مثل AddNewReelScreen لكن بياناتها محملة مسبقًا
        CustomOutlinedTextField(value = title, onValueChange = { title = it }, label = "Title", placeholder = "Edit title")
        Spacer(modifier = Modifier.height(8.dp))
        CustomOutlinedTextField(value = description, onValueChange = { description = it }, label = "Description", placeholder = "Edit description", minLines = 4)
        Spacer(modifier = Modifier.height(8.dp))
        CategoryDropdown(selectedCategory = category, onCategorySelected = { category = it })

        Spacer(modifier = Modifier.height(20.dp))

        Text("Visibility", fontWeight = FontWeight.SemiBold, color = Color(0xFF0066CC))
        Row(verticalAlignment = Alignment.CenterVertically) {
            listOf("Private", "Anyone", "Followers only").forEach {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 12.dp)) {
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
fun PreviewEditReelScreen() {
    val navController = rememberNavController()
    EditReelScreen(navController = navController, reelId = "123")
}

