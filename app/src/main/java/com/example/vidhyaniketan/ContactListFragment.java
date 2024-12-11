package com.example.vidhyaniketan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ProgressBar progressBar;
    private ContactDatabaseHelper dbHelper;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        recyclerView = view.findViewById(R.id.contactRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);

        // Initialize database helper
        dbHelper = new ContactDatabaseHelper(requireContext());

        // Setup RecyclerView
        setupRecyclerView();

        // Load contacts
        loadContacts();
    }

    private void setupRecyclerView() {
        adapter = new ContactAdapter(requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
    }

    private void loadContacts() {
        progressBar.setVisibility(View.VISIBLE);

        // Use a separate thread for database operations
        new Thread(() -> {
            try {
                final List<Contact> contacts = dbHelper.getAllContacts();

                // Update UI on main thread
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        try {
                            progressBar.setVisibility(View.GONE);
                            if (contacts.isEmpty()) {
                                showNoContactsMessage();
                            } else {
                                adapter.setContacts(contacts);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            showError("Error displaying contacts");
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        progressBar.setVisibility(View.GONE);
                        showError("Error loading contacts");
                    });
                }
            }
        }).start();
    }

    private void showNoContactsMessage() {
        if (getContext() != null) {
            Toast.makeText(getContext(), "No contacts found", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(String message) {
        if (getContext() != null) {
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }
}