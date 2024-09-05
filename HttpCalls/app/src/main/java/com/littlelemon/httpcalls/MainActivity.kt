package com.littlelemon.httpcalls

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.littlelemon.httpcalls.ui.theme.HttpCallsTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json



@Serializable
class MenuCategory(
    val menu: List<String>
)

@Serializable
data class Menu(
    val Appetizers: MenuCategory,
    val Salads: MenuCategory,
    val Drinks: MenuCategory,
    val Dessert: MenuCategory
)

class MainActivity : ComponentActivity() {

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType.Text.Plain)
        }
    }

    val menuItemsLiveData = MutableLiveData<List<String>>()


    private suspend fun getMenu(category: String): List<String>{
        val response: String = client
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/littleLemonMenu.json")
            .bodyAsText()

        val json = Json { ignoreUnknownKeys = true }
        val menu = json.decodeFromString<Menu>(response)

        return when (category){
            "Appetizers" -> menu.Appetizers.menu
            "Salads" -> menu.Salads.menu
            "Drinks" -> menu.Drinks.menu
            "Dessert" -> menu.Dessert.menu
            else -> emptyList()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            HttpCallsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val items = menuItemsLiveData.observeAsState(emptyList())
                        MenuItems(items.value)
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            Button(
                                onClick = {
                                    lifecycleScope.launch{
                                        val menuItems = getMenu("Salads")

                                        runOnUiThread{
                                            menuItemsLiveData.value = menuItems
                                        }
                                    }
                                }
                            ) {
                                Text(text = "Download Salads")
                            }
                            Button(
                                onClick = {
                                    lifecycleScope.launch{
                                        val menuItems = getMenu("Appetizers")

                                        runOnUiThread{
                                            menuItemsLiveData.value = menuItems
                                        }
                                    }
                                }
                            ) {
                                Text(text = "Download Appetizers")
                            }

                        }
                        Row(modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center) {
                            Button(
                                onClick = {
                                    lifecycleScope.launch{
                                        val menuItems = getMenu("Dessert")

                                        runOnUiThread{
                                            menuItemsLiveData.value = menuItems
                                        }
                                    }
                                }
                            ) {
                                Text(text = "Download Dessert")
                            }
                            Button(
                                onClick = {
                                    lifecycleScope.launch{
                                        val menuItems = getMenu("Drinks")

                                        runOnUiThread{
                                            menuItemsLiveData.value = menuItems
                                        }
                                    }
                                }
                            ) {
                                Text(text = "Download Drinks")
                            }
                        }

                    }
                }

            }
        }
    }

}


@Composable
fun MenuItems(items: List<String> = emptyList()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        LazyColumn {
            itemsIndexed(items) { _, item ->
                MenuItemDetails(item)
            }
        }
    }
}

@Composable
fun MenuItemDetails(menuItem: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = menuItem)
    }
}





