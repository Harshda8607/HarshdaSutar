package com.example.fitlife_fitcare;

import static android.widget.Toast.LENGTH_LONG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.URLEncoder;

public class Forget_Password extends AppCompatActivity {

    EditText newPassword, confirmPassword;
    Button resetBtn;
    String email;  // Email from previous screen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        newPassword = findViewById(R.id.forgotpass);
        confirmPassword = findViewById(R.id.forgotpassword);
        resetBtn = findViewById(R.id.reset_btn);


        email = getIntent().getStringExtra("email");

        resetBtn.setOnClickListener(v -> {
            String pass1 = newPassword.getText().toString().trim();
            String pass2 = confirmPassword.getText().toString().trim();
            int len=pass1.length();

            if (pass1.isEmpty() || pass2.isEmpty())
            {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
            else if (!pass1.equals(pass2))
            {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
            else
            {
                StringBuilder errorBuilder = new StringBuilder();

                if ( len < 8)
                {
                    errorBuilder.append("• At least 8 characters\n");
                }
                if (!pass1.matches(".*[A-Z].*"))
                {
                    errorBuilder.append("• At least 1 uppercase letter\n");
                }
                if (!pass1.matches(".*[a-z].*"))
                {
                    errorBuilder.append("• At least 1 lowercase letter\n");
                }
                if (!pass1.matches(".*\\d.*"))
                {
                    errorBuilder.append("• At least 1 digit\n");
                }
                if (!pass1.matches(".*[@#$%^&+=!_-].*"))
                {
                    errorBuilder.append("• At least 1 special character (@#$%^&+=!)\n");
                }

                if (errorBuilder.length() > 0)
                {
                    Toast.makeText(Forget_Password.this, "" + errorBuilder.toString(), LENGTH_LONG).show();
                }
                else
                {
                    updatePassword(email, pass1,pass2);
                }
            }
        });
    }


    private void updatePassword(String email, String password,String confirmPassword) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Updating password...");
        dialog.setCancelable(false);
        dialog.show();

        try {
            String encodedEmail_ID = URLEncoder.encode(email, "UTF-8");
            String encodedPassword = URLEncoder.encode(password, "UTF-8");
            String encodedConfirm_Password= URLEncoder.encode(confirmPassword,"UTF-8");

            String url = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/password_update.php?Email_ID=" + encodedEmail_ID + "&Password=" + encodedPassword +"&Confirm_Password=" +encodedConfirm_Password;

            StringRequest request = new StringRequest(Request.Method.GET, url,
                    response -> {
                        dialog.dismiss();
                        if (response.contains("updated")) {
                            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(Forget_Password.this, Login.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(this, response, LENGTH_LONG).show();
                        }

                    },
                    error -> {
                        dialog.dismiss();
                        Toast.makeText(this, "Error: " + error.toString(), LENGTH_LONG).show();
                    });

            Volley.newRequestQueue(this).add(request);
        } catch (Exception e) {
            dialog.dismiss();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

//
//package com.example.fitlife_fitcare;
//
//import static android.widget.Toast.LENGTH_LONG;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.android.volley.Request;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import java.net.URLEncoder;
//
//public class Forget_Password extends AppCompatActivity {
//
//    EditText newPassword, confirmPassword;
//    Button resetBtn;
//    String email;
//    private static final String Password_Pattern =
//            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_-]).{8,}$";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forget_password);
//
//        newPassword = findViewById(R.id.forgotpass);
//        confirmPassword = findViewById(R.id.forgotpassword);
//        resetBtn = findViewById(R.id.reset_btn);
//
//        email = getIntent().getStringExtra("email");
//
//
//        resetBtn.setOnClickListener(v -> {
//            String pass1 = newPassword.getText().toString().trim();
//            String pass2 = confirmPassword.getText().toString().trim();
//            int len=pass1.length();
//
//            if (pass1.isEmpty() || pass2.isEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//            }
//            else if (!pass1.equals(pass2)) {
//                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
//            }else
//            {
//                StringBuilder errorBuilder = new StringBuilder();
//
//                if ( len < 8)
//                {
//                    errorBuilder.append("• At least 8 characters\n");
//                }
//                if (!pass1.matches(".*[A-Z].*"))
//                {
//                    errorBuilder.append("• At least 1 uppercase letter\n");
//                }
//                if (!pass1.matches(".*[a-z].*"))
//                {
//                    errorBuilder.append("• At least 1 lowercase letter\n");
//                }
//                if (!pass1.matches(".*\\d.*"))
//                {
//                    errorBuilder.append("• At least 1 digit\n");
//                }
//                if (!pass1.matches(".*[@#$%^&+=!_-].*"))
//                {
//                    errorBuilder.append("• At least 1 special character (@#$%^&+=!)\n");
//                }
//
//                if (errorBuilder.length() > 0)
//                {
//                    Toast.makeText(Forget_Password.this, "" + errorBuilder.toString(), LENGTH_LONG).show();
//                }
//                else
//                {
//                    updatePassword(email, pass1);
//                }
//            }
//        });
//    }
//
//    private void updatePassword(String email, String password) {
//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("Updating password...");
//        dialog.setCancelable(false);
//        dialog.show();
//
//        try {
//            String encodedEmail = URLEncoder.encode(email, "UTF-8");
//            String encodedPassword = URLEncoder.encode(password, "UTF-8");
//
//            String url = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/password_update.php?Email_ID=" + encodedEmail + "&Password=" + encodedPassword ;
//
//            StringRequest request = new StringRequest(Request.Method.GET, url,
//                    response -> {
//                        dialog.dismiss();
//                        if (response.contains("updated")) {
//                            Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show();
//
//
//                            Intent intent = new Intent(Forget_Password.this, Login.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(this, response, Toast.LENGTH_LONG).show();
//                        }
//
//                    },
//                    error -> {
//                        dialog.dismiss();
//                        Toast.makeText(this, "Error: " + error.toString(), Toast.LENGTH_LONG).show();
//                    });
//
//            Volley.newRequestQueue(this).add(request);
//        } catch (Exception e) {
//            dialog.dismiss();
//            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//}
