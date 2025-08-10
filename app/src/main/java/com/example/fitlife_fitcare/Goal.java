package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Goal extends AppCompatActivity {

    TextView t1;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_goal);

        t1=findViewById(R.id.your_goal);
        anim= AnimationUtils.loadAnimation(this,R.anim.animation);
        t1.setAnimation(anim);


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Goal.this, motivate_1.class);
                startActivity(intent);
            }
        },3000);

    }
}