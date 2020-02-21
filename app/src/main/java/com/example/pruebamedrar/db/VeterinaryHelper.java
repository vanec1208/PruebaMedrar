package com.example.pruebamedrar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VeterinaryHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "veterinary.db";
    public static final int DB_VERSION = 1;

    public VeterinaryHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VeterinaryTables.OwnersTable.CREATE);
        sqLiteDatabase.execSQL(VeterinaryTables.PetsTable.CREATE);
        sqLiteDatabase.execSQL(VeterinaryTables.VaccinesTable.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
