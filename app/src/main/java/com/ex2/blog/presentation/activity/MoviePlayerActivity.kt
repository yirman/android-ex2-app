package com.ex2.blog.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ex2.blog.databinding.ActivityMoviePlayerBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MoviePlayerActivity : AppCompatActivity() {

    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var videoUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMoviePlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videoUrl = "https://github.com/yirman/json/raw/main/Nuestra%20escritura%20nos%20representa%20Estrella%20Montol%C3%ADo%20ling%C3%BCista%20y%20profesora_v240P.mp4"

        videoUrl?.let{ videoUrl ->
            simpleExoPlayer = SimpleExoPlayer.Builder(binding.root.context).build()
            binding.playervideo.player = simpleExoPlayer
            val mediaItem = MediaItem.fromUri(videoUrl)
            simpleExoPlayer?.addMediaItem(mediaItem)
            simpleExoPlayer?.prepare()
            simpleExoPlayer?.playWhenReady = true;
        }
    }


    override fun onResume() {
        super.onResume()
        simpleExoPlayer?.playWhenReady = true
    }

    override fun onPause() {
        simpleExoPlayer?.playWhenReady = false
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer?.release()
        simpleExoPlayer = null
    }
}