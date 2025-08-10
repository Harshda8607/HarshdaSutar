package com.example.fitlife_fitcare;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PrivateKey;

public class Login extends AppCompatActivity {

     private TextInputEditText user, pass;
     private MaterialButton login;

     private TextView signup,forget;

    private static final String BASE_URL = "http://www.testproject.info/Imran/VCHAutomation/VCHA_Login.php";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.in_username);
        pass = findViewById(R.id.in_password);
        login = findViewById(R.id.in_Button);
        signup = findViewById(R.id.in_signup);
        forget=findViewById(R.id.in_forgot);

        requestQueue = Volley.newRequestQueue(this);

        login.setOnClickListener(v -> validateAndLogin());

        signup.setOnClickListener(v -> Register());

        forget.setOnClickListener((v -> Forget()));

    }

    private void Register() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    private void Forget() {
        Intent intent = new Intent(Login.this, Forget_Password.class);
        startActivity(intent);
    }


    private void validateAndLogin() {

        String username = user.getText().toString().trim();
        String password = pass.getText().toString().trim();
        int length=password.length();

        if (TextUtils.isEmpty(username))
        {
            user.setError("Please enter username");
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            pass.setError("Please enter password");
            return;
        } else if (length<8)
        {
            Toast.makeText(Login.this, "Include 8 characters", LENGTH_SHORT).show();
        }


        performLogin(username, password);
    }

    private void performLogin(String username, String password) {
        try {
            String url = BASE_URL
                    + "?Username=" + URLEncoder.encode(username, "UTF-8")
                    + "&Password=" + URLEncoder.encode(password, "UTF-8");

            Log.d("LoginURL", url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        Log.d("ServerResponse", response);
                        if (response.contains("success")) {
                            Toast.makeText(Login.this, "Login successful!", LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Welcome.class));
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        String errorMsg = (error.networkResponse != null)
                                ? "HTTP Error Code: " + error.networkResponse.statusCode
                                : "Error: " + error.getMessage();
                        Log.e("VolleyError", errorMsg);
                        Toast.makeText(Login.this, errorMsg, Toast.LENGTH_LONG).show();
                    });

            requestQueue.add(stringRequest);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Encoding error", LENGTH_SHORT).show();
        }
    }
}