package com.rk.electronic.ui.supplier;

import android.content.Intent;
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
import com.rk.electronic.adapter.SupplierAdapter;
import com.rk.electronic.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierFragment extends Fragment {
    private RecyclerView recyclerView;
    private SupplierAdapter adapter;
    private EditText editSearch;
    private Button btnSearch;
    private Button btnCancelSearch;
    private DatabaseReference databaseRef;
    private ValueEventListener valueEventListener;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_supplier, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        editSearch = root.findViewById(R.id.editSearch);
        btnSearch = root.findViewById(R.id.btnSearch);
        btnCancelSearch = root.findViewById(R.id.btnCancelSearch);

        databaseRef = FirebaseDatabase.getInstance().getReference("electronic").child("supplier");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editSearch.getText().toString();
                searchSuppliers(searchQuery);
            }
        });

        btnCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSearch();
            }
        });

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Supplier> supplierList = new ArrayList<>();
                for (DataSnapshot supplierSnapshot : snapshot.getChildren()) {
                    Supplier supplier = supplierSnapshot.getValue(Supplier.class);
                    supplierList.add(supplier);
                }

                adapter = new SupplierAdapter(supplierList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        };

        databaseRef.addValueEventListener(valueEventListener);

        // Get intent from notification
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.getBooleanExtra("close_app", false)) {
            // Close the app
            if (getActivity() != null) {
                getActivity().finish();
            }
        }

        return root;
    }

    private void searchSuppliers(String searchQuery) {
        Query query = databaseRef.orderByChild("name").startAt(searchQuery).endAt(searchQuery + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Supplier> supplierList = new ArrayList<>();
                for (DataSnapshot supplierSnapshot : snapshot.getChildren()) {
                    Supplier supplier = supplierSnapshot.getValue(Supplier.class);
                    supplierList.add(supplier);
                }

                adapter = new SupplierAdapter(supplierList);
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
                List<Supplier> supplierList = new ArrayList<>();
                for (DataSnapshot supplierSnapshot : snapshot.getChildren()) {
                    Supplier supplier = supplierSnapshot.getValue(Supplier.class);
                    supplierList.add(supplier);
                }
                adapter = new SupplierAdapter(supplierList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        });
    }
}
