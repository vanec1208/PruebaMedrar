package com.example.pruebamedrar.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.db.VeterinaryDb;
import com.example.pruebamedrar.db.VeterinaryTables;

import java.util.ArrayList;

public class OwnerTransactions {

    public static boolean hasOwner(Context context, int ownerId) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        Cursor c = db.query(VeterinaryTables.OwnersTable.TABLE_NAME,
                new String[] {VeterinaryTables.OwnersTable.ID},
                VeterinaryTables.OwnersTable.ID + " = " + ownerId,
                null, null, null,null);

        return c.moveToFirst();
    }

    public static void insertOwner(Context context, Owner owner) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        ContentValues values = new ContentValues();
        values.put(VeterinaryTables.OwnersTable.ID, owner.getId());
        values.put(VeterinaryTables.OwnersTable.NAME, owner.getName());
        values.put(VeterinaryTables.OwnersTable.PHONE, owner.getPhone());

        db.insert(VeterinaryTables.OwnersTable.TABLE_NAME,null, values);
    }

    public static ArrayList<Owner> getOwners(Context context, String like) {
        ArrayList<Owner> listOwners = new ArrayList<>();
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        Cursor cursor = db.rawQuery("SELECT * FROM " + VeterinaryTables.OwnersTable.TABLE_NAME +
                " WHERE " + VeterinaryTables.OwnersTable.ID + " LIKE '%" + like + "%' OR " +
                        VeterinaryTables.OwnersTable.NAME + " LIKE '%" + like + "%' ",
                null);

        while(cursor.moveToNext()) {
            Owner owner = new Owner(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            listOwners.add(owner);
        }

        return listOwners;
    }
}
