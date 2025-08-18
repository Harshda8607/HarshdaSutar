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

        // Start animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation1);
        logo.startAnimation(animation);

        // Splash delay and login check
        new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("LoginCheck", MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

            // Decide next activity
            Class<?> nextActivity = isLoggedIn ? Dashboard.class : Login.class;

            // Go to selected activity
            startActivity(new Intent(MainActivity.this, nextActivity));
            finish();


        }, 3000); // 3 seconds splash
    }
}
