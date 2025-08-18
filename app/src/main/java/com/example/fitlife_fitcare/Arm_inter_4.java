package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Arm_inter_4 extends AppCompatActivity {

    private TextView timerText;
    private ProgressBar progressBar;
    private ImageButton playPauseButton;
    private ImageButton nextButton;
    ImageView backbutton;
    private CountDownTimer countDownTimer;
    private boolean isRunning = true;
    private long timeLeft = 30000;
    public static final int ARM_INT_WORKOUT = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_arm_inter4);

        timerText = findViewById(R.id.arm_intertime4);
        progressBar = findViewById(R.id.arm_interprogressBar4);
        playPauseButton = findViewById(R.id.arm_interPauseButton4);
        nextButton = findViewById(R.id.arm_interNextButton4);
        backbutton=findViewById(R.id.back_arm_i4);
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
            Intent intent = new Intent(Arm_inter_4.this, break_rest.class);
            intent.putExtra("NEXT_WORKOUT", ARM_INT_WORKOUT);
            startActivity(intent);
            finish();
        });
        backbutton.setOnClickListener(v -> {

            countDownTimer.cancel();
            isRunning = false;
            Intent intent = new Intent(Arm_inter_4.this, Arm_inter_3.class);
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
                Intent intent1 = new Intent(Arm_inter_4.this, break_rest.class);
                intent1.putExtra("NEXT_WORKOUT", ARM_INT_WORKOUT);
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
