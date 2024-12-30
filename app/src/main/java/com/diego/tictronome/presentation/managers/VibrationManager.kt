package com.diego.tictronome.presentation.managers

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log


class VibrationManager(context: Context) {
    private val vibrator: Vibrator

    init {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibrator = vibratorManager.defaultVibrator
    }

    fun play() {
        val DELAY = 0
        val VIBRATE = 500
        val SLEEP = 1500
        val START = 0
        val vibratePattern = longArrayOf(DELAY.toLong(), VIBRATE.toLong(), SLEEP.toLong())

        vibrator.vibrate(VibrationEffect.createWaveform(vibratePattern, START))

        //MAYBE PLAY A TONE?
        //val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        //toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
    }

    fun stop() {
        vibrator.cancel()
    }
}