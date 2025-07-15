package com.project.e_commerce.android.presentation.ui.screens

// HeartAnimation.kt
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HeartAnimation(
    isVisible: Boolean,
    position: Offset,
    iconPainter: Painter,
    onAnimationEnd: () -> Unit,
    modifier: Modifier = Modifier,
    iconSize: androidx.compose.ui.unit.Dp = 70.dp,
    iconColor: Color = Color.Red
) {
    if (isVisible) {
        val density = LocalDensity.current
        val iconSizePx = with(density) { iconSize.toPx() }
        val coroutineScope = rememberCoroutineScope()

        // Animation values
        val scale = remember { Animatable(1f) }
        val alpha = remember { Animatable(1f) }
        val pulseScale = remember { Animatable(1f) }

        // Start animation when visible
        LaunchedEffect(isVisible) {
            coroutineScope.launch {
                // Phase 1: Pulse animation (2 small pulses)
                repeat(2) {
                    pulseScale.animateTo(
                        targetValue = 1.2f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                    pulseScale.animateTo(
                        targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 100,
                            easing = FastOutSlowInEasing
                        )
                    )
                }

                // Phase 2: Brief pause
                delay(100)

                // Phase 3: Scale up and fade out
                val finalAnimations = listOf(
                    async {
                        scale.animateTo(
                            targetValue = 1.8f,
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = FastOutSlowInEasing
                            )
                        )
                    },
                    async {
                        alpha.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(
                                durationMillis = 400,
                                easing = LinearEasing
                            )
                        )
                    }
                )

                // Wait for both animations to complete
                finalAnimations.awaitAll()
                onAnimationEnd()
            }
        }

        Box(
            modifier = modifier
                .fillMaxSize()
                .offset {
                    IntOffset(
                        (position.x - iconSizePx / 2).toInt(),
                        (position.y - iconSizePx / 2).toInt()
                    )
                }
        ) {
            Icon(
                painter = iconPainter,
                contentDescription = null,
                tint = iconColor.copy(alpha = alpha.value),
                modifier = Modifier
                    .size(iconSize)
                    .scale(scale.value * pulseScale.value)
                    .alpha(alpha.value)
            )
        }
    }
}