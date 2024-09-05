package com.littlelemon.settinguproom

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .build()
        val playerDao = db.playerDao()

//        lifecycleScope.launch {
//            val players = withContext(Dispatchers.IO) {
//                playerDao.getAll()
//            }
//        }

        val playerTable: PlayerDao = db.playerDao()

        lifecycleScope.launch {
//            withContext(Dispatchers.IO){
//                playerTable.insertAll(Player(1,"TestName", 20))
//            }
            val players: List<Player> = withContext(Dispatchers.IO) {
                playerTable.getAll()
            }
            players.forEach { Log.d("db",  "id: ${it.id} name: ${it.name} age: ${it.age}")}
        }
    }
}

@Entity
data class Player(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int
)

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): List<Player>

    @Insert
    fun insertAll(vararg player: Player)

    @Delete
    fun delete(player: Player)
}

@Database(entities = [Player::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}