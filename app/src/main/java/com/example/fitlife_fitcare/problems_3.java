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

public class problems_3 extends AppCompatActivity {

    ProgressBar ps;
    ImageView back;
    LinearLayout l1, l2, l3, l4,l5,l6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_problems3);

        l1 = findViewById(R.id.problem3_l1);
        l2 = findViewById(R.id.problem3_l2);
        l3 = findViewById(R.id.problem3_l3);
        l4 = findViewById(R.id.problem3_l4);
        l5 = findViewById(R.id.problem3_l5);
        l6 = findViewById(R.id.problem3_l6);
        back=findViewById(R.id.back3);

        ps = findViewById(R.id.progress_3);
        ps.setProgress(33);

        l1.setOnClickListener(v -> selectOption(l1));
        l2.setOnClickListener(v -> selectOption(l2));
        l3.setOnClickListener(v -> selectOption(l3));
        l4.setOnClickListener(v -> selectOption(l4));
        l5.setOnClickListener(v -> selectOption(l5));
        l6.setOnClickListener(v -> selectOption(l6));

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(problems_3.this, goal_2.class);
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
        l5.setBackgroundResource(R.drawable.option_background);
        l6.setBackgroundResource(R.drawable.option_background);


        selectedLayout.setBackgroundResource(R.drawable.option_background2);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(problems_3.this,enjoy_4.class);
                startActivity(intent);
            }
        },1000);
    }
}