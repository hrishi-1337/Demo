package com.hrishikesh.Demo;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by j on 05-10-2017.
 */

public class FirebaseHandler {
    public static List<Shop> getShop(double lat, double lng) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final List<Shop> mShops = new ArrayList<Shop>();
        db.collection("Shops")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Shop shop = document.toObject(Shop.class);
                        mShops.add(shop);
                    }
                } else {
                    Log.e("Log", "Error getting documents: ", task.getException());
                }
            }
        });
        return mShops;
    }
}
