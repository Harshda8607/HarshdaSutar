package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.img);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation1);
        logo.startAnimation(animation);

        new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences prefs4 = getSharedPreferences("fetch_profile", MODE_PRIVATE);
            boolean isLoggedIn = prefs4.getBoolean("isLoggedIn", false);

            if (isLoggedIn) {
                Intent intent = new Intent(this, Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
            finish();
        }, 3000);

    }
}