package com.example.student_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StudentManagement extends AppCompatActivity {

    private Button AddDeleteButton;
    private Button EnterPointButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_page);

        AddDeleteButton = findViewById(R.id.OgrEkleSil);
        EnterPointButton = findViewById(R.id.NotGir);


        AddDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentManagement.this, AddDeleteStudent.class);
                startActivity(intent);
            }
        });
        EnterPointButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentManagement.this, EnterPoint.class);
                startActivity(intent);
            }
        });





    }
}
