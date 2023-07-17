package com.rk.electronic.ui.dashboard;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {
    private static final String ITEM_PATH = "electronic/item";
    private DatabaseReference databaseReference;

    public FirebaseManager() {
        // Inisialisasi Firebase Realtime Database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        // Mendapatkan referensi ke "item" dalam database
        databaseReference = firebaseDatabase.getReference(ITEM_PATH);
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
