package com.rk.electronic.ui.profile;

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
import com.rk.electronic.adapter.ProfileAdapter;
import com.rk.electronic.model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProfileAdapter adapter;
    private DatabaseReference profileRef;
    private ValueEventListener valueEventListener;
    private EditText editSearch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        profileRef = FirebaseDatabase.getInstance().getReference("electronic").child("users");
        editSearch = root.findViewById(R.id.editSearch);

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Profile> profileList = new ArrayList<>();
                for (DataSnapshot profileSnapshot : dataSnapshot.getChildren()) {
                    Profile profile = profileSnapshot.getValue(Profile.class);
                    if (profile != null) {
                        profileList.add(profile);
                    }
                }

                adapter = new ProfileAdapter(getActivity(), profileList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle data reading error
            }
        };

        profileRef.addValueEventListener(valueEventListener);

        // Search button
        Button btnSearch = root.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchQuery = editSearch.getText().toString().trim();
                searchProfiles(searchQuery);
            }
        });

        // Cancel search button
        Button btnCancelSearch = root.findViewById(R.id.btnCancelSearch);
        btnCancelSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSearch();
            }
        });

        return root;
    }

    private void searchProfiles(String searchQuery) {
        Query query = profileRef.orderByChild("name").startAt(searchQuery).endAt(searchQuery + "\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Profile> profileList = new ArrayList<>();
                for (DataSnapshot profileSnapshot : dataSnapshot.getChildren()) {
                    Profile profile = profileSnapshot.getValue(Profile.class);
                    if (profile != null) {
                        profileList.add(profile);
                    }
                }

                adapter = new ProfileAdapter(getActivity(), profileList);
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
        profileRef.addValueEventListener(valueEventListener);
    }
}
