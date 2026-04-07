package com.example.madpollapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

// HITHASHREE's file — Screen 1
public class CreatePollActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        EditText etQ = findViewById(R.id.etQuestion);
        EditText etA = findViewById(R.id.etOptionA);
        EditText etB = findViewById(R.id.etOptionB);
        Button btn   = findViewById(R.id.btnCreate);

        btn.setOnClickListener(v -> {
            String q = etQ.getText().toString().trim();
            String a = etA.getText().toString().trim();
            String b = etB.getText().toString().trim();
            if (q.isEmpty() || a.isEmpty() || b.isEmpty()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show();
                return;
            }
            PollManager.savePoll(this, q, a, b);
            startActivity(new Intent(this, VoteActivity.class));
            finish();
        });
    }
}
