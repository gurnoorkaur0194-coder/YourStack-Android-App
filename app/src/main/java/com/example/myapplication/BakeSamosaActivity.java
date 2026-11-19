
package com.example.myapplication;

import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BakeSamosaActivity extends AppCompatActivity {

    LinearLayout notesContainer;
    Button subscribeButton;

    boolean isSubscribed = false;

    String[] notes = {
            "Prank idea: open my substack in incognito mode, when asked for an email, put up the email id of a person you wish to prank, enjoy!",

            "Now all these indie vegan eco friendly fashion brands will ask AI to make a GTA VI style ad for them and post that cringe shit on their stories and consume an ocean full of water",

            "We got GTA VI before GTA VII",

            "Understanding is so underrated as a verb and an act. It often gets overshadowed by similar or connected acts such as learning, listening, reading, thinking, etc. It is like the final step of this category of verbs.\n\nI think understanding is my favourite continuous verb. Nothing brings me more joy than being able to tell myself that I've understood something. It can be something tiny or a long mathematical calculation, the joy of that eureka moment stays the same.\n\nUnderstanding is to a curious person what eating is to a hungry person.",

            "You'll never know if it stopped raining if you don't dare to remove the umbrella",

            "In moderation it is tiramisu but eat too much and it becomes tiramissue",

            "Not believing in the idea of free will is a great demonstration of it",

            "The idea of free will is starting to sound false and freewillous",

            "What's next for Robert Pattinson? Nvidia CEO?",

            "Franz Kafka was so big in Paris and surroundings, they named the country after him"
    };

    String[] times = {
            "3h", "5h", "12h", "21h", "1d",
            "1d", "3d", "3d", "3d", "3d"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bake_samosa);

        notesContainer = findViewById(R.id.bakeNotesContainer);
        subscribeButton = findViewById(R.id.bakeSubscribe);

        // Back button
        findViewById(R.id.bakeBack).setOnClickListener(v -> finish());

        // Subscribe button
        subscribeButton.setOnClickListener(v -> {

            isSubscribed = !isSubscribed;

            if (isSubscribed) {
                subscribeButton.setText("Subscribed ✓");
                Toast.makeText(this,
                        "Subscribed to bakesamosa!",
                        Toast.LENGTH_SHORT).show();
            } else {
                subscribeButton.setText("Subscribe");
                Toast.makeText(this,
                        "Unsubscribed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        displayNotes();
    }

    private void displayNotes() {

        for (int i = 0; i < notes.length; i++) {

            // NOTE CARD
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(dp(15), dp(15), dp(15), dp(12));

            GradientDrawable background = new GradientDrawable();
            background.setColor(Color.rgb(35, 34, 36));
            background.setCornerRadius(dp(14));
            card.setBackground(background);

            LinearLayout.LayoutParams cardParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

            cardParams.bottomMargin = dp(12);
            card.setLayoutParams(cardParams);

            // AUTHOR AND TIME
            TextView author = new TextView(this);
            author.setText("bakesamosa  ·  " + times[i]);
            author.setTextColor(Color.rgb(169, 162, 154));
            author.setTextSize(12);

            // NOTE CONTENT
            TextView content = new TextView(this);
            content.setText(notes[i]);
            content.setTextColor(Color.rgb(245, 240, 232));
            content.setTextSize(15);
            content.setLineSpacing(dp(3), 1f);
            content.setPadding(0, dp(10), 0, dp(12));

            // ACTIONS
            LinearLayout actions = new LinearLayout(this);
            actions.setOrientation(LinearLayout.HORIZONTAL);
            actions.setGravity(android.view.Gravity.CENTER_VERTICAL);

            TextView like = new TextView(this);
            like.setText("♡  Like");
            like.setTextColor(Color.rgb(169, 162, 154));
            like.setTextSize(13);

            TextView comment = new TextView(this);
            comment.setText("   ◯  Comment");
            comment.setTextColor(Color.rgb(169, 162, 154));
            comment.setTextSize(13);

            actions.addView(like);
            actions.addView(comment);

            // LIKE INTERACTION
            like.setOnClickListener(v -> {

                if (like.getText().toString().contains("♡")) {
                    like.setText("♥  Liked");
                    like.setTextColor(Color.rgb(196, 81, 12));
                } else {
                    like.setText("♡  Like");
                    like.setTextColor(Color.rgb(169, 162, 154));
                }
            });

            // COMMENT PLACEHOLDER
            comment.setOnClickListener(v ->
                    Toast.makeText(this,
                            "Comments coming soon!",
                            Toast.LENGTH_SHORT).show()
            );

            card.addView(author);
            card.addView(content);
            card.addView(actions);

            notesContainer.addView(card);
        }
    }

    private int dp(int value) {
        return (int) (value *
                getResources().getDisplayMetrics().density);
    }
}
