
package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        TextView author = findViewById(R.id.detailAuthor);
        TextView date = findViewById(R.id.detailDate);
        TextView title = findViewById(R.id.detailTitle);
        TextView content = findViewById(R.id.detailContent);
        TextView back = findViewById(R.id.detailBack);

        author.setText(getIntent().getStringExtra("author"));
        date.setText(getIntent().getStringExtra("date"));
        title.setText(getIntent().getStringExtra("title"));
        content.setText(getIntent().getStringExtra("content"));

        back.setOnClickListener(v -> finish());
    }
}
