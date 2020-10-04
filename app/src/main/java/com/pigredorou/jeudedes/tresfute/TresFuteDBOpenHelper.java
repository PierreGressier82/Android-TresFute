package com.pigredorou.jeudedes.tresfute;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class TresFuteDBOpenHelper extends SQLiteOpenHelper {
    public static class Constants implements BaseColumns {

        public static final String DATABASE_NAME = "tresFute.db";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_CLASSEMENT = "classement";

        // Noms de colonnes
        public static final String COL_ID = "_id";
        public static final String COL_SCORE = "score";
        public static final String COL_NOM = "nom";
        public static final String COL_DATE = "date";
        public static final String COL_NBJ = "nbJoueurs";

        // Index des colonnes
        public static final int NUM_COL_ID = 0;
        public static final int NUM_COL_SCORE = 1;
        public static final int NUM_COL_NOM = 2;
        public static final int NUM_COL_DATE = 3;
        public static final int NUM_COL_NBJ = 4;
    }

    private static final String CREATION_TABLE_SCORE = "create table " + Constants.TABLE_CLASSEMENT + " ("
            + Constants.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Constants.COL_SCORE + " INTEGER NOT NULL, "
            + Constants.COL_NOM + " TEXT NOT NULL, "
            + Constants.COL_DATE + " DATE not null, "
            + Constants.COL_NBJ + " INTERGER DEFAULT 1)";

    TresFuteDBOpenHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATION_TABLE_SCORE);
        db.execSQL("INSERT INTO " + Constants.TABLE_CLASSEMENT + " (score,nom,date,nbJoueurs) VALUES (1,'TEST','02/10/2020',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_CLASSEMENT);
        onCreate(db);
    }
}
