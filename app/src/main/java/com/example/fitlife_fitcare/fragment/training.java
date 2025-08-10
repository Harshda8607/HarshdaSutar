package com.example.fitlife_fitcare.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.fitlife_fitcare.R;
import com.example.fitlife_fitcare.angle_booty;
import com.example.fitlife_fitcare.ease_yoga;
import com.example.fitlife_fitcare.flat_belly;
import com.example.fitlife_fitcare.indoor_walking;
import com.example.fitlife_fitcare.lazy_bed;
import com.example.fitlife_fitcare.morning_warmup;
import com.example.fitlife_fitcare.office_workout;
import com.example.fitlife_fitcare.postrun_warmup;
import com.example.fitlife_fitcare.prerun_warmup;
import com.example.fitlife_fitcare.sitted_yoga;
import com.example.fitlife_fitcare.sleepy_time_stretch;
import com.example.fitlife_fitcare.slim_legss;
import com.example.fitlife_fitcare.standing_yoga;
import com.example.fitlife_fitcare.sun_salutation;
import com.example.fitlife_fitcare.toned_arms;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link training#newInstance} factory method to
 * create an instance of this fragment.
 */
public class training extends Fragment {

    Button b1,b2,b3;

    CardView g1,g2,g3,g4,r1,r2,r3,r4,y1,y2,y3,y4;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public training() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static training newInstance(String param1, String param2) {
        training fragment = new training();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        b1=view.findViewById(R.id.training_random1);
        b2=view.findViewById(R.id.training_random2);
        b3=view.findViewById(R.id.training_random3);

        g1=view.findViewById(R.id.training_goal1);
        g2=view.findViewById(R.id.training_goal2);
        g3=view.findViewById(R.id.training_goal3);
        g4=view.findViewById(R.id.training_goal4);

        r1=view.findViewById(R.id.training_routine1);
        r2=view.findViewById(R.id.training_routine2);
        r3=view.findViewById(R.id.training_routine3);
        r4=view.findViewById(R.id.training_routine4);

        y1=view.findViewById(R.id.training_yoga1);
        y2=view.findViewById(R.id.training_yoga2);
        y3=view.findViewById(R.id.training_yoga3);
        y4=view.findViewById(R.id.training_yoga4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(), lazy_bed.class);
                startActivity(i1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getActivity(), office_workout.class);
                startActivity(i2);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), indoor_walking.class);
                startActivity(i3);
            }
        });

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), flat_belly.class);
                startActivity(i3);
            }
        });
        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), angle_booty.class);
                startActivity(i3);
            }
        });
        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), toned_arms.class);
                startActivity(i3);
            }
        });
        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), slim_legss.class);
                startActivity(i3);
            }
        });

        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), slim_legss.class);
                startActivity(i3);
            }
        });

        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), morning_warmup.class);
                startActivity(i3);
            }
        });
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), prerun_warmup.class);
                startActivity(i3);
            }
        });
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), postrun_warmup.class);
                startActivity(i3);
            }
        });
        r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), sleepy_time_stretch.class);
                startActivity(i3);
            }
        });

        y1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), ease_yoga.class);
                startActivity(i3);
            }
        });
        y2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), sun_salutation.class);
                startActivity(i3);
            }
        });
        y3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), sitted_yoga.class);
                startActivity(i3);
            }
        });
        y4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getActivity(), standing_yoga.class);
                startActivity(i3);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training, container, false);
    }
}