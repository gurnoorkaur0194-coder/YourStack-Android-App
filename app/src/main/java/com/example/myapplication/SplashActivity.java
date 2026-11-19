
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View content = findViewById(R.id.splashContent);

        // Fade-in animation
        AlphaAnimation fade = new AlphaAnimation(0f, 1f);
        fade.setDuration(1300);

        // Gentle zoom animation
        ScaleAnimation zoom = new ScaleAnimation(
                0.85f, 1f,
                0.85f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
        );

        zoom.setDuration(1300);

        content.startAnimation(fade);
        content.startAnimation(zoom);

        // Open Home after 2 seconds
        content.postDelayed(() -> {
            Intent intent = new Intent(
                    SplashActivity.this,
                    MainActivity.class
            );

            startActivity(intent);
            finish();

        }, 2200);
    }
}
