package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Finish extends AppCompatActivity {
    Button btn;
    LinearLayout l1, l2, l3, l4,l5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_finish);

        l1 = findViewById(R.id.finish_l1);
        l2 = findViewById(R.id.finish_l2);
        l3 = findViewById(R.id.finish_l3);
        l4 = findViewById(R.id.finish_l4);
        l4 = findViewById(R.id.finish_l5);
        btn=findViewById(R.id.finish_btn);


        l1.setOnClickListener(v -> selectOption(l1));
        l2.setOnClickListener(v -> selectOption(l2));
        l3.setOnClickListener(v -> selectOption(l3));
        l4.setOnClickListener(v -> selectOption(l4));
        l5.setOnClickListener(v -> selectOption(l5));

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Finish.this, Dashboard.class);
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

        selectedLayout.setBackgroundResource(R.drawable.option_background2);

    }
}