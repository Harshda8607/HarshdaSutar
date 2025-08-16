package com.example.fitlife_fitcare.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitlife_fitcare.R;
import com.example.fitlife_fitcare.model.workout_model;

import java.util.List;

public class workout_adapter extends RecyclerView.Adapter<workout_adapter.ViewHolder> {

    private List<workout_model> courseList;

    public workout_adapter(List<workout_model> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        workout_model course = courseList.get(position);
        holder.tvCourseName.setText(course.getCourseName());
        holder.tvDate.setText(course.getDate());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.CName);
            tvDate = itemView.findViewById(R.id.CDate);
        }
    }
}


//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.example.fitlife_fitcare.R;
//import com.example.fitlife_fitcare.model.workout_model;
//import java.util.List;
//
//public class workout_adapter extends RecyclerView.Adapter<workout_adapter.CourseViewHolder> {
//
//    private List<workout_model> courseList;
//
//    public workout_adapter(List<workout_model> courseList) {
//        this.courseList = courseList;
//    }
//
//    @NonNull
//    @Override
//    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.recent_workout, parent, false);
//        return new CourseViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
//        workout_model course = courseList.get(position);
//        holder.tvCourseName.setText(course.getCourseName());
//        holder.tvCourseDate.setText(course.getCourseDate());
//    }
//
//    @Override
//    public int getItemCount() {
//        return courseList.size();
//    }
//
//    public static class CourseViewHolder extends RecyclerView.ViewHolder {
//        TextView tvCourseName, tvCourseDate;
//
//        public CourseViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvCourseName = itemView.findViewById(R.id.tvcoarse);
//            tvCourseDate = itemView.findViewById(R.id.tvdate);
//        }
//    }
//}