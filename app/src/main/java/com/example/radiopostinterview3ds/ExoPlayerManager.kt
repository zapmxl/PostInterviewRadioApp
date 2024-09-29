// ExoPlayerManager.kt
package com.example.radiopostinterview3ds

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class ExoPlayerManager(context: Context) {
    private val player: ExoPlayer = ExoPlayer.Builder(context).build()

    fun playStream(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    fun pause() {
        player.pause()
    }

    fun stop() {
        player.stop()
    }

    fun release() {
        player.release()
    }
}
