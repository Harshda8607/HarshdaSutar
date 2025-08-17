package com.example.fitlife_fitcare;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;




public class height_weight extends AppCompatActivity {

    TextView tvWeight, tvHeight;
    SeekBar weightSeekBar, heightSeekBar;
    Switch switchWeightUnit, switchHeightUnit;
    Button btn;
    boolean isLbs = true;
    boolean isFeet = true;
    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/height_insert.php";
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
        btn=findViewById(R.id.btndone_hw);

        requestQueue= Volley.newRequestQueue(this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int heightCm = heightSeekBar.getProgress();
                int weightLbs = weightSeekBar.getProgress();

                Intent intent= new Intent(height_weight.this,Dashboard.class);
                startActivity(intent);

                hwinsert(heightCm, weightLbs);
            }
        });




        weightSeekBar.setProgress(143);
        heightSeekBar.setProgress(163);

        updateWeightText(weightSeekBar.getProgress());
        updateHeightText(heightSeekBar.getProgress());


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
    }

    private void hwinsert(int height, int weight) {
        String url = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/height_insert.php=" + height + "&Weight=" + weight;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    // Response from PHP
                    Toast.makeText(height_weight.this, "Updated successfully", Toast.LENGTH_SHORT).show();
                },
                error -> {
                    Toast.makeText(height_weight.this, "Successful ", Toast.LENGTH_LONG).show();
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
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
