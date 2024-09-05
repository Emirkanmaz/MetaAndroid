package com.example.littlelemon

import android.widget.Gallery
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableGalleryScreen() {

}

@Composable
fun GalleryCell() {
    Card(
        elevation = 16.dp,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(height = 180.dp, width = 180.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.greeksalad),
                contentDescription = "Greek Salad"
            )
            Text()

        }
    }
}