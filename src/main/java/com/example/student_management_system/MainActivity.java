package com.example.student_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button EditButton;
    private Button DisplayButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //xml dosyasındaki widget'lari bulur
        EditButton = findViewById(R.id.edit_button);
        DisplayButton= findViewById(R.id.display_button);

        // Duzenle ekraninin butonunun tiklanma islemi
        EditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentManagement.class);
                startActivity(intent);
            }
        });
        DisplayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainDisplayPage.class);
                startActivity(intent);
            }
        });

    }
}