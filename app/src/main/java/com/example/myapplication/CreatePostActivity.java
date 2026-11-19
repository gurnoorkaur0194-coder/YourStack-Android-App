
package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class CreatePostActivity extends AppCompatActivity {

    EditText postTitle, postContent;
    TextView publishPost, cancelPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        postTitle = findViewById(R.id.postTitle);
        postContent = findViewById(R.id.postContent);
        publishPost = findViewById(R.id.publishPost);
        cancelPost = findViewById(R.id.cancelPost);

        cancelPost.setOnClickListener(v -> finish());

        publishPost.setOnClickListener(v -> publish());
    }

    private void publish() {

        String title = postTitle.getText().toString().trim();
        String content = postContent.getText().toString().trim();

        if (title.length() == 0) {
            postTitle.setError("Please enter a title");
            return;
        }

        if (content.length() == 0) {
            postContent.setError("Please write something");
            return;
        }

        try {
            android.content.SharedPreferences preferences =
                    getSharedPreferences("MyPosts", MODE_PRIVATE);

            String savedPosts = preferences.getString("posts", "[]");

            JSONArray posts = new JSONArray(savedPosts);

            JSONObject newPost = new JSONObject();
            newPost.put("title", title);
            newPost.put("content", content);
            newPost.put("date", System.currentTimeMillis());

            posts.put(newPost);

            preferences.edit()
                    .putString("posts", posts.toString())
                    .apply();

            setResult(RESULT_OK);
            Toast.makeText(this, "Post published!", Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this, "Could not publish post", Toast.LENGTH_SHORT).show();
        }
    }
}
