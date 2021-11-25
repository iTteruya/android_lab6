package com.example.lab6_2

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService

class MainViewModel: ViewModel() {
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    private val executorService: ExecutorService by lazy { MyApplication.executorService }

    fun downloadPic() {
            executorService.execute {
                bitmap.postValue(
                    BitmapFactory
                        .decodeStream(URL(MainActivity.IMG).openConnection().getInputStream())
                )
            }
        }

}