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

public class exercise_time_6 extends AppCompatActivity {

    ProgressBar ps;
    ImageView back;
    LinearLayout l1, l2, l3, l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise_time_6);

        l1 = findViewById(R.id.time6_l1);
        l2 = findViewById(R.id.time6_l2);
        l3 = findViewById(R.id.time6_l3);
        l4 = findViewById(R.id.time6_l4);
        back=findViewById(R.id.back6);

        ps = findViewById(R.id.progress_6);
        ps.setProgress(83);

        l1.setOnClickListener(v -> selectOption(l1));
        l2.setOnClickListener(v -> selectOption(l2));
        l3.setOnClickListener(v -> selectOption(l3));
        l4.setOnClickListener(v -> selectOption(l4));

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(exercise_time_6.this, workout_5.class);
                startActivity(intent);
            }
        });

    }
    public void selectOption (LinearLayout selectedLayout)
    {
        l1.setBackgroundResource(R.drawable.option_background);
        l2.setBackgroundResource(R.drawable.option_background);
        l3.setBackgroundResource(R.drawable.option_background);
        l4.setBackgroundResource(R.drawable.option_background);


        selectedLayout.setBackgroundResource(R.drawable.option_background2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(exercise_time_6.this,day_spend7.class);
                startActivity(intent);
            }
        },1000);
    }

}