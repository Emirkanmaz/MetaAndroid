package com.littlelemon.dataassignment

import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.littlelemon.dataassignment.ui.theme.DataAssignmentTheme
import com.littlelemon.dataassignment.ui.theme.Yellow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, MenuItemDao.AppDatabase::class.java, "database")
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataAssignmentTheme {

                // add databaseMenuItems code here
                val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

                // add orderMenuItems variable here
                var orderMenuItems by remember {
                    mutableStateOf(false)
                }

                // add menuItems variable here
                var menuItems = if (orderMenuItems) {
                    databaseMenuItems.sortedBy { it.title }
                } else {
                    databaseMenuItems
                }

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "logo",
                        modifier = Modifier.padding(50.dp)
                    )
                    // add Button code here
                    Button(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .width(200.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Yellow,
                            contentColor = Color.Black
                        ),
                        onClick = { orderMenuItems = true }) {
                        Text(text = "Tap to Order By Name")
                    }

                    // add searchPhrase variable here
                    var searchPhrase by remember {
                        mutableStateOf("")
                    }

                    // Add OutlinedTextField
                    OutlinedTextField(
                        value = searchPhrase,
                        onValueChange = { searchPhrase = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, end = 50.dp),
                        label = { Text(text = "Search") }
                    )

                    // add is not empty check here
                    if(searchPhrase.isNotEmpty()){
                        menuItems = menuItems.filter { it.title.lowercase().contains(searchPhrase.lowercase()) }
                    }


                    MenuItemsList(items = menuItems)
                }
            }
        }
        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuItemDao().isEmpty()) {
                val menu = fetchMenu()
                saveMenuToDatabase(menu)
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {

        // data URL: https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json
        val url =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonSimpleMenu.json"
        return httpClient.get(url).body<MenuNetwork>().menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

@Composable
private fun MenuItemsList(items: List<MenuItemRoom>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 20.dp)
    ) {
        items(
            items = items,
            itemContent = { menuItem ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(menuItem.title)
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Right,
                        text = "%.2f".format(menuItem.price)
                    )
                }
            }
        )
    }
}