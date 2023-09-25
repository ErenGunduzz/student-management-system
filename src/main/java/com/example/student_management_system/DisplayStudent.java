package com.example.student_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayStudent extends AppCompatActivity {

    private EditText studentName;
    private Button searchStudent;
    private TextView resultText;
    private DatabaseHelper database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_student);

        database = new DatabaseHelper(this);
        studentName = findViewById(R.id.student_name_editText);
        searchStudent = findViewById(R.id.student_search_button);
        resultText = findViewById(R.id.display_student_text_view);

        searchStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database'den ogrenciyi cekme
                String studentNameValue = studentName.getText().toString();
                String studentData = getStudentData(studentNameValue);
                if (studentData != null) {
                    resultText = findViewById(R.id.display_student_text_view);
                    resultText.setText(studentData);
                } else {
                    Toast.makeText(DisplayStudent.this, "Ogrenci bulunamadı!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //isme gore ogrenci verilerini ceken fonksiyon
    private String getStudentData(String studentName) {
        String numara = "";
        String isim = "";
        String sinif = "";
        String birinci_yazili = "";
        String ikinci_yazili = "";
        String birinci_performans = "";
        String ikinci_performans = "";
        String ogrenci_yorum = "";

        // Veritabanı işlemleri için DatabaseHelper sınıfının bir örneğini oluşturun
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        // Veritabanından öğrenci bilgilerini sorgulayın
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Sorgu için koşulları hazırlayın
        String[] columnsToReturn = {
                DatabaseHelper.COLUMN_NUMBER,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_CLASS,
                DatabaseHelper.COLUMN_FIRST_EXAM,
                DatabaseHelper.COLUMN_SECOND_EXAM,
                DatabaseHelper.COLUMN_PERF_BIR,
                DatabaseHelper.COLUMN_PERF_IKI,
                DatabaseHelper.COLUMN_COMMENT
        };

        String selection = DatabaseHelper.COLUMN_NAME + " = ?";
        String[] selectionArgs = {studentName};

        // Sorguyu çalıştırın ve sonucu bir Cursor nesnesine alın
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                columnsToReturn,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        // Eğer sorgu sonucu veri bulunursa, verileri çek
        if (cursor != null && cursor.moveToFirst()) {
            numara = cursor.getString(0);
            isim = studentName;
            sinif = cursor.getString(2);
            birinci_yazili = cursor.getString(3);
            ikinci_yazili = cursor.getString(4);
            birinci_performans = cursor.getString(5);
            ikinci_performans = cursor.getString(6);
            ogrenci_yorum = cursor.getString(7);

        } else {
            Toast.makeText(DisplayStudent.this, "Ogrenci bulunamadı!", Toast.LENGTH_SHORT).show();
        }

        // Cursor ve veritabanı bağlantısını kapat
        if (cursor != null) {
            cursor.close();
        }
        db.close();

        // Verileri metin olarak döndürün
        return "Ogrenci adı: " + isim + "\nNumara: " + numara +
                "\nSinif: " + sinif + "\nBirinci Yazili: " + birinci_yazili +
                "\nIkinci Yazili: " + ikinci_yazili + "\nBirinci Performans: " +
                birinci_performans + "\nIkinci Performans: " + ikinci_performans +
                "\nOgrenci Yorum: " + ogrenci_yorum;
    }


}
