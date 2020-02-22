package com.example.pruebamedrar.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebamedrar.beans.Vaccine;
import com.example.pruebamedrar.db.VeterinaryDb;
import com.example.pruebamedrar.db.VeterinaryTables;

public class VaccineTransactions {

    public static void insertVaccine(Context context, Vaccine vaccine) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        ContentValues values = new ContentValues();
        values.put(VeterinaryTables.VaccinesTable.NAME, vaccine.getName());
        values.put(VeterinaryTables.VaccinesTable.DATE, vaccine.getDate());
        values.put(VeterinaryTables.VaccinesTable.DOSE, vaccine.getDose());
        values.put(VeterinaryTables.VaccinesTable.PET, vaccine.getPetId());

        db.insert(VeterinaryTables.VaccinesTable.TABLE_NAME,null, values);
    }
}
