package com.example.lab6_3

import androidx.lifecycle.ViewModel
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel: ViewModel() {
    val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    fun downloadPic() {
        viewModelScope.launch {
            bitmap.value = withContext(Dispatchers.IO) {
                val url = URL(MainActivity.IMG)
                val stream = url.openStream()
                BitmapFactory.decodeStream(stream)
            }
        }
    }
}