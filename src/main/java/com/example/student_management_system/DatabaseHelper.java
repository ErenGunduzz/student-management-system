package com.example.student_management_system;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 6;

    public static final String TABLE_NAME = "Students";
    public static final String COLUMN_NUMBER = "numara";
    public static final String COLUMN_NAME = "isim";
    public static final String COLUMN_CLASS = "sinif";
    public static final String COLUMN_FIRST_EXAM = "birinci_yazili";
    public static final String COLUMN_SECOND_EXAM = "ikinci_yazili";
    public static final String COLUMN_PERF_BIR = "birinci_performans";
    public static final String COLUMN_PERF_IKI = "ikinci_performans";
    public static final String COLUMN_COMMENT = "ogrenci_yorum";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NUMBER + " TEXT PRIMARY KEY , " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_CLASS + " TEXT, " +
                    COLUMN_FIRST_EXAM + " INTEGER, " +
                    COLUMN_SECOND_EXAM + " INTEGER, " +
                    COLUMN_PERF_BIR + " INTEGER, " +
                    COLUMN_PERF_IKI + " INTEGER, " +
                    COLUMN_COMMENT + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void yeniOgrenciEkle(String numara, String isim, String sinif) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, numara);
        values.put(COLUMN_NAME, isim);
        values.put(COLUMN_CLASS, sinif);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void ogrenciSil(String numara) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Kaydı silmeden önce numaranın veritabanında bulunduğunu kontrol et
        if (ogrenciVarMi(db, numara)) {
            db.delete(TABLE_NAME, COLUMN_NUMBER + " = ?", new String[]{numara});
        } else {
            System.out.println("Zaten var!");
        }

        db.close();
    }

    private boolean ogrenciVarMi(SQLiteDatabase db, String numara) {
        // Veritabanında belirtilen numarayı içeren bir kayıt olup olmadığını kontrol et
        String[] columns = {COLUMN_NUMBER};
        String selection = COLUMN_NUMBER + " = ?";
        String[] selectionArgs = {numara};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        boolean varMi = cursor.moveToFirst();

        cursor.close();

        return varMi;
    }

    public void notEkle(String numara, String ilkYazili, String ikinciYazili, String ilkPerformans, String ikinciPerformans, String ogrYorum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NUMBER, numara);
        values.put(COLUMN_FIRST_EXAM, ilkYazili);
        values.put(COLUMN_SECOND_EXAM, ikinciYazili);
        values.put(COLUMN_PERF_BIR, ilkPerformans);
        values.put(COLUMN_PERF_IKI, ikinciPerformans);
        values.put(COLUMN_COMMENT, ogrYorum);

        db.update(TABLE_NAME, values, COLUMN_NUMBER + " = ?",
                new String[]{String.valueOf(numara)});
        db.close();

    }
}

