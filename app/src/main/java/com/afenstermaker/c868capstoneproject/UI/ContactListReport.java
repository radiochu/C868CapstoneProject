package com.afenstermaker.c868capstoneproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.afenstermaker.c868capstoneproject.R;
import com.afenstermaker.c868capstoneproject.ViewModel.CourseViewModel;
import com.afenstermaker.c868capstoneproject.databinding.ActivityContactListReportBinding;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ContactListReport extends AppCompatActivity {
    private ActivityContactListReportBinding binding;
    private TextView reportDate;
    private RecyclerView rv;
    private CourseViewModel courseViewModel;
    private SearchView contactSearch;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mma", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list_report);

        binding = ActivityContactListReportBinding.inflate(getLayoutInflater());
        View viewToBind = binding.getRoot();
        setContentView(viewToBind);

        reportDate = binding.reportDate;
        rv = binding.contactListRv;
        contactSearch = binding.contactSearch;

        reportDate.setText(LocalDateTime.now().format(formatter));

        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAllCourses().observe(this, adapter::submitList);

        contactSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}