package com.afenstermaker.c868capstoneproject.UI;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentListItemBinding;

public class AssignmentViewHolder extends RecyclerView.ViewHolder {
    final TextView assignmentID;
    final TextView assignmentName;
    final TextView assignmentDate;
    final TextView assignmentCourse;
    final TextView assignmentType;
    final TextView assessmentClassID;
    final CardView cv;

    AssignmentViewHolder(AssignmentListItemBinding binding) {
        super(binding.getRoot());

        assignmentID = binding.assignmentID;
        assignmentName = binding.assignmentName;
        assignmentCourse = binding.assignmentCourse;
        assignmentDate = binding.assignmentDate;
        assignmentType = binding.assignmentType;
        assessmentClassID = binding.assignmentClassID;
        cv = binding.assignmentCardView;

        Context context = cv.getContext();
        cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, AssignmentDetail.class);
            intent.putExtra("ID", assignmentID.getText().toString());
            intent.putExtra("name", assignmentName.getText().toString());
            intent.putExtra("dueDate", assignmentDate.getText().toString());
            intent.putExtra("class", assignmentCourse.getText().toString());
            intent.putExtra("type", assignmentType.getText().toString());
            context.startActivity(intent);
        });
    }

    public void bind(Assignment assignment) {
        assignmentID.setText(String.valueOf(assignment.getAssignmentID()));
        assignmentName.setText(assignment.getAssignmentName());
        assignmentDate.setText(assignment.getAssignmentDate());
        assignmentCourse.setText(assignment.getCourseName());
        assessmentClassID.setText(String.valueOf(assignment.getCourseID()));
        assignmentType.setText(assignment.getAssignmentType());
    }
}
