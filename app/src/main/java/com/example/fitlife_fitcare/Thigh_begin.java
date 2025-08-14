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

public class Thigh_begin extends AppCompatActivity {
<<<<<<< HEAD
    Button btn;
=======
    Button bt1;
>>>>>>> bb5ee0acaac8ac67194c18d871ba99b14b7dc214

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thigh_begin);
<<<<<<< HEAD
        btn=findViewById(R.id.start_thigh_beg);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Thigh_begin.this,thigh_beg_1.class);
=======
        bt1=findViewById(R.id.start_thigh_beg);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Thigh_begin.this,Thigh_begin_1.class);
>>>>>>> bb5ee0acaac8ac67194c18d871ba99b14b7dc214
                startActivity(intent);
            }
        });
    }
}