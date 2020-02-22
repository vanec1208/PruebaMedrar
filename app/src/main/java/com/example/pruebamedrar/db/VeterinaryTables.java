package com.example.pruebamedrar.db;

public class VeterinaryTables {

    public static abstract class OwnersTable {
        public static final String TABLE_NAME = "owners";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String PHONE = "phone";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NAME + " TEXT, " +
            PHONE + " TEXT " +
            ")";
    }

    public static abstract class PetsTable {
        public static final String TABLE_NAME = "pets";
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String TYPE = "type";
        public static final String AGE = "age";
        public static final String BREED = "breed";
        public static final String OWNER = "owner";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                TYPE + " TEXT, " +
                AGE + " INTEGER, " +
                BREED + " TEXT, " +
                OWNER + " INTEGER " +
                ")";
    }

    public static abstract class VaccinesTable {
        public static final String TABLE_NAME = "vaccines";
        public static final String NAME = "name";
        public static final String DATE = "date";
        public static final String DOSE = "dose";
        public static final String PET = "pet";

        public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                NAME + " TEXT, " +
                DATE + " TEXT, " +
                DOSE + " TEXT, " +
                PET + " INTEGER " +
                ")";
    }
}
