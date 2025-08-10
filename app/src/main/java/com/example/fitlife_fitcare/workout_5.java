package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class workout_5 extends AppCompatActivity {

    ProgressBar ps;
    ImageView back;
    LinearLayout l1, l2, l3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout5);

        l1 = findViewById(R.id.work5_l1);
        l2 = findViewById(R.id.work5_l2);
        l3 = findViewById(R.id.work5_l3);
        back=findViewById(R.id.back5);

        ps = findViewById(R.id.progress_5);
        ps.setProgress(66);

        l1.setOnClickListener(v -> selectOption(l1));
        l2.setOnClickListener(v -> selectOption(l2));
        l3.setOnClickListener(v -> selectOption(l3));

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(workout_5.this, enjoy_4.class);
                startActivity(intent);
            }
        });

    }
    public void selectOption (LinearLayout selectedLayout)
    {
        l1.setBackgroundResource(R.drawable.option_background);
        l2.setBackgroundResource(R.drawable.option_background);
        l3.setBackgroundResource(R.drawable.option_background);


        selectedLayout.setBackgroundResource(R.drawable.option_background2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(workout_5.this,exercise_time_6.class);
                startActivity(intent);
            }
        },1000);
    }
}