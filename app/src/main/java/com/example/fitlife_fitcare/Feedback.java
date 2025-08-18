package com.example.fitlife_fitcare;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Feedback extends AppCompatActivity {

    EditText feedback;
    Button send;
    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/insert.php";

    private RequestQueue requestQueue;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        feedback=findViewById(R.id.editfeedback);
        send=findViewById(R.id.send);
        requestQueue=Volley.newRequestQueue(this);

        send.setOnClickListener(view ->
        {
            String feed = feedback.getText().toString().trim();


//            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty())
//
            if (TextUtils.isEmpty(feed))
            {
                Toast.makeText(this, "Feedback must be filled", LENGTH_SHORT).show();
            }
             else {
                    registerUser(feed);
                }

        });
    }

    private void registerUser(String feed) {
        try {
            String url = BASE_URL +
                    "?Username=" + URLEncoder.encode(feed, "UTF-8");

            Log.d("RegisterURL: " , url);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    response -> {
                        Log.d("Server Response: " ,response);
                       {
                            Toast.makeText(this, "Server Response: " + response, LENGTH_LONG).show();
                        }
                    },
                    error -> {
                        String errorMSG= Boolean.parseBoolean(String.valueOf((error.networkResponse!=null)))
                                ? "HTTP Error Code : " + error.networkResponse.statusCode
                                : "Error : " +error.getMessage();
                        Log.e( "Volley Error: " ,errorMSG);
                        Toast.makeText(Feedback.this, errorMSG , LENGTH_LONG).show();
                    });


            requestQueue.add(request);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Encoding error " , LENGTH_LONG).show();
        }
    }
}