package com.example.student_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPoint extends AppCompatActivity {
    // Ogrenci notlarinin girilmesi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_point);

        Button notEkle = findViewById(R.id.NotEkle);
        EditText number = findViewById(R.id.student_number);
        EditText firstExam = findViewById(R.id.first_exam);
        EditText secondExam = findViewById(R.id.second_exam);
        EditText first_perf = findViewById(R.id.first_perf);
        EditText second_perf = findViewById(R.id.second_perf);
        EditText student_comment = findViewById(R.id.student_comment);

        notEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper newDatabase = new DatabaseHelper(EnterPoint.this);
                String numara = number.getText().toString();
                String ilkYazili = firstExam.getText().toString();
                String ikinciYazili = secondExam.getText().toString();
                String ilkPerformans = first_perf.getText().toString();
                String ikinciPerformans = second_perf.getText().toString();
                String ogrYorum = student_comment.getText().toString();
                // integer olan notlarin ortalamasi bulma kisminda int tamSayi = Integer.parseInt(metin); kullanilir
                notEkle(newDatabase, numara, ilkYazili, ikinciYazili, ilkPerformans, ikinciPerformans, ogrYorum);
            }
        });

    }

    private void notEkle(DatabaseHelper database, String numara, String ilkYazili, String ikinciYazili, String ilkPerformans,
                         String ikinciPerformans, String ogrYorum) {
        // Database'e kaydetme

        database.notEkle(numara, ilkYazili, ikinciYazili, ilkPerformans, ikinciPerformans, ogrYorum);

        // Başarılı bir şekilde resim kaydedildiğinde kullanıcıya geri bildirim verilir
        Toast.makeText(this, "Öğrenci başarıyla kaydedildi!", Toast.LENGTH_SHORT).show();

    }

}