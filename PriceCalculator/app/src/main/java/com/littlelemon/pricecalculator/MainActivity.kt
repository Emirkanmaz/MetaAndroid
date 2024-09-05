package com.littlelemon.pricecalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.littlelemon.pricecalculator.ui.theme.PriceCalculatorTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PriceCalculatorTheme {
                SimpleUI {
                    Toast.makeText(this, "Data saved $it times", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

fun disableThenExecute(action: (View) -> Unit): (View) -> Unit {
    return { view ->
        view.isEnabled = false
        action(view)
    }
}

@Composable
fun SimpleUI(onClick: (Int) -> Unit) {
    var counts by rememberSaveable { mutableStateOf(1) }
    var isEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (isEnabled) {
                    isEnabled = false
                    onClick(counts++)
                    // Re-enable the button after a short delay
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000) // 500ms delay, adjust as needed
                        isEnabled = true
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth(.8f)
                .fillMaxHeight(.1f),
            enabled = isEnabled
        ) {
            Text(text = "Button $counts")
        }
    }
}

@Composable
fun ListenerButton(modifier: Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show() }) {
            Text(text = "Save Data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListenerButtonPreview() {
    ListenerButton(modifier = Modifier.padding())
}