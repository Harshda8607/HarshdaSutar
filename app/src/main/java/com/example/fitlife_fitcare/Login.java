
package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Login extends AppCompatActivity
{

    private TextInputEditText user, pass;
    private AppCompatButton login;
    private TextView signup,forget;

    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/fitlife_login.php";

    private RequestQueue requestQueue;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.in_username);
        pass = findViewById(R.id.in_password);
        login = findViewById(R.id.in_Button);
        signup = findViewById(R.id.in_signup);
        forget=findViewById(R.id.in_forgot);

        requestQueue = Volley.newRequestQueue(this);

        login.setOnClickListener(v -> validateAndLogin());

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,password_email.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });

        sharedPreferences = getSharedPreferences("LoginCheck", MODE_PRIVATE);

        // ✅ Skip login if already logged in
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(Login.this, Dashboard.class));
            finish();
            return;
        }



//        SharedPreferences userPrefs = getSharedPreferences("fetch_", MODE_PRIVATE);
//        SharedPreferences.Editor userEditor = userPrefs.edit();
//        userEditor.putString("id", ID); // Replace with actual ID from response
//        userEditor.apply();

    }



    private void validateAndLogin() {
        String username = user.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            user.setError("Please enter username");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            pass.setError("Please enter password");
            return;
        }

        performLogin(username, password);
    }

    private void performLogin(String username, String password) {
        try {
            String url = BASE_URL + "?Username=" + URLEncoder.encode(username, "UTF-8")
                    + "&Password=" + URLEncoder.encode(password, "UTF-8");

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        if (response.contains("success")) {
                            Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(Login.this, "Network error:", Toast.LENGTH_SHORT).show()
            );

            requestQueue.add(stringRequest);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


