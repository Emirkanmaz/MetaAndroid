package com.littlelemon.httprequestresponseinkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.MutableLiveData
import com.littlelemon.httprequestresponseinkotlin.ui.theme.HTTPRequestResponseinKotlinTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private var responseLiveData = MutableLiveData<String>()

    private val httpClient = HttpClient(Android)

    private suspend fun fetchContent(): String{
        return httpClient.get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json").bodyAsText()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            HTTPRequestResponseinKotlinTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val responseState = responseLiveData.observeAsState().value


                    Column(modifier = Modifier.fillMaxSize()) {

                        Text(text = responseState.toString())

                        Button(onClick = {
                            lifecycleScope.launch {
                                val response = fetchContent()
                                runOnUiThread { responseLiveData.value = response }
                            }
                        }) {
                            Text("Download")
                        }
                    }
                }
            }
        }
    }
}

