package com.afenstermaker.c868capstoneproject.UI;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.afenstermaker.c868capstoneproject.Entity.Assignment;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentListItemBinding;
import com.afenstermaker.c868capstoneproject.databinding.AssignmentScheduleRowBinding;

public class AssignmentRowViewHolder extends RecyclerView.ViewHolder {
    final TextView assignmentName;
    final TextView assignmentDate;
    final TextView assignmentCourse;

    AssignmentRowViewHolder(AssignmentScheduleRowBinding binding) {
        super(binding.getRoot());

        assignmentName = binding.assignmentRowName;
        assignmentDate = binding.assignmentRowDate;
        assignmentCourse = binding.assignmentRowClass;
    }

    public void bind(Assignment assignment) {
        assignmentName.setText(assignment.getAssignmentName());
        assignmentDate.setText(assignment.getAssignmentDate());
        assignmentCourse.setText(assignment.getCourseName());
    }
}
