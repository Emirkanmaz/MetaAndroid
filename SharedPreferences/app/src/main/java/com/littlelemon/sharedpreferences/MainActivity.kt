package com.littlelemon.sharedpreferences

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.littlelemon.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val sharedPreferences = getSharedPreferences("Little Lemon", MODE_PRIVATE)
        val lastCount = sharedPreferences.getInt("StartCount", 0)
        val newCount = lastCount + 1

        Log.d("StartCount", "NewCount: $newCount")
        sharedPreferences.edit().putInt("StartCount", newCount).apply()

    }

}