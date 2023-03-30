package com.example.rithostel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class YearsSelect extends AppCompatActivity {


    Button b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_years_select);
        getSupportActionBar().hide();

        b1=(Button) findViewById(R.id.First);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(YearsSelect.this, FirstYear.class));
            }
        });
    }
}