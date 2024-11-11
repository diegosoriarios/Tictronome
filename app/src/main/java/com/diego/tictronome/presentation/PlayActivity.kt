package com.diego.tictronome.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.diego.tictronome.R
import com.diego.tictronome.presentation.components.ImageButton
import com.diego.tictronome.presentation.managers.VibrationManager
import com.diego.tictronome.presentation.ui.theme.TictronomeTheme


class PlayActivity : ComponentActivity() {
    lateinit var vibrationManager: VibrationManager
    private lateinit var runnable: Runnable

    val mainHandler = Handler(Looper.getMainLooper())
    fun play(bpm: Int) {
        if (bpm == 0) {
            vibrationManager.stop()
            finish()
            return
        }

        runnable = object : Runnable {
            override fun run() {
                vibrationManager.play()
                mainHandler.postDelayed(this, ((60 * 1000) / bpm).toLong())
            }
        }

        mainHandler.post(runnable)
    }

    override fun onStop() {
        super.onStop()
        mainHandler.removeCallbacks(runnable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vibrationManager = VibrationManager(this)
        val myIntent = intent // gets the previously created intent
        val bpm = myIntent.getIntExtra("bpm", 0)

        play(bpm)

        setContent {
            TictronomeTheme {
                // A surface container using the 'background' color from the theme
                PlayingView(vibrationManager, bpm, ::finish)
            }
        }
    }
}

@Composable
fun PlayingView(vibrationManager: VibrationManager, bpm: Int, back: () -> Unit ) {
    val mainHandler = Handler(Looper.getMainLooper())

    fun handleStop() {
        vibrationManager.stop()
        mainHandler.removeMessages(0)
        back()
    }

    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageButton(icon = R.drawable.stop, onClick = { handleStop() }, iconDescription = "Stop")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TictronomeTheme {
        Text("Android")
    }
}