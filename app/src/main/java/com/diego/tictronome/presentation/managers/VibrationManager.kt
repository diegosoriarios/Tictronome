package com.diego.tictronome.presentation.managers

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.util.Log


class VibrationManager(context: Context) {
    private val vibrator: Vibrator?
    private val hasVibrator: Boolean

    init {
        vibrator = try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
                vibratorManager?.defaultVibrator
            } else {
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
            }
        } catch (e: Exception) {
            null
        }
        hasVibrator = vibrator?.hasVibrator() ?: false
    }

    fun play(): Boolean {
        if (!hasVibrator || vibrator == null) {
            return false
        }
        
        return try {
            val vibratePattern = longArrayOf(0, VIBRATION_DURATION, SLEEP_DURATION)
            val effect = VibrationEffect.createWaveform(vibratePattern, 0)
            vibrator.vibrate(effect)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun stop() {
        if (hasVibrator && vibrator != null) {
            try {
                vibrator.cancel()
            } catch (e: Exception) {
                // Ignore cancellation errors
            }
        }
    }

    companion object {
        private const val VIBRATION_DURATION = 500L
        private const val SLEEP_DURATION = 1500L
    }
}
