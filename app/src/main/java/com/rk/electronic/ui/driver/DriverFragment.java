package com.rk.electronic.ui.driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rk.electronic.R;
import com.rk.electronic.adapter.DriverAdapter;
import com.rk.electronic.model.Driver;

import java.util.ArrayList;
import java.util.List;

public class DriverFragment extends Fragment {
    private RecyclerView recyclerView;
    private DriverAdapter adapter;
    private EditText editSearch;
    private Button btnSearch;
    private DatabaseReference databaseRef;
    private ValueEventListener valueEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_driver, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        editSearch = root.findViewById(R.id.editSearch);
        btnSearch = root.findViewById(R.id.btnSearch);

        databaseRef = FirebaseDatabase.getInstance().getReference("electronic").child("driver");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editSearch.getText().toString();
                searchDrivers(searchQuery);
            }
        });

        // Tambahkan listener untuk tombol "Cancel Search"
        Button btnCancelSearch = root.findViewById(R.id.btnCancelSearch);
        btnCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSearch();
            }
        });

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Driver> driverList = new ArrayList<>();
                for (DataSnapshot driverSnapshot : snapshot.getChildren()) {
                    Driver driver = driverSnapshot.getValue(Driver.class);
                    driverList.add(driver);
                }

                adapter = new DriverAdapter(driverList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        };

        databaseRef.addValueEventListener(valueEventListener);

        return root;
    }

    private void searchDrivers(String searchQuery) {
        Query query = databaseRef.orderByChild("name").startAt(searchQuery).endAt(searchQuery + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Driver> driverList = new ArrayList<>();
                for (DataSnapshot driverSnapshot : snapshot.getChildren()) {
                    Driver driver = driverSnapshot.getValue(Driver.class);
                    driverList.add(driver);
                }

                adapter = new DriverAdapter(driverList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        });
    }

    private void cancelSearch() {

        editSearch.setText("");
        databaseRef.removeEventListener(valueEventListener);
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Driver> driverList = new ArrayList<>();
                for (DataSnapshot driverSnapshot : snapshot.getChildren()) {
                    Driver driver = driverSnapshot.getValue(Driver.class);
                    driverList.add(driver);
                }
                adapter = new DriverAdapter(driverList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        });
    }
}
