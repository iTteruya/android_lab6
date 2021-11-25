package com.example.lab6_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var dwnld: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        dwnld = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.image)

        dwnld.setOnClickListener {
                viewModel.downloadPic()
        }

        viewModel.bitmap.observe(this) {
                imageView.setImageBitmap(it)
        }
    }


    companion object {
        const val IMG = "https://www.hse.ru/data/2020/12/02/1354734762/10_15.png"
    }
}
