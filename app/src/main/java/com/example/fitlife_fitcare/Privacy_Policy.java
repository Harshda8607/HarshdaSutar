package com.example.fitlife_fitcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Privacy_Policy extends AppCompatActivity {
    ImageView privacy_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_privacy_policy);
        privacy_back=findViewById(R.id.privacy_back);
        privacy_back.setOnClickListener(v -> {
            Intent intent=new Intent(Privacy_Policy.this, Settings.class);
            startActivity(intent);
        });

    }
}