
package com.project.e_commerce.android.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RequireLoginPrompt(
    onLogin: () -> Unit,
    onSignUp: () -> Unit,
    onDismiss: () -> Unit,
    showCloseButton: Boolean = true
) {
    val visible = true // يكون دائمًا ظاهر طالما تم استدعاؤه

    // removed

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        awaitPointerEvent().changes.forEach { it.consume() }
                    }
                }
            }
        // لا يتم الإغلاق بالضغط على الخلفية إذا لم يُسمح بذلك

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFFFF6600).copy(alpha = 0.8f),
                            Color(0xFF0066CC).copy(alpha = 0.8f)
                        )
                    )
                )
        )


        /*// زر الإغلاق
        if (showCloseButton) {
            IconButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top=48.dp,end= 32.dp)
                    .size(36.dp)
                    .background(color = Color.Black.copy(alpha = 0.5f), shape = RoundedCornerShape(50.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color(0xFFFF6600),
                    modifier = Modifier.size(36.dp)
                )
            }
        }*/

        AnimatedVisibility(

            visible = visible,
            enter = fadeIn(animationSpec = tween(400)) + scaleIn(
                initialScale = 0.9f,
                animationSpec = tween(400)
            ),
            exit = fadeOut(animationSpec = tween(300)) + scaleOut(
                targetScale = 0.85f,
                animationSpec = tween(300)
            ),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(24.dp)
                    .padding(bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (showCloseButton) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(30.dp)
                                .background(Color.Transparent, shape = RoundedCornerShape(50))
                                .border(2.dp, Color.White, shape = RoundedCornerShape(50))
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                }
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Unlock the Full Experience!",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Sign in or create an account to like, comment, and enjoy exclusive features tailored just for you.",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    Button(
                        onClick = onLogin,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(bottomStart = 12.dp, topStart = 12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        elevation = null
                    ) {
                        Text(
                            "Login",
                            color = Color(0xFFFF6600),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                    Button(
                        onClick = onSignUp,
                        modifier = Modifier.weight(1f).height(48.dp),
                        shape = RoundedCornerShape(bottomEnd = 12.dp, topEnd = 12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6600))
                    ) {
                        Text(
                            "Sign up",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RequireLoginPromptPreview() {
    RequireLoginPrompt(
        onLogin = {},
        onSignUp = {},
        onDismiss = {}
    )
}
