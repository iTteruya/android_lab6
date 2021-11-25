package com.example.lab6_4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var dwnld: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dwnld = findViewById(R.id.downloadButton)
        imageView = findViewById(R.id.image)

        dwnld.setOnClickListener {
            Picasso.get().load(IMG).into(imageView)
        }
    }


    companion object {
        const val IMG = "https://www.hse.ru/data/2020/12/02/1354734762/10_15.png"
    }
}
