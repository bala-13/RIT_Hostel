package com.example.rithostel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstYear extends AppCompatActivity {

    Button b1;
   int val;
   String str;
   Integer myval;
    ArrayList<String> arr = new ArrayList<>();
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_year);
        getSupportActionBar().hide();
        b1=(Button) findViewById(R.id.room1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference("Users").child("FirstYear").child("Room1");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                       arr.clear();

                       for(DataSnapshot data : snapshot.getChildren()){

                             arr.add(data.getKey());
                       }
                       str=arr.get(4);
                       val=Integer.parseInt(str);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                        HashMap hashmap = new HashMap();
                        hashmap.put("RoomOne",1);
                        reference.child("FirstYear").updateChildren(hashmap).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {
                                Toast.makeText(FirstYear.this, String.valueOf(val), Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FirstYear.this, "Room Full", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

    }
}