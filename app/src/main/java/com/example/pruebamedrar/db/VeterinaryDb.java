package com.example.pruebamedrar.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class VeterinaryDb {
    private static SQLiteDatabase sqLiteDatabase = null;

    private synchronized static void createInstance(Context context) {
        if(sqLiteDatabase == null) {
            sqLiteDatabase = new VeterinaryHelper(context).getReadableDatabase();
        }
    }

    public static SQLiteDatabase getInstance(Context context) {
        if(sqLiteDatabase == null) createInstance(context);
        return sqLiteDatabase;
    }
}
