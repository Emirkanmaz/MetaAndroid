package com.example.littlelemonfinal

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.emirkanmaz.littlelemonfinal.R
import com.emirkanmaz.littlelemonfinal.ui.theme.yellow


@Composable
fun LowerPanel(navController: NavHostController, dishes: List<Dish> = listOf()) {
    Column(modifier = Modifier.background(Color.White)) {
        WeeklySpecialCard()
        LazyColumn(modifier = Modifier.background(Color.White)) {
            itemsIndexed(dishes) { _, dish ->
                MenuDish(navController, dish)
            }
        }
    }
}

@Composable
fun WeeklySpecialCard() {
    Row(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.weekly_special),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .padding(8.dp)
        )
    }
}

@Composable
fun MenuDish(navController: NavHostController? = null, dish: Dish) {
    Card(
        modifier = Modifier.background(Color.White),
        onClick = {
        Log.d("AAA", "Click ${dish.id}")
        navController?.navigate(DishDetails.route + "/${dish.id}")
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.background(Color.White)) {
                Text(text = dish.name, style = MaterialTheme.typography.displayMedium)
                Text(
                    text = dish.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .padding(top = 5.dp, bottom = 5.dp)
                )
                Text(text = "$${dish.price}", style = MaterialTheme.typography.bodyMedium)
            }
            Image(
                painter = painterResource(id = dish.imageResource),
                contentDescription = dish.name,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))

            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = yellow,
    )
}
