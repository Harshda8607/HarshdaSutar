package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.graphics.ImageDecoderKt;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.img);



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(MainActivity.this, Login.class);
//                startActivity(intent);
//            }
//        },4000);

        new Handler(android.os.Looper.getMainLooper()).postDelayed(() -> {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation1);
            logo.startAnimation(animation);

            SharedPreferences sharedPreferences = getSharedPreferences("loggedcheck", MODE_PRIVATE);
            boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

            Class<?> nextActivity = isLoggedIn ? Dashboard.class : Login.class;

            startActivity(new Intent(MainActivity.this, nextActivity));
            finish();


        }, 3000);

    }
}
