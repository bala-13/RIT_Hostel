package com.example.rithostel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    DatabaseReference reference;
    String username,password,orgpass;
    TextInputEditText t1,t2;
     FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mAuth=FirebaseAuth.getInstance();
        b1=(Button)findViewById(R.id.nextPage);
        b2=(Button)findViewById(R.id.loginBtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t1=(TextInputEditText)findViewById(R.id.suname);
                t2=(TextInputEditText)findViewById(R.id.spass);
                username=t1.getText().toString();
                password=t2.getText().toString();
                reference = FirebaseDatabase.getInstance().getReference("Users");


                Query checkUser=reference.orderByChild("username").equalTo(username);

                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                orgpass=snapshot.child(username).child("password").getValue(String.class);
                                if(orgpass.equals(password)){
                                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity.this, YearsSelect.class));
                                }
                                ((TextInputEditText) findViewById(R.id.suname)).setText("");
                                ((TextInputEditText) findViewById(R.id.spass)).setText("");
                            }else{
                                ((TextInputEditText) findViewById(R.id.suname)).setText("");
                                ((TextInputEditText) findViewById(R.id.spass)).setText("");
                                Toast.makeText(MainActivity.this, "Login Invalid", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    if (password.equals(orgpass)) {
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }

            }
        });


    }
}