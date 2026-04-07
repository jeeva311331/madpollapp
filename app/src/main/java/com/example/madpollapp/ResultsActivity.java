package com.example.madpollapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// JEEVA's file --- Screen 3

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        String[] poll = PollManager.getPoll(this);
        int[] votes = PollManager.getVotes(this);

        // Null/bounds safety check
        if (poll == null || poll.length < 3 || votes == null || votes.length < 2) {
            return;
        }

        int total = votes[0] + votes[1];

        // Avoid division by zero; use float for accurate percentage then cast
        int pctA = total == 0 ? 0 : (int) (votes[0] * 100.0f / total);
        int pctB = total == 0 ? 0 : (int) (votes[1] * 100.0f / total);

        ((TextView) findViewById(R.id.tvResultA))
                .setText(poll[1] + ": " + votes[0] + " votes (" + pctA + "%)");

        ((TextView) findViewById(R.id.tvResultB))
                .setText(poll[2] + ": " + votes[1] + " votes (" + pctB + "%)");

        ((ProgressBar) findViewById(R.id.pbA)).setProgress(pctA);
        ((ProgressBar) findViewById(R.id.pbB)).setProgress(pctB);

        findViewById(R.id.btnReset).setOnClickListener(v -> {
            PollManager.resetPoll(this);
            startActivity(new Intent(this, CreatePollActivity.class));
            finish();
        });
    }
}