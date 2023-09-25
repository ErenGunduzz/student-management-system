package com.example.student_management_system;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayClass extends AppCompatActivity {

    private EditText className;
    private Button searchClass;
    private TextView resultClass;
    private DatabaseHelper database;
    private LinearLayout classLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_class);

        database = new DatabaseHelper(this);
        classLayout = findViewById(R.id.class_layout);
        className = findViewById(R.id.class_name_editText);

        searchClass = findViewById(R.id.class_search_button);

        searchClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database'den sinifi cekme
                String classNameValue = className.getText().toString();
                getClassData(classNameValue);
            }
        });
    }
    //isme gore ogrenci verilerini ceken fonksiyon
    private void getClassData(String className) {
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

        String selection = DatabaseHelper.COLUMN_CLASS + " = ?";
        String[] selectionArgs = {className};

        // Sorguyu çalıştırın ve sonucu bir Cursor nesnesine alın
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                columnsToReturn,
                selection,
                selectionArgs,
                null,
                null,
                DatabaseHelper.COLUMN_CLASS
        );

        System.out.println(cursor == null);
        System.out.println(cursor.moveToFirst() ==  true);

        // Eğer sorgu sonucu veri bulunursa, verileri çekin
        if (cursor != null && cursor.moveToFirst()) {
            do {
                numara = cursor.getString(0);
                isim = cursor.getString(1);
                sinif = cursor.getString(2);
                birinci_yazili = cursor.getString(3);
                ikinci_yazili = cursor.getString(4);
                birinci_performans = cursor.getString(5);
                ikinci_performans = cursor.getString(6);
                ogrenci_yorum = cursor.getString(7);

                String studentData = "Ogrenci adı: " + isim + "\nNumara: " + numara +
                        "\nSinif: " + sinif + "\nBirinci Yazili: " + birinci_yazili +
                        "\nIkinci Yazili: " + ikinci_yazili + "\nBirinci Performans: " +
                        birinci_performans + "\nIkinci Performans: " + ikinci_performans +
                        "\nOgrenci Yorum: " + ogrenci_yorum + "\n";

                TextView studentTextView = new TextView(this);
                studentTextView.setText(studentData);
                classLayout.addView(studentTextView);
            } while (cursor.moveToNext());

        }

        // Cursor ve veritabanı bağlantısını kapat
        if (cursor != null) {
            cursor.close();
        }
        db.close();
    }
}
