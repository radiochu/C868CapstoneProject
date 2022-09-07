package com.afenstermaker.c868capstoneproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.AssignmentViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityCourseDetailBinding;

public class CourseDetail extends AppCompatActivity {
    private TextView courseID;
    private TextView courseName;
    private TextView classroom;
    private TextView teacherName;
    private TextView teacherPhone;
    private TextView teacherEmail;
    private TextView courseNotes;
    private ActivityCourseDetailBinding binding;
    private AssignmentViewModel assignmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        binding = ActivityCourseDetailBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        courseID = binding.courseDetailID;
        courseName = binding.courseDetailName;
        classroom = binding.courseDetailClassroom;
        teacherName = binding.courseDetailTeacherName;
        teacherPhone = binding.courseDetailTeacherPhone;
        teacherEmail = binding.courseDetailTeacherEmail;
        courseNotes = binding.courseDetailNotes;

        RecyclerView assignmentsRV = binding.courseDetailAssignmentsRV;
        final AssignmentListAdapter adapter = new AssignmentListAdapter(new AssignmentListAdapter.AssignmentDiff());
        assignmentsRV.setAdapter(adapter);
        assignmentsRV.setLayoutManager(new LinearLayoutManager(this));

        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getAssignmentsByCourse(getIntent().getIntExtra("id", -1)).observe(this, assignments -> {
            adapter.submitList(assignments);
        });

        if (getIntent().getExtras() != null) {
            courseID.setText(String.valueOf(getIntent().getIntExtra("id", -1)));
            courseName.setText(getIntent().getStringExtra("name"));
            classroom.setText(getIntent().getStringExtra("room"));
            teacherName.setText(getIntent().getStringExtra("teacher"));
            teacherPhone.setText(getIntent().getStringExtra("phone"));
            teacherEmail.setText(getIntent().getStringExtra("email"));
            courseNotes.setText(getIntent().getStringExtra("notes"));
        }
    }
}