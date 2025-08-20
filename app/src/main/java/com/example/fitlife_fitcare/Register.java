package com.example.fitlife_fitcare;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputEditText Name, Email, Phone, Password, ConfirmPassword;
    Button btnRegister;
    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/insert.php";

    private static final String  Mobile_Pattern="\\d{10}";
    private static final String Password_Pattern =
            "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=!_-]).{8,}$";

    int no;
    private RequestQueue requestQueue;
    private static CookieManager cookieManager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        Name = findViewById(R.id.up_username);
        Email = findViewById(R.id.up_emailid);
        Phone = findViewById(R.id.up_mobilenumber);
        Password = findViewById(R.id.up_password);
        ConfirmPassword = findViewById(R.id.up_confirmpassword);
        btnRegister = findViewById(R.id.up_button);

        requestQueue=Volley.newRequestQueue(this);
        if (cookieManager == null) {
            cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            java.net.CookieHandler.setDefault(cookieManager);
        }

        btnRegister.setOnClickListener(view ->
        {
            String name = Name.getText().toString().trim();
            String email = Email.getText().toString().trim();
            String phone = Phone.getText().toString().trim();
            String password = Password.getText().toString().trim();
            String confirmPassword = ConfirmPassword.getText().toString().trim();
            no=password.length();

            if (TextUtils.isEmpty(name)||TextUtils.isEmpty(email)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(password)|| TextUtils.isEmpty(confirmPassword))
            {
                Toast.makeText(this, "All fields must be required", LENGTH_SHORT).show();
            }
            else if (!phone.matches(Mobile_Pattern))
            {
                Toast.makeText(Register.this, "Invalid Mobile number", LENGTH_SHORT).show();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                Toast.makeText(Register.this, "Invalid Email Id ", LENGTH_SHORT).show();
            }
            else if (!password.equals(confirmPassword))
            {
                Toast.makeText(Register.this, "Password do not match ", LENGTH_SHORT).show();
            }
            else {
                StringBuilder errorBuilder = new StringBuilder();

                if (password.length() < 8) {
                    errorBuilder.append("• At least 8 characters\n");
                }
                if (!password.matches(".*[A-Z].*")) {
                    errorBuilder.append("• At least 1 uppercase letter\n");
                }
                if (!password.matches(".*[a-z].*")) {
                    errorBuilder.append("• At least 1 lowercase letter\n");
                }
                if (!password.matches(".*\\d.*")) {
                    errorBuilder.append("• At least 1 digit\n");
                }
                if (!password.matches(".*[@#$%^&+=!_-].*")) {
                    errorBuilder.append("• At least 1 special character (@#$%^&+=!_-)\n");
                }

                if (errorBuilder.length() > 0) {
                    Toast.makeText(Register.this, "" + errorBuilder.toString(), LENGTH_LONG).show();
                } else {
                    registerUser(name, email, phone, password, confirmPassword);
                }
            }


        });
    }

    private void registerUser(String name, String email, String phone, String password,String confirmpass) {
        try {
            String url = BASE_URL +
                    "?Username=" + URLEncoder.encode(name, "UTF-8") +
                    "&Email_ID=" + URLEncoder.encode(email, "UTF-8") +
                    "&Mobile_Number=" + URLEncoder.encode(phone, "UTF-8") +
                    "&Password=" + URLEncoder.encode(password, "UTF-8")+
                    "&Confirm_Password="+ URLEncoder.encode(confirmpass, "UTF-8");

            Log.d("RegisterURL: " , url);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    response -> {
                        String usern=name.toString().trim();
                        Log.d("Server Response: " ,response);
                        if (response.contains("Username already exists")) {
                            Toast.makeText(this, "Username already exists. Choose another one.", LENGTH_LONG).show();
                        } else if (response.contains("Email already exists")) {
                            Toast.makeText(this, "Email already registered. Try logging in.", LENGTH_LONG).show();
                        } else if (response.contains("Registered successfully")) {
                            Toast.makeText(this, "Registration successful!", LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Welcome.class);
                            startActivity(intent);
                            SharedPreferences prefs = getSharedPreferences("fetch_profile", MODE_PRIVATE);
                            prefs.edit()
                                    .putString("Username",usern)
                                    .apply();
                            finish();
                        } else {
                            Toast.makeText(this, "Server Response: " + response, LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        String errorMSG= Boolean.parseBoolean(String.valueOf((error.networkResponse!=null)))
                                ? "HTTP Error Code : " + error.networkResponse.statusCode
                                : "Error : " +error.getMessage();
                        Log.e( "Volley Error: " ,errorMSG);
                        Toast.makeText(Register.this, errorMSG , LENGTH_LONG).show();
                    }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();

                    // Add cookies stored in CookieManager (to maintain session)
                    List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
                    if (!cookies.isEmpty()) {
                        StringBuilder cookieHeader = new StringBuilder();
                        for (HttpCookie cookie : cookies) {
                            cookieHeader.append(cookie.toString()).append("; ");
                        }
                        headers.put("Cookie", cookieHeader.toString());
                    }
                    return headers;
                }

            };


            requestQueue.add(request);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Encoding error " , LENGTH_LONG).show();
        }
    }
}