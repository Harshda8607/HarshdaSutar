package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class abs_advanced extends AppCompatActivity {
<<<<<<< HEAD
    Button btn;
=======
    Button bt1;
>>>>>>> bb5ee0acaac8ac67194c18d871ba99b14b7dc214

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_abs_advanced);
<<<<<<< HEAD
        btn=findViewById(R.id.start_ABS_advanced);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(abs_advanced.this, Abs_advan_1.class);
=======
        bt1=findViewById(R.id.start_ABS_advanced);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(abs_advanced.this,Abs_advan_1.class);
>>>>>>> bb5ee0acaac8ac67194c18d871ba99b14b7dc214
                startActivity(intent);
            }
        });

    }
}