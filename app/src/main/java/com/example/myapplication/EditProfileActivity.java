
package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    EditText nameInput, usernameInput, bioInput, emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        nameInput = findViewById(R.id.editName);
        usernameInput = findViewById(R.id.editUsername);
        bioInput = findViewById(R.id.editBio);
        emailInput = findViewById(R.id.editEmail);

        TextView cancel = findViewById(R.id.cancelEdit);
        TextView save = findViewById(R.id.saveProfile);

        // Load existing profile details
        android.content.SharedPreferences prefs =
                getSharedPreferences("UserProfile", MODE_PRIVATE);

        nameInput.setText(prefs.getString("name", "Gurnoor Kaur"));
        usernameInput.setText(prefs.getString("username", "gurnoorkaur0194"));
        bioInput.setText(prefs.getString("bio",
                "Collecting thoughts, stories and little things that make life interesting. ✨"));
        emailInput.setText(prefs.getString("email", "gurnoor@example.com"));

        cancel.setOnClickListener(v -> finish());

        save.setOnClickListener(v -> saveProfile());
    }

    private void saveProfile() {

        String name = nameInput.getText().toString().trim();
        String username = usernameInput.getText().toString().trim();
        String bio = bioInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();

        if (name.isEmpty() || username.isEmpty()) {
            Toast.makeText(this,
                    "Name and username cannot be empty!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.isEmpty() &&
                !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Enter a valid email address");
            return;
        }

        android.content.SharedPreferences prefs =
                getSharedPreferences("UserProfile", MODE_PRIVATE);

        prefs.edit()
                .putString("name", name)
                .putString("username", username)
                .putString("bio", bio)
                .putString("email", email)
                .apply();

        Toast.makeText(this, "Profile updated! ✨",
                Toast.LENGTH_SHORT).show();

        finish();
    }
}
