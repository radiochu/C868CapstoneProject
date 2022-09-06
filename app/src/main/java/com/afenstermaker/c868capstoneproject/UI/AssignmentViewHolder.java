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
    private final TextView assignmentName;
    private final TextView assignmentDate;
    private final TextView assignmentCourse;
    private final TextView assignmentType;
    private final CardView cv;

    public AssignmentViewHolder(AssignmentListItemBinding binding) {
        super(binding.getRoot());

        assignmentName = binding.assignmentName;
        assignmentCourse = binding.assignmentCourse;
        assignmentDate = binding.assignmentDate;
        assignmentType = binding.assignmentType;
        cv = binding.assignmentCardView;

        Context context = cv.getContext();
        cv.setOnClickListener(view -> {
            Intent intent = new Intent(context, AssignmentDetail.class);
            intent.putExtra("name", assignmentName.getText().toString());
            intent.putExtra("date", assignmentDate.getText().toString());
            intent.putExtra("course", assignmentCourse.getText().toString());
            intent.putExtra("type", assignmentType.getText().toString());
            context.startActivity(intent);
        });
    }

    public void bind(@NonNull Assignment assignment) {
        assignmentName.setText(assignment.getAssignmentName());
        assignmentDate.setText(assignment.getAssignmentDate());
        assignmentCourse.setText(assignment.getCourseName());
        assignmentType.setText(assignment.getAssignmentType());
    }
}
