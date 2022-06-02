package ru.mirea.getcar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }


    override fun onBackPressed() {

    }

    override fun onDestroy() {
        super.onDestroy()
        val sharedPref = this.getPreferences(MODE_PRIVATE)
        sharedPref.edit().remove("login").apply()
    }
}