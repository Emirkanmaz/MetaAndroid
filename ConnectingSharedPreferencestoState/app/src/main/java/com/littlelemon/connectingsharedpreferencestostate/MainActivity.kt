package com.littlelemon.connectingsharedpreferencestostate

import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.littlelemon.connectingsharedpreferencestostate.ui.theme.ConnectingSharedPreferencesToStateTheme

class MainActivity : ComponentActivity() {

    private val showLunchMenuLiveData = MutableLiveData<Boolean>()

    private val sharedPreferences by lazy {
        getSharedPreferences("LittleLemon", MODE_PRIVATE)
    }

    private val sharedPreferencesListener =
        OnSharedPreferenceChangeListener { sharedPreferences, key ->
            if (key == "LunchMenu") {
                showLunchMenuLiveData.value = sharedPreferences.getBoolean(key, false)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showLunchMenuLiveData.value = sharedPreferences.getBoolean("LunchMenu", false)
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)

        setContent {
            ConnectingSharedPreferencesToStateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Column() {
                        val selected = showLunchMenuLiveData.observeAsState()
                        Switch(checked = selected.value!!, onCheckedChange = {
                            sharedPreferences.edit(commit = true){
                                putBoolean("LunchMenu", it)
                            }
                        })
                        Title(showLunchMenu = selected.value!!)
                    }
                }
            }
        }
    }
}

@Composable
fun Title(showLunchMenu: Boolean) {
    val text = if (showLunchMenu) {
        "Lunch Menu"
    } else {
        "Breakfast Menu"
    }
    Text(text)
}