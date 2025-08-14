package com.example.fitlife_fitcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.example.fitlife_fitcare.adapter.workout_adapter;


import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.Map;

public class Calendar_1 extends AppCompatActivity {

    private CalendarView calendarView;
    private Button btnMarkWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar1);

        calendarView = findViewById(R.id.calendarView);
        btnMarkWorkout = findViewById(R.id.btnMarkWorkout);

        // Load saved workout days when activity starts
        loadWorkoutDays();

        // When user clicks "Mark Today's Workout"
        btnMarkWorkout.setOnClickListener(v -> {
            Calendar today = Calendar.getInstance();
            saveWorkoutDate(today);
            loadWorkoutDays();// Refresh calendar
            Intent intent=new Intent(Calendar_1.this, workout_adapter.class);
            startActivity(intent);
        });
    }



    private void saveWorkoutDate(Calendar date) {
        SharedPreferences prefs = getSharedPreferences("WorkoutPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Convert date to string: YYYY-MM-DD
        String dateStr = date.get(Calendar.YEAR) + "-" +
                (date.get(Calendar.MONTH) + 1) + "-" +
                date.get(Calendar.DAY_OF_MONTH);

        editor.putBoolean(dateStr, true);
        editor.apply();
    }

    private void loadWorkoutDays() {
        SharedPreferences prefs = getSharedPreferences("WorkoutPrefs", MODE_PRIVATE);
        Map<String, ?> allDates = prefs.getAll();

        List<EventDay> events = new ArrayList<>();

        for (String dateStr : allDates.keySet()) {
            if ((boolean) allDates.get(dateStr)) {
                String[] parts = dateStr.split("-");
                int year = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]) - 1;
                int day = Integer.parseInt(parts[2]);

                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day);
                events.add(new EventDay(cal, R.drawable.baseline_circle_24));
            }
        }

        calendarView.setEvents(events);
    }
}