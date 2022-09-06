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
            startForResult.launch(new Intent(this, EditAssignment.class));
        });
    }

    ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                Assignment assignment = new Assignment(
                        0,
                        data.getStringExtra(EditAssignment.EXTRA_REPLY_TITLE),
                        data.getStringExtra(EditAssignment.EXTRA_REPLY_DESCRIPTION),
                        data.getStringExtra(EditAssignment.EXTRA_REPLY_DUE_DATE),
                        data.getStringExtra(EditAssignment.EXTRA_REPLY_COURSE_ID)
                );
                assignmentViewModel.insert(assignment);
            }
        }
    });
}