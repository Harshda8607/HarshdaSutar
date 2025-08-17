package com.example.fitlife_fitcare;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class password_email extends AppCompatActivity {
    EditText emailEditText;
    Button nextButton;
    LinearLayout backPress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_email);

        emailEditText = findViewById(R.id.pass_email_text);
        nextButton = findViewById(R.id.next_btn);
//        backPress=findViewById(R.id.back_btn);

        nextButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter your registered email", Toast.LENGTH_SHORT).show();
            } else {
                verifyEmail(email);
            }
        });

    }

    private void verifyEmail(String email) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Checking email...");
        dialog.setCancelable(false);
        dialog.show();

        String url = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/email_found?Email_ID=" + email;

        StringRequest request = new StringRequest(Request.Method.GET, url,
                response -> {
                    dialog.dismiss();
                    if (response.contains("exists")) {
                        // Navigate to ResetPasswordActivity and pass the email
                        Intent intent = new Intent(this, Forget_Password.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Email not registered", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
                });

        Volley.newRequestQueue(this).add(request);
    }
}


