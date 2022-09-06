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
import com.afenstermaker.c868capstoneproject.ViewModel.AssignmentViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityAssignmentListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AssignmentList extends AppCompatActivity {
    private ActivityAssignmentListBinding binding;
    private AssignmentViewModel assignmentViewModel;
    private RecyclerView assignmentListRv;
    private FloatingActionButton assignmentFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_list);

        binding = ActivityAssignmentListBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        assignmentListRv = binding.assignmentRecyclerView;
        assignmentFab = binding.addAssignmentButton;

        final AssignmentListAdapter adapter = new AssignmentListAdapter(new AssignmentListAdapter.AssignmentDiff());
        assignmentListRv.setAdapter(adapter);
        assignmentListRv.setLayoutManager(new LinearLayoutManager(this));

        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getAllAssignments().observe(this, adapter::submitList);

        assignmentFab.setOnClickListener(view -> {
            Intent intent = new Intent(AssignmentList.this, EditAssignment.class);
            startActivityForResult(intent, 2);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode == RESULT_OK) {
            Assignment assignment = new Assignment(
                    0,
                    data.getStringExtra("className"),
                    data.getStringExtra("name"),
                    data.getStringExtra("type"),
                    data.getStringExtra("date")
            );
            assignment.setAssignmentID(assignmentViewModel.insert(assignment));
        }
    }
}