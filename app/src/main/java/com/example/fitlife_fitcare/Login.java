package com.example.fitlife_fitcare;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private TextInputEditText user, pass;
    private AppCompatButton login;
    ProgressDialog progressDialog;
    private TextView signup;
    private static final String LOGIN_URL = "http://training.testproject.info/9_AM_Batch/MainProject/login.php";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Auto-login check
        SharedPreferences prefs = getSharedPreferences("MyLearningPadPrefs", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);
        if (isLoggedIn) {
            startActivity(new Intent(Login.this, Welcome.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_login);

        user = findViewById(R.id.in_username);
        pass = findViewById(R.id.in_password);
//        tvforgotPassword = findViewById(R.id.forgotPassword_tv);
        signup = findViewById(R.id.in_signup);
        login = findViewById(R.id.in_Button);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");

        login.setOnClickListener(v -> userLogin());

//        tvforgotPassword.setOnClickListener(v -> {
//            Intent intent = new Intent(Login.this, ForgotPassword.class);
//            startActivity(intent);
//        });
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private void userLogin() {
        String username = user.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                response -> {
                    progressDialog.dismiss();

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        boolean success = jsonObject.getBoolean("success");

                        if (success) {
                            String returnedUsername = jsonObject.optString("username", "");
                            String email = jsonObject.optString("email", "");
                            String sessionToken = jsonObject.optString("session_token", "");

                            SharedPreferences prefs = getSharedPreferences("MyLearningPadPrefs", MODE_PRIVATE);
                            String storedSignupUsername = prefs.getString("signup_username", "");

                            if (!storedSignupUsername.isEmpty() && !storedSignupUsername.equals(returnedUsername)) {
                                Toast.makeText(Login.this, "This username does not match your registered account", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("email", email);
                            editor.putString("username", returnedUsername);
                            editor.putString("session_token", sessionToken);
                            editor.apply();

                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Welcome.class));
                            finish();

                        } else {
                            String message = jsonObject.optString("message", "Login failed!");
                            if (message.equalsIgnoreCase("Incorrect Password")) {
                                Toast.makeText(Login.this, "Password doesn't match. Please try again.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(Login.this, "JSON Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Volley Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("Password", password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}

//package com.example.fitlife_fitcare;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.android.material.button.MaterialButton;
//import com.google.android.material.textfield.TextInputEditText;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//public class Login extends AppCompatActivity {
//
//    private TextInputEditText user, pass;
//    private AppCompatButton login;
//    private TextView signup;
//
//    private static final String BASE_URL = "http://training.testproject/9_AM_Batch/FitLife_firCare/Login.php";
//
//    private RequestQueue requestQueue;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        user = findViewById(R.id.in_username);
//        pass = findViewById(R.id.in_password);
//        login = findViewById(R.id.in_Button);
//        signup = findViewById(R.id.in_signup);
//
//        requestQueue = Volley.newRequestQueue(this);
//
//        login.setOnClickListener(v -> validateAndLogin());
//
//        signup.setOnClickListener(v -> {
//            Intent intent = new Intent(Login.this, Register.class);
//            startActivity(intent);
//        });
//    }
//
//    private void validateAndLogin() {
//        String username = user.getText().toString().trim();
//        String password = pass.getText().toString().trim();
//
//        if (TextUtils.isEmpty(username)) {
//            user.setError("Please enter username");
//            return;
//        }
//
//        if (TextUtils.isEmpty(password)) {
//            pass.setError("Please enter password");
//            return;
//        }
//
//        performLogin(username, password);
//    }
//
//    private void performLogin(String username, String password) {
//        try {
//            String url = BASE_URL + "?Username=" + URLEncoder.encode(username, "UTF-8")
//                    + "&Password=" + URLEncoder.encode(password, "UTF-8");
//
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    response -> {
//                        if (response.contains("success")) {
//                            Toast.makeText(Login.this, "Login successful!", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(Login.this, Welcome.class));
//                            finish();
//                        } else {
//                            Toast.makeText(Login.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
//                        }
//                    },
//                    error -> Toast.makeText(Login.this, "Network error:", Toast.LENGTH_SHORT).show()
//            );
//
//            requestQueue.add(stringRequest);
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//    }
//}
