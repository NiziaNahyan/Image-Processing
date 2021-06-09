package com.example.myapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

public class picassoimage extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picassoimage);
        //Initializing the ImageView
        imageView = (ImageView) findViewById(R.id.imageView);
        //Loading Image from URL
        Picasso.with(this)
                .load("http://192.168.0.104/AndroidUploadImage/uploads/1.jpg")
                .placeholder(R.drawable.placeholder)   // optional
                .error(R.drawable.error)      // optional
                .resize(400,400)                        // optional
                .into(imageView);
    }
    }

