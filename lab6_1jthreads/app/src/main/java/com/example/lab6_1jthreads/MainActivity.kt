package com.example.lab6_1jthreads

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView
    private var backgroundThread: Thread? = null

    private val sharedPref: SharedPreferences? by lazy { getPreferences(Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
    }

    override fun onStart() {
        super.onStart()
        secondsElapsed = sharedPref?.getInt(STATE_TIME, 0) ?: 0
        textSecondsElapsed.text = getString(R.string.seconds_elapsed, secondsElapsed)

        backgroundThread = Thread {
            Log.d(TAG, "The thread has been created")
            try {
                while (!Thread.currentThread().isInterrupted) {
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text =
                            getString(R.string.seconds_elapsed, secondsElapsed++)
                    }
                }
            } catch (e: InterruptedException) {
                Log.i(TAG, "The thread has been interrupted", e)
            }
            Log.d(TAG, "The thread is dead")
        }
        backgroundThread?.start()

    }

    override fun onStop() {
        backgroundThread?.interrupt()
        backgroundThread = null
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