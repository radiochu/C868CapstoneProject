package com.afenstermaker.c868capstoneproject.UI;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.afenstermaker.c868capstoneproject.Entity.Course;
import com.afenstermaker.c868capstoneproject.databinding.CourseListItemBinding;

public class CourseListAdapter extends ListAdapter<Course, CourseViewHolder> {
    public CourseListAdapter(@NonNull DiffUtil.ItemCallback<Course> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CourseListItemBinding binding = CourseListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        Course current = getItem(position);
        holder.bind(current);
    }

    static class CourseDiff extends DiffUtil.ItemCallback<Course> {
        @Override
        public boolean areItemsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse) {
            return oldCourse == newCourse;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Course oldCourse, @NonNull Course newCourse) {
            return oldCourse.getCourseID() == newCourse.getCourseID();

        }
    }
}