package com.example.vidhyaniketan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import pl.droidsonroids.gif.GifImageView; // Import GifImageView

public class SplashActivity extends AppCompatActivity {

    private GifImageView gifImageView; // Declare GifImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // Initialize the GifImageView
        gifImageView = findViewById(R.id.gifImageView); // Make sure this ID matches your XML layout

        // Start playing the GIF
        gifImageView.setVisibility(GifImageView.VISIBLE); // Ensure it's visible

        Intent iHome = new Intent(SplashActivity.this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(iHome);
                finish();
            }
        }, 3000);

        // Optional: You can stop the GIF when transitioning to the next activity
        // gifImageView.setVisibility(GifImageView.GONE); // Hide it if needed
    }
}