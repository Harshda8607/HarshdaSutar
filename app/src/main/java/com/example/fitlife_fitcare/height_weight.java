package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class height_weight extends AppCompatActivity {

    TextView tvWeight, tvHeight;
    SeekBar weightSeekBar, heightSeekBar;
    Switch switchWeightUnit, switchHeightUnit;
    Button btnDone;
    boolean isLbs = true;
    boolean isFeet = true;

    public static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/height_insert.php";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_weight);

        tvWeight = findViewById(R.id.tvWeight);
        tvHeight = findViewById(R.id.tvHeight);
        weightSeekBar = findViewById(R.id.weightSeekBar);
        heightSeekBar = findViewById(R.id.heightSeekBar);
        switchWeightUnit = findViewById(R.id.switchWeightUnit);
        switchHeightUnit = findViewById(R.id.switchHeightUnit);
        btnDone = findViewById(R.id.btndone_hw);

        requestQueue = Volley.newRequestQueue(this);

        // Default progress
        weightSeekBar.setProgress(60);  // 60kg approx
        heightSeekBar.setProgress(165); // 165cm approx
        updateWeightText(weightSeekBar.getProgress());
        updateHeightText(heightSeekBar.getProgress());

        // Seekbar listeners
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                updateWeightText(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        heightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser) {
                updateHeightText(value);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Switch listeners
        switchWeightUnit.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isLbs = isChecked;
            switchWeightUnit.setText(isLbs ? "lbs" : "kg");
            updateWeightText(weightSeekBar.getProgress());
        });

        switchHeightUnit.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isFeet = isChecked;
            switchHeightUnit.setText(isFeet ? "ft" : "cm");
            updateHeightText(heightSeekBar.getProgress());
        });


        btnDone.setOnClickListener(v -> {
            // Send the text exactly as shown in UI
            String heightStr = tvHeight.getText().toString();
            String weightStr = tvWeight.getText().toString();

            hwinsert(heightStr, weightStr);  // send to PHP

            // Go to Dashboard
            startActivity(new Intent(height_weight.this, Dashboard.class));
        });

    }

    private void hwinsert(String height, String weight) {

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
                            Toast.makeText(height_weight.this, "Height & Weight saved!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(height_weight.this, "Error: " + message, Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Log.e("JSONError", "Invalid JSON: " + response);
                        Toast.makeText(height_weight.this, "Invalid server response", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    String errorMsg = (error.networkResponse != null)
                            ? "HTTP Error Code: " + error.networkResponse.statusCode
                            : "Error: " + error.getMessage();
                    Log.e("VolleyError", errorMsg);
                    Toast.makeText(height_weight.this, errorMsg, Toast.LENGTH_LONG).show();
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Username", username);
                params.put("Height", height);
                params.put("Weight", weight);
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void updateWeightText(int value) {
        if (isLbs) {
            tvWeight.setText(value + " lbs");
        } else {
            double kg = value * 0.4536;
            tvWeight.setText(String.format(Locale.US, "%.1f kg", kg));
        }
    }

    private void updateHeightText(int value) {
        if (isFeet) {
            int inches = (int) Math.round(value / 2.54);
            int feet = inches / 12;
            int inch = inches % 12;
            tvHeight.setText(feet + " ft " + inch + " in");
        } else {
            tvHeight.setText(value + " cm");
        }
    }
}

