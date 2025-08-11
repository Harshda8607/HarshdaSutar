package com.example.fitlife_fitcare;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.fitlife_fitcare.R;
import com.example.fitlife_fitcare.fragment.HomeFragment;
import com.example.fitlife_fitcare.fragment.ReportFragment;
import com.example.fitlife_fitcare.fragment.training;
import com.example.fitlife_fitcare.fragment.training;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        bottomNav = findViewById(R.id.bottom_nav);
        loadFragment(new HomeFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int id = item.getItemId();

            if (id == R.id.home) {
                selectedFragment = new HomeFragment();
            } else if (id == R.id.training) {
                selectedFragment = new training();
            } else if (id == R.id.report) {
                selectedFragment = new ReportFragment();
            }

            return loadFragment(selectedFragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framelayiut, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}