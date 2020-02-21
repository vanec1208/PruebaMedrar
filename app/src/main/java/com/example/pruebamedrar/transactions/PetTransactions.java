package com.example.pruebamedrar.transactions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pruebamedrar.beans.Owner;
import com.example.pruebamedrar.beans.Pet;
import com.example.pruebamedrar.db.VeterinaryDb;
import com.example.pruebamedrar.db.VeterinaryTables;

import java.util.ArrayList;

public class PetTransactions {

    public static boolean hasPet(Context context, int petId) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        Cursor c = db.query(VeterinaryTables.PetsTable.TABLE_NAME,
                new String[] {VeterinaryTables.PetsTable.ID},
                VeterinaryTables.PetsTable.ID + " = " + petId,
                null, null, null,null);

        return c.moveToFirst();
    }

    public static void insertPet(Context context, Pet pet) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        ContentValues values = new ContentValues();
        values.put(VeterinaryTables.PetsTable.ID, pet.getId());
        values.put(VeterinaryTables.PetsTable.NAME, pet.getName());
        values.put(VeterinaryTables.PetsTable.TYPE, pet.getType());
        values.put(VeterinaryTables.PetsTable.AGE, pet.getAge());
        values.put(VeterinaryTables.PetsTable.BREED, pet.getBreed());
        values.put(VeterinaryTables.PetsTable.OWNER, pet.getOwner().getId());

        db.insert(VeterinaryTables.PetsTable.TABLE_NAME,null, values);
    }

    public static ArrayList<Pet> getPets(Context context, String like) {
        ArrayList<Pet> listPets = new ArrayList<>();
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        String selection = "P." + VeterinaryTables.PetsTable.ID + ", " +
                "P." + VeterinaryTables.PetsTable.NAME + ", " +
                "P." + VeterinaryTables.PetsTable.TYPE + ", " +
                "P." + VeterinaryTables.PetsTable.AGE + ", " +
                "P." + VeterinaryTables.PetsTable.BREED + ", " +
                "P." + VeterinaryTables.PetsTable.OWNER + ", " +
                "O." + VeterinaryTables.OwnersTable.NAME + ", " +
                "O." + VeterinaryTables.OwnersTable.PHONE;

        Cursor cursor = db.rawQuery("SELECT " + selection + " FROM " + VeterinaryTables.PetsTable.TABLE_NAME +
                    " P LEFT JOIN " + VeterinaryTables.OwnersTable.TABLE_NAME +
                    " O ON P." + VeterinaryTables.PetsTable.OWNER + " = O." + VeterinaryTables.OwnersTable.ID +
                    " WHERE P." + VeterinaryTables.PetsTable.ID + " LIKE '%" + like + "%' OR P." +
                    VeterinaryTables.PetsTable.NAME + " LIKE '%" + like + "%' ",
            null);

        while(cursor.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setType(cursor.getString(2));
            pet.setAge(cursor.getInt(3));
            pet.setBreed(cursor.getString(4));

            Owner owner = new Owner(cursor.getInt(5), cursor.getString(6), cursor.getString(7));
            pet.setOwner(owner);
            listPets.add(pet);
        }

        return listPets;
    }

    public static void deletePet(Context context, int petId) {
        SQLiteDatabase db = VeterinaryDb.getInstance(context);

        db.delete(VeterinaryTables.PetsTable.TABLE_NAME,
                VeterinaryTables.PetsTable.ID + " = " + petId,
                null);
    }
}
