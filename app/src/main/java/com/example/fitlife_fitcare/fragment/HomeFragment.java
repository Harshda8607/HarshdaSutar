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

import com.example.fitlife_fitcare.abs_advanced;
import com.example.fitlife_fitcare.flatstomach;
import com.example.fitlife_fitcare.abs_beginner;
import com.example.fitlife_fitcare.abs_intermediate;
import com.example.fitlife_fitcare.arm_advanced;
import com.example.fitlife_fitcare.arm_beginner;
import com.example.fitlife_fitcare.arm_intermediate;
import com.example.fitlife_fitcare.Butt_advan;
import com.example.fitlife_fitcare.Butt_begin;
import com.example.fitlife_fitcare.Butt_inter;
import com.example.fitlife_fitcare.Chest_advanc;
import com.example.fitlife_fitcare.Chest_begin;
import com.example.fitlife_fitcare.Chest_inter;
import com.example.fitlife_fitcare.Faceyoga;
import com.example.fitlife_fitcare.FullBody;
import com.example.fitlife_fitcare.Lowerbody;
import com.example.fitlife_fitcare.R;
import com.example.fitlife_fitcare.Thigh_advanced;
import com.example.fitlife_fitcare.Thigh_begin;
import com.example.fitlife_fitcare.Thigh_intermediate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    Button bt1,bt2,bt3,bt4;
    CardView cabs1,cabs2,cabs3,cthigh1,cthigh2,cthigh3,carm1,carm2,carm3,cbutt1,cbutt2,cbutt3,cchest1,cchest2,cchest3;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt1=requireView().findViewById(R.id.btn1_fullbody);
        bt2=requireView().findViewById(R.id.btn2_lowerbody);
        bt3=requireView().findViewById(R.id.btn3_flatstomach);
        bt4=requireView().findViewById(R.id.btn4_faceyoga);
        cabs1=requireView().findViewById(R.id.abs_workout1);
        cabs2=requireView().findViewById(R.id.abs_workout_2);
        cabs3=requireView().findViewById(R.id.abs_workout_3);
        cthigh1=requireView().findViewById(R.id.thigh_workout_1);
        cthigh2=requireView().findViewById(R.id.thighr_workout2);
        cthigh3=requireView().findViewById(R.id.thigh_workout3);
        carm1=requireView().findViewById(R.id.arm_workout1);
        carm2=requireView().findViewById(R.id.arm_workout2);
        carm3=requireView().findViewById(R.id.arm_workout3);
        cbutt1=requireView().findViewById(R.id.butt_workout1);
        cbutt2=requireView().findViewById(R.id.butt_workout2);
        cbutt3=requireView().findViewById(R.id.butt_workout3);
        cchest1=requireView().findViewById(R.id.chest_workout1);
        cchest2=requireView().findViewById(R.id.chest_workout2);
        cchest3=requireView().findViewById(R.id.chest_workout3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(requireActivity(), FullBody.class);
                startActivity(intent1);

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(requireActivity(), Lowerbody.class);
                startActivity(intent2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3=new Intent(requireActivity(), flatstomach.class);
                startActivity(intent3);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4=new Intent(requireActivity(), Faceyoga.class);
                startActivity(intent4);
            }
        });
        cabs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), abs_beginner.class);
                startActivity(intent);
            }
        });
        cabs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), abs_intermediate.class);
                startActivity(intent);
            }
        });
        cabs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), abs_advanced.class);
                startActivity(intent);
            }
        });
        cthigh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Thigh_begin.class);
                startActivity(intent);
            }
        });
        cthigh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Thigh_intermediate.class);
                startActivity(intent);
            }
        });
        cthigh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Thigh_advanced.class);
                startActivity(intent);
            }
        });
        carm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), arm_beginner.class);
                startActivity(intent);
            }
        });
        carm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), arm_intermediate.class);
                startActivity(intent);
            }
        });
        carm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), arm_advanced.class);
                startActivity(intent);
            }
        });
        cbutt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Butt_begin.class);
                startActivity(intent);
            }
        });
        cbutt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Butt_inter.class);
                startActivity(intent);
            }
        });
        cbutt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Butt_begin.class);
                startActivity(intent);
            }
        });
        cbutt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Butt_inter.class);
                startActivity(intent);
            }
        });
        cbutt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Butt_advan.class);
                startActivity(intent);
            }
        });
        cchest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Chest_begin.class);
                startActivity(intent);
            }
        });
        cchest2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Chest_inter.class);
                startActivity(intent);
            }
        });
        cchest3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(requireActivity(), Chest_advanc.class);
                startActivity(intent);
            }
        });
    }
}