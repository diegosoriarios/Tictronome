/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.diego.tictronome.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.diego.tictronome.R
import com.diego.tictronome.presentation.components.ImageButton
import com.diego.tictronome.presentation.theme.TictronomeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WearApp("Android")
        }
    }
}

@Composable
fun WearApp(greetingName: String) {
    val navController = rememberSwipeDismissableNavController()

    TictronomeTheme {
        /* If you have enough items in your list, use [ScalingLazyColumn] which is an optimized
         * version of LazyColumn for wear devices with some added features. For more information,
         * see d.android.com/wear/compose.
         */
        SwipeDismissableNavHost(
            navController = navController,
            startDestination = "home"
        ) {
            composable("home") {
                Greeting(navController)
            }
            composable("tap") {
                TapControllerScreen(navController, LocalContext.current)
            }
            composable("number") {
                BpmControllerScreen(navController, LocalContext.current)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TapControllerScreen(navController: NavController, context: Context) {
    var count: Long by remember { mutableStateOf(0) }
    var delta: Long by remember { mutableStateOf(0)}

    fun handleTap() {
        var temp = System.currentTimeMillis()
        count = 60000 / (temp - delta)
        delta = temp
    }

    fun handleStart() {
        val intent = Intent(context, PlayActivity::class.java)
        intent.putExtra("bpm", count.toInt())
        context.startActivity(intent)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .combinedClickable(
            onClick = {
                handleTap()
            },
            onLongClick = { },
        ), verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            text = "Tap: $count"
        )
        ImageButton(icon = R.drawable.play, onClick = { handleStart() }, iconDescription = "Play")
    }
}

@Composable
fun BpmControllerScreen(navController: NavController, context: Context) {
    var bpm by remember { mutableStateOf(100) }

    fun handleStart() {
        val intent = Intent(context, PlayActivity::class.java)
        intent.putExtra("bpm", bpm)
        context.startActivity(intent)
    }

    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageButton(icon = R.drawable.add, onClick = { bpm += 1 }, iconDescription = "Add")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.width(60.dp))
            Text(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                text = "$bpm"
            )
            ImageButton(icon = R.drawable.play, onClick = { handleStart() }, modifier = Modifier.padding(end = 12.dp), iconDescription = "Play")
        }
        ImageButton(icon = R.drawable.minus, onClick = { bpm -= 1 }, iconDescription = "Minus")
    }
}

@Composable
fun Greeting(navController: NavController) {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            text = "Tictronome"
        )
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            ImageButton(icon = R.drawable.tap, onClick = { navController.navigate("tap") }, iconDescription = "Tap")
            ImageButton(icon = R.drawable.number, onClick = { navController.navigate("number") }, iconDescription = "Number")
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WearApp("Preview Android")
}