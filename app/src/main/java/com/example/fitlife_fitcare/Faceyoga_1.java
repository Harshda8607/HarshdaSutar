package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pl.droidsonroids.gif.GifImageView;

public class Faceyoga_1 extends AppCompatActivity {
    private TextView timerText;
    private ProgressBar progressBar;
    private ImageButton playPauseButton;
    ImageButton nextButton;
    private CountDownTimer countDownTimer;
    GifImageView img1;
    private boolean isRunning = true;
    private long timeLeft = 30000; // 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faceyoga1);
        EdgeToEdge.enable(this);
        timerText = findViewById(R.id.Faceyogatime1);
        progressBar = findViewById(R.id.FaceyogaprogressBar1);
        playPauseButton = findViewById(R.id.FaceyogaplayPauseButton1);
        nextButton = findViewById(R.id.FaceyogaNextButton1);
        progressBar.setMax(30);
        startTimer();
        playPauseButton.setOnClickListener(v -> {
            if (isRunning) {
                pauseTimer();
            } else
            {
                resumeTimer();
            }
        });
        nextButton.setOnClickListener(v -> {

            countDownTimer.cancel();
            isRunning = false;
            Intent intent = new Intent(Faceyoga_1.this, Faceyoga_2.class);
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
                Intent intent=new Intent(Faceyoga_1.this,Faceyoga_2.class);
                startActivity(intent);
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