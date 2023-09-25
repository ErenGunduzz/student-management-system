package com.example.student_management_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddDeleteStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_add_delete);

        // database e ekleme silme işlemleri yapılacak.

        Button AddStudent = findViewById(R.id.OgrEkle);
        Button DeleteStudent = findViewById(R.id.OgrSil);
        EditText name = findViewById(R.id.add_student_activity_studentName);
        EditText number = findViewById(R.id.add_student_activity_studentNumber);
        EditText sinif = findViewById(R.id.add_student_activity_studentClass);

        AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper newDatabase = new DatabaseHelper(AddDeleteStudent.this);
                String numara = number.getText().toString();
                String ad = name.getText().toString();
                String sinifi = sinif.getText().toString();
                kaydet(newDatabase, numara, ad, sinifi);
            }
        });

        DeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Burada veri silme işlemini yapabilirsiniz.
                // Veri silme işlemini Database sınıfınızın VeriSil metodunu kullanarak yapabilirsiniz.
                String numara = number.getText().toString(); // Silinecek öğrencinin numarasını alın
                DatabaseHelper newDatabase = new DatabaseHelper(AddDeleteStudent.this);
                sil(newDatabase, numara);
            }
        });
    }

    private void kaydet(DatabaseHelper database, String numara, String isim, String sinifi) {
        database.yeniOgrenciEkle(numara, isim, sinifi);
        // Başarılı bir şekilde resim kaydedildiğinde kullanıcıya geri bildirim verilir
        Toast.makeText(this, "Öğrenci başarıyla kaydedildi!", Toast.LENGTH_SHORT).show();
    }

    private void sil(DatabaseHelper database, String numara){
        //Database'den silme
        database.ogrenciSil(numara);

        Toast.makeText(this, "Silme basarili...", Toast.LENGTH_SHORT).show();
    }


}