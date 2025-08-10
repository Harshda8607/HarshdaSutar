package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class motivate_1 extends AppCompatActivity {
    ProgressBar ps;
    Button back;

    LinearLayout l1, l2, l3, l4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_motivate1);

        l1 = findViewById(R.id.motivate1_l1);
        l2 = findViewById(R.id.motivate1_l2);
        l3 = findViewById(R.id.motivate1_l3);
        l4 = findViewById(R.id.motivate1_l4);

        ps = findViewById(R.id.progress_1);
        ps.setProgress(0);

        l1.setOnClickListener(v -> selectOption(l1));
        l2.setOnClickListener(v -> selectOption(l2));
        l3.setOnClickListener(v -> selectOption(l3));
        l4.setOnClickListener(v -> selectOption(l4));


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
                Intent intent=new Intent(motivate_1.this,goal_2.class);
                startActivity(intent);
            }
        },1000);
    }
}
