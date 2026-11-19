
package com.example.myapplication;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.bakeSamosaStory).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BakeSamosaActivity.class);
            startActivity(intent);
        });

        // ANDREW'S FULL ARTICLE
        findViewById(R.id.postAndrewCard).setOnClickListener(v ->
                openPost(
                        "Andrew",
                        "The trick to put your phone down",
                        "We often tell ourselves that we will put our phones down in just five minutes. But those five minutes quietly turn into an hour.\n\nThe answer may be simpler than we think.\n\nSome monks practise being fully present in whatever they are doing. When they eat, they eat. When they walk, they walk. They do not constantly divide their attention between the present moment and a screen.\n\nTry this: keep your phone away during one small activity today. Have your tea without scrolling. Take a walk without checking notifications. Read a few pages without switching apps.\n\nYou don't have to disappear from the digital world. You just have to make room for the world around you.\n\nPerhaps the trick isn't to use your phone less by force, but to become more present in the life happening outside it.",
                        "2 days ago"
                )
        );

        // THE THINKING ROOM
        findViewById(R.id.postThinkingCard).setOnClickListener(v ->
                openPost(
                        "The Thinking Room",
                        "The internet made us more connected, but are we less understood?",
                        "We have never had so many ways to communicate. Messages travel instantly, photographs reach hundreds of people, and conversations continue across time zones.\n\nYet being reachable is not the same as being understood.\n\nOur digital conversations are often quick. We react, like, reply and move on. Sometimes we share everything about our day but struggle to explain how we actually feel.\n\nConnection needs more than constant communication. It needs attention, patience and the willingness to listen.\n\nPerhaps the question is not how many people we can reach, but how many people we genuinely connect with.",
                        "3 days ago"
                )
        );

        // LETTERS TO MYSELF
        findViewById(R.id.postLettersCard).setOnClickListener(v ->
                openPost(
                        "Letters to Myself",
                        "A reminder that you don't have to have everything figured out.",
                        "Dear you,\n\nYou are allowed to change your mind. You are allowed to begin again, even if you have already begun a hundred times.\n\nLife does not always move according to the plans we make. Sometimes the path becomes unclear, and sometimes we outgrow the dreams we once held close.\n\nThat does not mean you are falling behind.\n\nTake your time. Learn something new. Make mistakes. Rest when you need to. You do not have to turn every moment into an achievement.\n\nYou are a work in progress, and that is a beautiful thing.",
                        "4 days ago"
                )
        );

        // RITHANYA'S POST
        findViewById(R.id.postRithanyaCard).setOnClickListener(v ->
                openPost(
                        "Rithanya S",
                        "Sometimes, the smallest things stay with us the longest.",
                        "It is strange how ordinary moments become our favourite memories.\n\nA conversation that lasted longer than expected. A familiar song playing at the right time. Someone remembering a little detail about you.\n\nWe spend so much time waiting for extraordinary moments that we sometimes overlook the small ones already happening around us.\n\nMaybe happiness is not always a grand event. Maybe it is hidden in the quiet, everyday things we almost forget to notice.\n\nPay attention. The little things have a way of staying with us.",
                        "5 days ago"
                )
        );


        // CREATE POST BUTTON
        findViewById(R.id.createPostButton).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreatePostActivity.class);
            startActivity(intent);
        });

        // LIKE BUTTON
        TextView likeButton = findViewById(R.id.likeButton);

        final boolean[] liked = {false};
        final int[] likes = {3100};

        likeButton.setOnClickListener(v -> {
            liked[0] = !liked[0];

            if (liked[0]) {
                likes[0]++;
                likeButton.setText("♥  " + likes[0]);
                likeButton.setTextColor(
                        android.graphics.Color.parseColor("#E85D75"));
            } else {
                likes[0]--;
                likeButton.setText("♡  " + likes[0]);
                likeButton.setTextColor(
                        android.graphics.Color.parseColor("#AAAAAA"));
            }
        });

        // COMMENT BUTTON
        TextView commentButton = findViewById(R.id.commentButton);
        LinearLayout commentArea = findViewById(R.id.commentArea);

        commentButton.setOnClickListener(v -> {
            if (commentArea.getVisibility() == View.GONE) {
                commentArea.setVisibility(View.VISIBLE);
            } else {
                commentArea.setVisibility(View.GONE);
            }
        });

        // POST A COMMENT
        EditText commentInput = findViewById(R.id.commentInput);
        TextView postComment = findViewById(R.id.postComment);
        TextView commentDisplay = findViewById(R.id.commentDisplay);

        final int[] comments = {34};

        postComment.setOnClickListener(v -> {
            String message = commentInput.getText().toString().trim();

            if (message.length() > 0) {
                commentDisplay.append("You: " + message + "\n\n");
                comments[0]++;
                commentButton.setText("▢  " + comments[0]);
                commentInput.setText("");
            }
        });

        // SUBSCRIBE BUTTON
        TextView subscribeButton = findViewById(R.id.subscribeAndrew);

        final boolean[] subscribed = {false};

        subscribeButton.setOnClickListener(v -> {
            subscribed[0] = !subscribed[0];

            if (subscribed[0]) {
                subscribeButton.setText("2d  ·  Subscribed ✓");
            } else {
                subscribeButton.setText("2d  ·  Subscribe");
            }
        });

        // BOTTOM NAVIGATION
        TextView home = findViewById(R.id.navHome);
        TextView podcasts = findViewById(R.id.navPodcasts);
        TextView chat = findViewById(R.id.navChat);
        TextView activity = findViewById(R.id.navActivity);

        home.setOnClickListener(v ->
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show());

        podcasts.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PodcastsActivity.class)));

        chat.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ChatActivity.class)));

        activity.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ActivityActivity.class)));

        // HEADER BUTTONS
        findViewById(R.id.searchButton).setOnClickListener(v ->
                showComingSoon("Search"));

        findViewById(R.id.profileButton).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }

    // REFRESH HOME WHEN RETURNING FROM CREATE POST
    @Override
    protected void onResume() {
        super.onResume();
        loadUserPosts();
    }

    // LOAD ALL PUBLISHED POSTS
    private void loadUserPosts() {

        LinearLayout container = findViewById(R.id.userPostsContainer);

        if (container == null) {
            return;
        }

        container.removeAllViews();

        android.content.SharedPreferences preferences =
                getSharedPreferences("MyPosts", MODE_PRIVATE);

        String savedPosts = preferences.getString("posts", "[]");

        try {
            JSONArray posts = new JSONArray(savedPosts);

            // SHOW NEWEST POSTS FIRST
            for (int i = posts.length() - 1; i >= 0; i--) {

                JSONObject post = posts.getJSONObject(i);

                String title = post.getString("title");
                String content = post.getString("content");
                long date = post.getLong("date");

                // POST CARD
                LinearLayout card = new LinearLayout(this);
                card.setOrientation(LinearLayout.VERTICAL);
                card.setPadding(dp(16), dp(16), dp(16), dp(14));
                card.setBackgroundResource(R.drawable.ui_soft_card);
                card.setOnClickListener(v ->
                        openPost(
                                "Gurnoor Kaur",
                                title,
                                content,
                                "Your post"
                        )
                );

                LinearLayout.LayoutParams cardParams =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                cardParams.setMargins(0, dp(10), 0, dp(10));
                card.setLayoutParams(cardParams);

                // AUTHOR
                TextView author = new TextView(this);
                author.setText("Gurnoor Kaur  ·  Your post");
                author.setTextColor(0xFFD17A4B);
                author.setTextSize(12);
                card.addView(author);

                // TITLE
                TextView postHeading = new TextView(this);
                postHeading.setText(title);
                postHeading.setTextColor(0xFFF2EDE8);
                postHeading.setTextSize(20);
                postHeading.setTypeface(null, Typeface.BOLD);

                LinearLayout.LayoutParams titleParams =
                        new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );

                titleParams.setMargins(0, dp(12), 0, dp(8));
                card.addView(postHeading, titleParams);

                // CONTENT
                TextView postBody = new TextView(this);
                postBody.setText(content);
                postBody.setTextColor(0xFFB8AEA7);
                postBody.setTextSize(15);
                postBody.setLineSpacing(dp(4), 1f);
                card.addView(postBody);

                // DELETE BUTTON
                TextView deleteButton = new TextView(this);
                deleteButton.setText("Delete post");
                deleteButton.setTextColor(0xFFD17A4B);
                deleteButton.setTextSize(13);
                deleteButton.setTypeface(null, Typeface.BOLD);
                deleteButton.setGravity(Gravity.END);
                deleteButton.setPadding(0, dp(16), 0, dp(4));
                deleteButton.setClickable(true);
                deleteButton.setFocusable(true);

                deleteButton.setOnClickListener(v -> {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Delete post?")
                            .setMessage("Are you sure you want to delete this post?")
                            .setNegativeButton("Cancel", null)
                            .setPositiveButton("Delete", (dialog, which) ->
                                    deletePost(date))
                            .show();
                });

                card.addView(deleteButton);

                container.addView(card);
            }

        } catch (Exception e) {
            android.util.Log.e("MainActivity", "Could not load posts", e);
        }
    }


    private void openPost(String author, String title,
                          String content, String date) {

        Intent intent = new Intent(
                MainActivity.this,
                PostDetailActivity.class
        );

        intent.putExtra("author", author);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("date", date);

        startActivity(intent);
    }


    // DELETE A POST
    private void deletePost(long postDate) {

        android.content.SharedPreferences preferences =
                getSharedPreferences("MyPosts", MODE_PRIVATE);

        try {
            JSONArray oldPosts = new JSONArray(
                    preferences.getString("posts", "[]")
            );

            JSONArray updatedPosts = new JSONArray();

            for (int i = 0; i < oldPosts.length(); i++) {

                JSONObject post = oldPosts.getJSONObject(i);

                if (post.getLong("date") != postDate) {
                    updatedPosts.put(post);
                }
            }

            preferences.edit()
                    .putString("posts", updatedPosts.toString())
                    .apply();

            loadUserPosts();

            Toast.makeText(this, "Post deleted", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            android.util.Log.e("MainActivity", "Could not delete post", e);
        }
    }

    // CONVERT DP TO PIXELS
    private int dp(float value) {
        return (int) (value * getResources()
                .getDisplayMetrics().density + 0.5f);
    }

    // COMING SOON MESSAGE
    private void showComingSoon(String page) {
        Toast.makeText(
                this,
                page + " page coming soon!",
                Toast.LENGTH_SHORT
        ).show();
    }
}
