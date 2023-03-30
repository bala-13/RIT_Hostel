package com.example.rithostel;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.*;
public class MainActivity2 extends AppCompatActivity {
    Button b1;

    TextInputEditText t1,t2,t3;
    String username,email,password;
    FirebaseDatabase db;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();

        b1=(Button) findViewById(R.id.SignupBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1=(TextInputEditText) findViewById(R.id.suname);
                t2=(TextInputEditText) findViewById(R.id.semail);
                t3=(TextInputEditText) findViewById(R.id.spass);

                username = t1.getText().toString();;
                email = t2.getText().toString();
                password = t3.getText().toString();;


                reference = FirebaseDatabase.getInstance().getReference("Users");
                if(!username.isEmpty() && !email.isEmpty() && !password.isEmpty() ){
                    Users user= new Users(username,email,password);
                    reference.child(username).setValue(user).addOnSuccessListener(suc -> {
                        Toast.makeText(MainActivity2.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                        ((TextInputEditText) findViewById(R.id.suname)).setText("");
                        ((TextInputEditText) findViewById(R.id.semail)).setText("");
                        ((TextInputEditText) findViewById(R.id.spass)).setText("");
                    }).addOnFailureListener(er ->{
                        Toast.makeText(MainActivity2.this, "Failed to register", Toast.LENGTH_LONG).show();
                    });
                }
            }
        });

    }

}