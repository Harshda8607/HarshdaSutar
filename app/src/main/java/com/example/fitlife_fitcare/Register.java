
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

public class Register extends AppCompatActivity {

    TextInputEditText Name, Email, Phone, Password, ConfirmPassword;
    Button btnRegister;
    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/insert.php";

    private static final String  Mobile_Pattern="\\d{10}";
    int no;
    private RequestQueue requestQueue;

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

        btnRegister.setOnClickListener(view ->
        {
            String name = Name.getText().toString().trim();
            String email = Email.getText().toString().trim();
            String phone = Phone.getText().toString().trim();
            String password = Password.getText().toString().trim();
            String confirmPassword = ConfirmPassword.getText().toString().trim();
            no=password.length();

//            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty())
//
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
            else if (no<8)
            {
                Toast.makeText(Register.this,"Password contains must be 8 characters",LENGTH_LONG).show();
            } else
            {
                registerUser(name, email, phone, password);
            }
        });
    }

    private void registerUser(String name, String email, String phone, String password) {
        try {
            String url = BASE_URL +
                    "?Username=" + URLEncoder.encode(name, "UTF-8") +
                    "&Email_ID=" + URLEncoder.encode(email, "UTF-8") +
                    "&Mobile_Number=" + URLEncoder.encode(phone, "UTF-8") +
                    "&Password=" + URLEncoder.encode(password, "UTF-8");

            Log.d("RegisterURL: " , url);

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    response -> {
                        Log.d("Server Response: " ,response);
                        Toast.makeText(Register.this,response,LENGTH_LONG).show();
                        Intent intent=new Intent(Register.this,Welcome.class);
                        startActivity(intent);
                    },
                    error -> {
                        String errorMSG= Boolean.parseBoolean(String.valueOf((error.networkResponse!=null)))
                                ? "HTTP Error Code : " + error.networkResponse.statusCode
                                : "Error : " +error.getMessage();
                        Log.e( "Volley Error: " ,errorMSG);
                        Toast.makeText(Register.this, errorMSG , LENGTH_LONG).show();
                    });


            requestQueue.add(request);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(this, "Encoding error " , LENGTH_LONG).show();
        }
    }
}
