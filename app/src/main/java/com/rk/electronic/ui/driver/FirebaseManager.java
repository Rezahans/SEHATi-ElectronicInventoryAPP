package com.rk.electronic.ui.driver;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {
    private static final String ITEM_PATH = "electronic/driver";
    private DatabaseReference databaseReference;

    public FirebaseManager() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(ITEM_PATH);
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}

