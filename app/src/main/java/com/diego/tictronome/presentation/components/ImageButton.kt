package com.diego.tictronome.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonDefaults

@Composable
fun ImageButton(icon: Int, onClick: () -> Unit, modifier: Modifier = Modifier, iconDescription: String) {
    Button(onClick = onClick, modifier = modifier, colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary)) {
        Image(painter = painterResource(icon), contentDescription = iconDescription)
    }
}