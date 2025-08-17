package com.example.fitlife_fitcare.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitlife_fitcare.Dashboard;
import com.example.fitlife_fitcare.Login;
import com.example.fitlife_fitcare.R;
import com.example.fitlife_fitcare.Welcome;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Profile extends Fragment {

    TextView user, email, mobile, hei, wei, signout;
    ImageView set;
    String id;

    private static final String BASE_URL = "http://training.testproject.info/9_AM_Batch/FitLife_firCare/profile_php.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        signout = view.findViewById(R.id.signout);
        signout.setOnClickListener(v -> {
            requireActivity().getSharedPreferences("loggedcheck", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .apply();

            Toast.makeText(getActivity(), "Signed out successfully!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getActivity(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = view.findViewById(R.id.value_username);
        email = view.findViewById(R.id.value_email);
        mobile = view.findViewById(R.id.value_mobile);
        hei = view.findViewById(R.id.value_height);
        wei = view.findViewById(R.id.value_weight);
        set = view.findViewById(R.id.setting);

        SharedPreferences prefs = requireActivity().getSharedPreferences("fetch_profile", MODE_PRIVATE);
        id = prefs.getString("id", null);

        if (id != null) {
            new FetchProfile().execute(id);
        } else {
            Toast.makeText(getContext(), "User ID not found", Toast.LENGTH_SHORT).show();
        }

        set.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Welcome.class);
            intent.putExtra("id", id);
            startActivityForResult(intent, 1001);
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(
                getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent intent = new Intent(requireContext(), Dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        requireActivity().finish();
                    }
                }
        );
    }

    // AsyncTask to fetch profile data
    private class FetchProfile extends AsyncTask<String, Void, String[]> {
        @Override
        protected String[] doInBackground(String... strings) {
            String userId = strings[0];

            try {
                URL url = new URL(BASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "id=" + URLEncoder.encode(userId, "UTF-8");
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(postData);
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String response = reader.readLine();

                reader.close();
                is.close();
                conn.disconnect();

                if (response != null && response.contains("|")) {
                    return response.split("\\|");
                } else {
                    return null;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String[] data) {
            if (data != null && data.length >= 5) {
                user.setText(data[0]);    // username
                mobile.setText(data[1]);  // mobile
                email.setText(data[2]);   // email
                hei.setText(data[3]);     // height
                wei.setText(data[4]);     // weight
            } else {
                Toast.makeText(getContext(), "Failed to load profile", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Reload profile data after editing
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001 && resultCode == Activity.RESULT_OK) {
            if (id != null) {
                new FetchProfile().execute(id);
            }
        }
    }
}
