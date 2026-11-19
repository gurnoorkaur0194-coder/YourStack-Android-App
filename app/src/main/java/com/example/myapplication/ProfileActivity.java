
package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileUsername, profileBio, profileEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileName = findViewById(R.id.profileName);
        profileUsername = findViewById(R.id.profileUsername);
        profileBio = findViewById(R.id.profileBio);
        profileEmail = findViewById(R.id.profileEmail);

        TextView back = findViewById(R.id.profileBack);
        TextView edit = findViewById(R.id.editProfile);

        TextView home = findViewById(R.id.navHome);
        TextView podcasts = findViewById(R.id.navPodcasts);
        TextView chat = findViewById(R.id.navChat);
        TextView activity = findViewById(R.id.navActivity);

        back.setOnClickListener(v -> finish());
        home.setOnClickListener(v -> finish());

        podcasts.setOnClickListener(v -> {
            startActivity(new Intent(this, PodcastsActivity.class));
            finish();
        });

        chat.setOnClickListener(v -> {
            startActivity(new Intent(this, ChatActivity.class));
            finish();
        });

        activity.setOnClickListener(v -> {
            startActivity(new Intent(this, ActivityActivity.class));
            finish();
        });

        edit.setOnClickListener(v ->
                startActivity(new Intent(this, EditProfileActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProfile();
    }

    private void loadProfile() {

        SharedPreferences prefs =
                getSharedPreferences("UserProfile", MODE_PRIVATE);

        profileName.setText(
                prefs.getString("name", "Gurnoor Kaur"));

        profileUsername.setText(
                prefs.getString("username", "gurnoorkaur0194"));

        profileBio.setText(
                prefs.getString("bio",
                        "Collecting thoughts, stories and little things that make life interesting. ✨"));

        profileEmail.setText(
                "✉  " + prefs.getString("email", "gurnoor@example.com"));
    }
}
