package com.afenstermaker.c868capstoneproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityEditAssignmentBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditAssignment extends AppCompatActivity {
    private EditText assignmentName;
    private Spinner assignmentType;
    private Spinner assignmentClass;
    private TextView assignmentDate;
    private Button assignmentDateButton;
    private Button saveAssignment;
    private Button cancelAssignment;
    private ActivityEditAssignmentBinding binding;
    private final ArrayList<String> assignmentTypes = new ArrayList<>();
    final Calendar assignmentDateCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener assignmentDateSetListener;
    private String dateFormat = "MM/dd/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        binding = ActivityEditAssignmentBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        assignmentName = binding.editAssignmentName;
        assignmentType = binding.assignmentTypeSpinner;
        assignmentClass = binding.assignmentClassSpinner;
        assignmentDate = binding.assignmentDateLabel;
        assignmentDateButton = binding.setAssignmentDateButton;
        saveAssignment = binding.saveAssignmentButton;
        cancelAssignment = binding.cancelAssignmentButton;

        ArrayAdapter<Integer> classAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentClass.setAdapter(classAdapter);

        CourseViewModel courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, courses -> {
            classAdapter.clear();
            for (int i = 0; i < courses.size(); i++) {
                classAdapter.add(courses.get(i).getCourseID());
            }
        });

        ArrayAdapter<String> assignmentTypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, assignmentTypes);
        assignmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignmentType.setAdapter(assignmentTypeAdapter);
        assignmentTypes.add("Quiz");
        assignmentTypes.add("Test");
        assignmentTypes.add("Project");
        assignmentTypes.add("Homework");
        assignmentTypes.add("Other");
        assignmentTypeAdapter.addAll(assignmentTypes);
    }
}