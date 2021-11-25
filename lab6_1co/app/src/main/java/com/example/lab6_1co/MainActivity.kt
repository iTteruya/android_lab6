package com.example.lab6_1co

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    private val sharedPref: SharedPreferences? by lazy { getPreferences(Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

        lifecycleScope.launchWhenResumed {
            Log.d(TAG, "Coroutine has started")
            while (isActive) {
                delay(1000L)
                textSecondsElapsed.text = getString(R.string.seconds_elapsed, secondsElapsed++)
            }
        }.apply {
            invokeOnCompletion { Log.d(TAG, "Coroutine has finished") }
        }
    }

    override fun onStart() {
        super.onStart()
        secondsElapsed = sharedPref?.getInt(STATE_TIME, 0) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed, secondsElapsed)
    }

    override fun onStop() {
        super.onStop()
        sharedPref?.edit()
            ?.putInt(STATE_TIME, secondsElapsed)
            ?.apply()
    }

    private companion object {
        private const val STATE_TIME = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}