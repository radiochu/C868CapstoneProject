package com.afenstermaker.c868capstoneproject.UI;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityCourseListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CourseList extends AppCompatActivity {
    private ActivityCourseListBinding binding;
    private CourseViewModel courseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        binding = ActivityCourseListBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        RecyclerView courseListRv = binding.courseRecyclerView;
        FloatingActionButton courseFab = binding.addCourseButton;

        final CourseListAdapter adapter = new CourseListAdapter(new CourseListAdapter.CourseDiff());
        courseListRv.setAdapter(adapter);
        courseListRv.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            adapter.submitList(courses);
        });

        courseFab.setOnClickListener(view -> {
            startActivityForResult(new Intent(CourseList.this, EditCourse.class), 1);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Course course = new Course(
                    0,
                    data.getStringExtra("name"),
                    data.getStringExtra("room"),
                    data.getStringExtra("teacher"),
                    data.getStringExtra("phone"),
                    data.getStringExtra("email"),
                    data.getStringExtra("notes")
            );
            course.setCourseID(courseViewModel.insert(course));
        }
    }
}