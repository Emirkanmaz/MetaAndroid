package com.littlelemon.httprequestresponseinkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.httprequestresponseinkotlin.ui.theme.HTTPRequestResponseinKotlinTheme


class ImageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HTTPRequestResponseinKotlinTheme {
                Scaffold { PaddingValues ->
                    MyView(PaddingValues)
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyView(padding: PaddingValues) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        GlideImage(modifier = Modifier.fillMaxSize(), model ="https://images.pexels.com/photos/3768263/pexels-photo-3768263.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500" , contentDescription = "Glide Image")
    }
}