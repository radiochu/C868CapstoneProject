package com.afenstermaker.c868capstoneproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.databinding.ActivityAssignmentDetailBinding;

public class AssignmentDetail extends AppCompatActivity {
    private TextView assignmentID;
    private TextView assignmentName;
    private TextView assignmentType;
    private TextView assignmentDueDate;
    private TextView assignmentClass;
    private ActivityAssignmentDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        binding = ActivityAssignmentDetailBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        assignmentID = binding.assignmentDetailID;
        assignmentName = binding.assignmentDetailName;
        assignmentType = binding.assignmentDetailType;
        assignmentDueDate = binding.assignmentDetailDueDate;
        assignmentClass = binding.assignmentDetailCourseID;

        if (getIntent().getExtras() != null) {
            assignmentID.setText(getIntent().getStringExtra("ID"));
            assignmentName.setText(getIntent().getStringExtra("name"));
            assignmentType.setText(getIntent().getStringExtra("type"));
            assignmentDueDate.setText(getIntent().getStringExtra("dueDate"));
            assignmentClass.setText(getIntent().getStringExtra("class"));
        }
    }
}