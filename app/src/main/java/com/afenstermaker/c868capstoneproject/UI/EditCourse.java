package com.afenstermaker.c868capstoneproject.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityEditCourseBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditCourse extends AppCompatActivity {
    private TextView courseID;
    private EditText courseName;
    private EditText classroom;
    private EditText teacherName;
    private EditText teacherPhone;
    private EditText teacherEmail;
    private EditText courseNotes;
    private ActivityEditCourseBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        binding = ActivityEditCourseBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        courseID = binding.courseIDTV;
        courseName = binding.editCourseName;
        classroom = binding.editClassroom;
        teacherName = binding.editTeacherName;
        teacherPhone = binding.editTeacherPhone;
        teacherEmail = binding.editTeacherEmail;
        courseNotes = binding.editCourseNotes;
        final Button saveCourse = binding.saveCourseButton;
        final Button cancelCourse = binding.cancelCourseButton;

        if (getIntent().getExtras() != null) {
            courseID.setText(getIntent().getStringExtra("courseID"));
            courseName.setText(getIntent().getStringExtra("courseName"));
            classroom.setText(getIntent().getStringExtra("classroom"));
            teacherName.setText(getIntent().getStringExtra("teacherName"));
            teacherPhone.setText(getIntent().getStringExtra("teacherPhone"));
            teacherEmail.setText(getIntent().getStringExtra("teacherEmail"));
            courseNotes.setText(getIntent().getStringExtra("courseNotes"));
        }

        saveCourse.setOnClickListener(view -> {
            Intent replyIntent = new Intent();

            if (!validateInput()) {
                Toast.makeText(getApplicationContext(), "Some values were missing. Course not saved.", Toast.LENGTH_LONG).show();
            } else {
                String name = courseName.getText().toString();
                String room = classroom.getText().toString();
                String teacher = teacherName.getText().toString();
                String phone = teacherPhone.getText().toString();
                String email = teacherEmail.getText().toString();
                String notes = courseNotes.getText().toString();

                if (!courseID.getText().toString().isEmpty()) {
                    replyIntent.putExtra("courseID", courseID.getText().toString());
                }
                replyIntent.putExtra("name", name);
                replyIntent.putExtra("room", room);
                replyIntent.putExtra("teacher", teacher);
                replyIntent.putExtra("phone", phone);
                replyIntent.putExtra("email", email);
                replyIntent.putExtra("notes", notes);

                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });

        cancelCourse.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditCourse.this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Cancel without saving changes?");
            builder.setPositiveButton("Yes", (dialogInterface, i) -> {
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
            });
            builder.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    private boolean validateInput() {
        if (courseName.getText().toString().trim().isEmpty()) {
            courseName.setError("Course name is required");
            return false;
        }
        else if (classroom.getText().toString().trim().isEmpty()) {
            classroom.setError("Classroom is required");
            return false;
        }
        else if (teacherName.getText().toString().trim().isEmpty()) {
            teacherName.setError("Teacher name is required");
            return false;
        }
        else if (teacherPhone.getText().toString().trim().isEmpty()) {
            teacherPhone.setError("Teacher phone number is required");
            return false;
        }
        else if (teacherEmail.getText().toString().trim().isEmpty()) {
            teacherEmail.setError("Teacher email address is required");
            return false;
        }
        else {
            return true;
        }
    }
}