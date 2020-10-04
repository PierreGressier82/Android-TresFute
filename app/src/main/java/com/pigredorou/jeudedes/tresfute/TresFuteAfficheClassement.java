package com.pigredorou.jeudedes.tresfute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.jeudedes.R;

public class TresFuteAfficheClassement extends AppCompatActivity {
    // The database
    private SQLiteDatabase bdd;
    // The database creator and updater helper
    TresFuteDBOpenHelper classementDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String texteDuClassement="";

        setContentView(R.layout.activity_tres_fute_affiche_classement);
        TextView textClassement = findViewById(R.id.classement_complet);

        classementDB = new TresFuteDBOpenHelper(this);
        openDB();

        Cursor cursor=getClassementTrieParSCore();

        if (cursor.moveToFirst()) {
            // The elements to retrieve
            int colId;
            int score;
            String nom;
            String date;
            int nbJoueurs;
            int count = 0;
            do {
                colId = cursor.getInt(0);
                score = cursor.getInt(1);
                nom = cursor.getString(2);
                date = cursor.getString(3);
                nbJoueurs = cursor.getInt(4);
                texteDuClassement += count+1 + " - " + score + " " + nom + " " + date + " " + nbJoueurs + "\n";
                count++;
            } while (cursor.moveToNext());
        }
            else {
            texteDuClassement = "Pas de score enregistré";
            Toast.makeText(this, "Pas de score", Toast.LENGTH_LONG).show();
        }

        closeDB();
        textClassement.setText(texteDuClassement);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openDB();
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDB();
    }

    public void openDB() throws SQLiteException {
        try {
            bdd = classementDB.getWritableDatabase();
        } catch (SQLiteException ex) {
            bdd = classementDB.getReadableDatabase();
        }
    }

    public void closeDB() {
        bdd.close();
    }

    public Cursor getClassementTrieParSCore(){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TresFuteDBOpenHelper.Constants.TABLE_CLASSEMENT, new String[] {TresFuteDBOpenHelper.Constants.COL_ID,
                TresFuteDBOpenHelper.Constants.COL_SCORE, TresFuteDBOpenHelper.Constants.COL_NOM, TresFuteDBOpenHelper.Constants.COL_DATE,
                TresFuteDBOpenHelper.Constants.COL_NBJ}, null, null, null, null, TresFuteDBOpenHelper.Constants.COL_SCORE + " DESC", "10");
        return c;
    }

}
