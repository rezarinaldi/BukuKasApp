package com.rezarinaldi.bukukasapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static final String DATABASE_NAME = "buku_kas.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_FINANCE = "finance";
    private static final String COLUMN_ID_FINANCE = "id_finance";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_NOMINAL = "nominal";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_CATEGORY = "category";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_FINANCE +
                " (" + COLUMN_ID_FINANCE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_NOMINAL + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_CATEGORY + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINANCE);
        onCreate(db);
    }

    public void addFinance(String date, String nominal, String description, String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_NOMINAL, nominal);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_CATEGORY, category);

        long result = db.insert(TABLE_FINANCE, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Data gagal ditambahkan!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Data berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_FINANCE
                + " ORDER BY " + COLUMN_ID_FINANCE
                + " DESC ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    public String getFinancialSummary(String category) {
        String result = "0";
        String query = "SELECT SUM(nominal) AS total FROM finance where category = '" + category + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            if (cursor.moveToFirst()) {
                if (cursor.isNull(0)) {
                    result = "0";
                } else {
                    result = cursor.getString(0);
                }
            }
        }

        return result;
    }
}
