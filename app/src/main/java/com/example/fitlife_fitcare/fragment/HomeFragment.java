package com.example.fitlife_fitcare.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitlife_fitcare.*;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    Button bt1, bt2, bt3, bt4;
    CardView cabs1, cabs2, cabs3, cthigh1, cthigh2, cthigh3, carm1, carm2, carm3,
            cbutt1, cbutt2, cbutt3, cchest1, cchest2, cchest3;

    String PHP_URL = "https://yourdomain.com/insert_course.php"; // Change to your real PHP URL

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bt1 = view.findViewById(R.id.btn1_fullbody);
        bt2 = view.findViewById(R.id.btn2_lowerbody);
        bt3 = view.findViewById(R.id.btn3_flatstomach);
        bt4 = view.findViewById(R.id.btn4_faceyoga);

        cabs1 = view.findViewById(R.id.abs_workout1);
        cabs2 = view.findViewById(R.id.abs_workout_2);
        cabs3 = view.findViewById(R.id.abs_workout_3);
        cthigh1 = view.findViewById(R.id.thigh_workout_1);
        cthigh2 = view.findViewById(R.id.thighr_workout2);
        cthigh3 = view.findViewById(R.id.thigh_workout3);
        carm1 = view.findViewById(R.id.arm_workout1);
        carm2 = view.findViewById(R.id.arm_workout2);
        carm3 = view.findViewById(R.id.arm_workout3);
        cbutt1 = view.findViewById(R.id.butt_workout1);
        cbutt2 = view.findViewById(R.id.butt_workout2);
        cbutt3 = view.findViewById(R.id.butt_workout3);
        cchest1 = view.findViewById(R.id.chest_workout1);
        cchest2 = view.findViewById(R.id.chest_workout2);
        cchest3 = view.findViewById(R.id.chest_workout3);

        // Full Body
        bt1.setOnClickListener(v -> {
            sendCourseToServer("Full Body Workout");
            startActivity(new Intent(getActivity(), FullBody.class));
        });

        bt2.setOnClickListener(v -> {
            sendCourseToServer("Lower Body Workout");
            startActivity(new Intent(getActivity(), Lowerbody.class));
        });

        bt3.setOnClickListener(v -> {
            sendCourseToServer("Flat Stomach Workout");
            startActivity(new Intent(getActivity(), flatstomach.class));
        });

        bt4.setOnClickListener(v -> {
            sendCourseToServer("Face Yoga");
            startActivity(new Intent(getActivity(), Faceyoga.class));
        });

        // Abs Workouts
        cabs1.setOnClickListener(v -> {
            sendCourseToServer("Abs Beginner");
            startActivity(new Intent(getActivity(), abs_beginner.class));
        });

        cabs2.setOnClickListener(v -> {
            sendCourseToServer("Abs Intermediate");
            startActivity(new Intent(getActivity(), abs_intermediate.class));
        });

        cabs3.setOnClickListener(v -> {
            sendCourseToServer("Abs Advanced");
            startActivity(new Intent(getActivity(), abs_advanced.class));
        });

        // Thigh Workouts
        cthigh1.setOnClickListener(v -> {
            sendCourseToServer("Thigh Beginner");
            startActivity(new Intent(getActivity(), Thigh_begin.class));
        });

        cthigh2.setOnClickListener(v -> {
            sendCourseToServer("Thigh Intermediate");
            startActivity(new Intent(getActivity(), Thigh_intermediate.class));
        });

        cthigh3.setOnClickListener(v -> {
            sendCourseToServer("Thigh Advanced");
            startActivity(new Intent(getActivity(), Thigh_advanced.class));
        });

        // Arm Workouts
        carm1.setOnClickListener(v -> {
            sendCourseToServer("Arm Beginner");
            startActivity(new Intent(getActivity(), arm_beginner.class));
        });

        carm2.setOnClickListener(v -> {
            sendCourseToServer("Arm Intermediate");
            startActivity(new Intent(getActivity(), arm_intermediate.class));
        });

        carm3.setOnClickListener(v -> {
            sendCourseToServer("Arm Advanced");
            startActivity(new Intent(getActivity(), arm_advanced.class));
        });

        // Butt Workouts
        cbutt1.setOnClickListener(v -> {
            sendCourseToServer("Butt Beginner");
            startActivity(new Intent(getActivity(), Butt_begin.class));
        });

        cbutt2.setOnClickListener(v -> {
            sendCourseToServer("Butt Intermediate");
            startActivity(new Intent(getActivity(), Butt_inter.class));
        });

        cbutt3.setOnClickListener(v -> {
            sendCourseToServer("Butt Advanced");
            startActivity(new Intent(getActivity(), Butt_advan.class));
        });

        // Chest Workouts
        cchest1.setOnClickListener(v -> {
            sendCourseToServer("Chest Beginner");
            startActivity(new Intent(getActivity(), Chest_begin.class));
        });

        cchest2.setOnClickListener(v -> {
            sendCourseToServer("Chest Intermediate");
            startActivity(new Intent(getActivity(), Chest_inter.class));
        });

        cchest3.setOnClickListener(v -> {
            sendCourseToServer("Chest Advanced");
            startActivity(new Intent(getActivity(), Chest_advanc.class));
        });
    }

    private void sendCourseToServer(String courseName) {
        StringRequest request = new StringRequest(Request.Method.POST, PHP_URL,
                response -> {
                    switch (response.trim()) {
                        case "success":
                            Toast.makeText(getContext(), "Started: " + courseName, Toast.LENGTH_SHORT).show();
                            break;
                        case "already_exists":
                            Toast.makeText(getContext(), "You already started this today!", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(getContext(), "Server Error: " + response, Toast.LENGTH_SHORT).show();
                            break;
                    }
                },
                error -> Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("course_name", courseName);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}