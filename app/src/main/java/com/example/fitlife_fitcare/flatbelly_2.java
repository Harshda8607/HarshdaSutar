package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class flatbelly_2 extends AppCompatActivity {

    private TextView timerText;
    private ProgressBar progressBar;
    private ImageButton playPauseButton;
    private ImageButton nextButton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = true;
    private long timeLeft = 30000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_flatbelly2);

        timerText = findViewById(R.id.bellytime2);
        progressBar = findViewById(R.id.bellyrprogressBar2);
        playPauseButton = findViewById(R.id.bellyPauseButton2);
        nextButton = findViewById(R.id.bellyNextButton2);
        progressBar.setMax(30);
        startTimer();

        playPauseButton.setOnClickListener(v -> {
            if (isRunning) {
                pauseTimer();
            } else {
                resumeTimer();
            }
        });

        nextButton.setOnClickListener(v -> {

            countDownTimer.cancel();
            isRunning = false;
            Intent intent = new Intent(flatbelly_2.this, flatbelly_3.class);
            startActivity(intent);
            finish();
        });

    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                int seconds = (int) millisUntilFinished / 1000;
                timerText.setText("00:" + String.format("%02d", seconds));
                progressBar.setProgress(30 - seconds);
            }

            public void onFinish() {
                timerText.setText("00:00");
                Intent intent1 = new Intent(flatbelly_2.this, flatbelly_3.class);
                startActivity(intent1);

            }
        }.start();
        isRunning = true;
        playPauseButton.setImageResource(android.R.drawable.ic_media_pause);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isRunning = false;
        playPauseButton.setImageResource(android.R.drawable.ic_media_play);
    }

    private void resumeTimer() {
        startTimer();
    }
}
