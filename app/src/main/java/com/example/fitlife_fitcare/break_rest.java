package com.example.fitlife_fitcare;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class break_rest extends AppCompatActivity {
    private TextView timertext;
    private CountDownTimer countDownTimer;
    private boolean isRunning = true;
    private ImageButton playPauseButton;
    private long timeLeft = 30000;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_break_rest);
        timertext=findViewById(R.id.timer);
        playPauseButton=findViewById(R.id.cplay);

        playPauseButton.setOnClickListener(v ->
        {
            if (isRunning)
            {
                pauseTimer();
            } else
            {
                resumeTimer();
            }
        });

    }
    private void startTimer()
    {
        countDownTimer = new CountDownTimer(timeLeft, 1000)
        {
            public void onTick(long millisUntilFinished)
            {
                timeLeft = millisUntilFinished;
                int seconds = (int) millisUntilFinished / 1000;
                timertext.setText("00:" + String.format("%02d", seconds));
            }

            public void onFinish()
            {
                timertext.setText("00:00");
                Intent intent1 = new Intent(break_rest.this, Abs_advan_6.class);
                startActivity(intent1);

            }
        }.start();
        isRunning = true;
        playPauseButton.setImageResource(android.R.drawable.ic_media_pause);
    }
    private void pauseTimer()
    {
        countDownTimer.cancel();
        isRunning = false;
        playPauseButton.setImageResource(android.R.drawable.ic_media_play);
    }

    private void resumeTimer()
    {
        startTimer();
    }
}