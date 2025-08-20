package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {

    EditText feedback;
    Button send;
    ImageView feed_back;

    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/feedinsert.php";

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        EdgeToEdge.enable(this);

        feedback = findViewById(R.id.editfeedback);
        send = findViewById(R.id.send);
        feed_back=findViewById(R.id.feedback_back);
        feed_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Feedback.this, Settings.class);
                startActivity(intent);
            }
        });

        requestQueue = Volley.newRequestQueue(this);

        send.setOnClickListener(view -> {
            String feedText = feedback.getText().toString().trim();

            if (TextUtils.isEmpty(feedText)) {
                Toast.makeText(Feedback.this, "Feedback must be filled", Toast.LENGTH_SHORT).show();
            } else {
                sendFeedback(feedText);
            }
        });
    }

    private void sendFeedback(String feedText) {
        // ✅ Get stored username from SharedPreferences (set during login)
        SharedPreferences prefs = getSharedPreferences("fetch_profile", MODE_PRIVATE);
        String username = prefs.getString("Username", null);

        if (username == null) {
            Toast.makeText(this, "No username found. Please login again.", Toast.LENGTH_LONG).show();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
                response -> {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String status = obj.optString("status");
                        String message = obj.optString("message");

                        if ("success".equalsIgnoreCase(status)) {
                            Toast.makeText(Feedback.this, message, Toast.LENGTH_LONG).show();
                            feedback.setText("");
                        } else {
                            Toast.makeText(Feedback.this, "Error: " + message, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Log.e("JSONError", "Invalid JSON: " + response);
                        Toast.makeText(Feedback.this, "Invalid server response", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    String errorMsg = (error.networkResponse != null)
                            ? "HTTP Error Code : " + error.networkResponse.statusCode
                            : "Error : " + error.getMessage();
                    Log.e("VolleyError", errorMsg);
                    Toast.makeText(Feedback.this, errorMsg, Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("Feedback", feedText);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}