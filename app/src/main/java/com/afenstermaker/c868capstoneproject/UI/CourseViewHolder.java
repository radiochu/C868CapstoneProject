package com.afenstermaker.c868capstoneproject.UI;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.CourseListItemBinding;

public class CourseViewHolder extends RecyclerView.ViewHolder {
    private final TextView courseName;
    private final TextView classroom;
    private final TextView teacherName;
    private final CardView cv;

    public CourseViewHolder(CourseListItemBinding binding) {
        super(binding.getRoot());

        courseName = binding.courseName;
        classroom = binding.classroom;
        teacherName = binding.teacherName;
        cv = binding.courseCardView;

        Context context = cv.getContext();
        cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, CourseDetail.class);
            intent.putExtra("name", courseName.getText().toString());
            intent.putExtra("classroom", classroom.getText().toString());
            intent.putExtra("teacher", teacherName.getText().toString());
            context.startActivity(intent);
        });
    }

    public void bind(Course course) {
        courseName.setText(course.getCourseName());
        classroom.setText(course.getClassroom());
        teacherName.setText(course.getTeacherName());
    }
}
