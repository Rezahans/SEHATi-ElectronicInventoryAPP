package com.rk.electronic.ui.profile;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {
    private static final String USERS_PATH = "electronic/users";
    private DatabaseReference databaseReference;

    public FirebaseManager() {
        // Initialize Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Get a reference to "users" in the database
        databaseReference = firebaseDatabase.getReference(USERS_PATH);
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
