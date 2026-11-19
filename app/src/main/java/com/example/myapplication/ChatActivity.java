package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        TextView home = findViewById(R.id.navHome);
        TextView podcasts = findViewById(R.id.navPodcasts);
        TextView chat = findViewById(R.id.navChat);
        TextView activity = findViewById(R.id.navActivity);

        home.setOnClickListener(v -> finish());

        podcasts.setOnClickListener(v -> {
            startActivity(new Intent(this, PodcastsActivity.class));
            finish();
        });

        chat.setOnClickListener(v ->
                Toast.makeText(this, "You're already in Chat",
                        Toast.LENGTH_SHORT).show());

        activity.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityActivity.class));
            finish();
        });
    }
}