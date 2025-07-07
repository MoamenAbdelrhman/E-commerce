package com.project.e_commerce.android.presentation.ui.composable.composableScreen.public

import android.net.Uri
import androidx.annotation.OptIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_ALWAYS

//@OptIn(UnstableApi::class)
//@Composable
//fun VideoPlayer(uri: Uri, modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaItem.fromUri(uri))
//            prepare()
//            playWhenReady = true
//            addListener(object : Player.Listener {
//                override fun onPlaybackStateChanged(state: Int) {
//                    if (state == Player.STATE_ENDED) {
//                        seekTo(0)
//                        playWhenReady = true
//                    }
//                }
//            })
//        }
//    }
//
//    var isPlaying by remember { mutableStateOf(true) }
//
//    // Function to toggle playback
//    fun togglePlayback() {
//        if (isPlaying) {
//            exoPlayer.pause()
//        } else {
//            exoPlayer.play()
//        }
//        isPlaying = !isPlaying
//    }
//
//    val lifecycleOwner = LocalLifecycleOwner.current
//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            if (event == Lifecycle.Event.ON_PAUSE) {
//                exoPlayer.pause()
//                isPlaying = false
//            } else if (event == Lifecycle.Event.ON_RESUME && isPlaying) {
//                exoPlayer.play()
//            }
//        }
//        lifecycleOwner.lifecycle.addObserver(observer)
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//            exoPlayer.release()
//        }
//    }
//
//    Box(modifier = modifier
//        .fillMaxSize()
//        .clickable { togglePlayback() }) {
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = false
//                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//        IconButton(
//            onClick = { togglePlayback() },
//            modifier = Modifier.align(Alignment.Center)
//        ) {
//            if (!isPlaying)
//                Icon(
//                    imageVector = Icons.Default.PlayArrow,
//                    contentDescription = if (isPlaying) "Pause" else "Play",
//                    modifier = Modifier.size(128.dp),
//                    tint = Color.White
//                )
//            else Icon(
//                imageVector = Icons.Default.PlayArrow,
//                contentDescription = if (isPlaying) "Pause" else "Play",
//                modifier = Modifier.size(128.dp),
//                tint = Color.Transparent
//            )
//        }
//    }
//}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(uri: Uri, isPlaying: Boolean, onPlaybackStarted: () -> Unit) {
    val context = LocalContext.current
        var isPlayingVideo by remember { mutableStateOf(true) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = isPlaying
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                exoPlayer.pause()
                isPlayingVideo = false
            } else if (event == Lifecycle.Event.ON_RESUME && isPlaying) {
                exoPlayer.play()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            exoPlayer.release()
        }
    }

    // Function to toggle playback
    fun togglePlayback() {
        if (isPlayingVideo) {
            exoPlayer.pause()
        } else {
            exoPlayer.play()
        }
        isPlayingVideo = !isPlayingVideo
    }


    DisposableEffect(Unit) {
        onPlaybackStarted()
        onDispose {
            exoPlayer.release()
        }
    }


    // Update player state based on isPlaying
    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            exoPlayer.play()
        } else {
            exoPlayer.pause()
        }
    }

//    Box(Modifier.fillMaxSize()) {
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = false
//                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
//                }
//            },
//            modifier = Modifier.fillMaxSize()
//        )
//
//    }

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { togglePlayback() }) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    useController = false
                    setShowBuffering(SHOW_BUFFERING_ALWAYS)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        IconButton(
            onClick = { togglePlayback() },
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (!isPlayingVideo)
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = if (isPlayingVideo) "Pause" else "Play",
                    modifier = Modifier.size(128.dp),
                    tint = Color.White
                )
            else Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = if (isPlayingVideo) "Pause" else "Play",
                modifier = Modifier.size(128.dp),
                tint = Color.Transparent
            )
        }
    }

}

