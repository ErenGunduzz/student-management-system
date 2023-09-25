package com.example.student_management_system;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

public class MainDisplayPage extends AppCompatActivity {

    private Button DisplayStudent;
    private Button DisplayClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);

        DisplayStudent = findViewById(R.id.OgrenciGor);
        DisplayClass = findViewById(R.id.SinifGor);

        DisplayStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDisplayPage.this, com.example.student_management_system.DisplayStudent.class);
                startActivity(intent);
            }
        });
        DisplayClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainDisplayPage.this, DisplayClass.class);
                startActivity(intent);
            }
        });
    }
}