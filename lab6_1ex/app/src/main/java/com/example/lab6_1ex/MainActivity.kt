package com.example.lab6_1ex

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import java.util.concurrent.ExecutorService

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView

    private var isRunning = false

    private val executorService: ExecutorService by lazy { MyApplication.executorService }
    private val sharedPref: SharedPreferences? by lazy { getPreferences(Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)

    }

    override fun onStart() {
        super.onStart()
        isRunning = true
        secondsElapsed = sharedPref?.getInt(STATE_TIME, 0) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed, secondsElapsed)

        executorService.execute {
            Log.d(TAG, "Task is running on ${Thread.currentThread().name}")
            while (isRunning) {
                Thread.sleep(1000)
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds_elapsed, secondsElapsed++)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        isRunning = false
        sharedPref?.edit()
            ?.putInt(STATE_TIME, secondsElapsed)
            ?.apply()
    }

    private companion object {
        private const val STATE_TIME = "secondsElapsed"
        private const val TAG = "MainActivity"
    }
}