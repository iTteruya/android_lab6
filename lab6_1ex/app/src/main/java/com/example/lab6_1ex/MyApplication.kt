package com.example.lab6_1ex

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MyApplication: Application() {

    companion object {
        val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    }
}