package com.pigredorou.jeudedes.tresfute;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.pigredorou.jeudedes.R.*;

public class TresFuteAfficheClassement extends AppCompatActivity {
    // The database
    private SQLiteDatabase bdd;
    // The database creator and updater helper
    TresFuteDBOpenHelper classementDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Masque le bar de titre de l'activité
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(layout.activity_tres_fute_affiche_classement);
        TextView textClassement = findViewById(id.classement_complet);
        // Tout d'abord, il faut détruire tous les éléments contenus dans le layout.
        TableLayout layoutTableauScores = (TableLayout) findViewById(id.layout_tableau_scores);
        layoutTableauScores.removeAllViewsInLayout();

        // Entête du tableau
        ajouteLigneTableau(layoutTableauScores, "Pos", "Score", "Nom", "Date");

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
                //texteDuClassement += count+1 + " - " + score + " " + nom + " " + date + " " + nbJoueurs + "\n";
                count++;
                // Affichage de la ligne
                ajouteLigneTableau(layoutTableauScores, String.valueOf(count),String.valueOf(score),nom,date);
            } while (cursor.moveToNext());
        }
            else {
            //texteDuClassement = "Pas de score enregistré";
            Toast.makeText(this, "Pas de score", Toast.LENGTH_LONG).show();
        }

        closeDB();
    }

    private void ajouteLigneTableau(TableLayout layoutTableauScores, String position, String score, String nom, String date) {
        TableRow ligneTableau = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT);
        params.setMargins(2,2,2,2);
        ligneTableau.setPadding(3,3,3,3);
        ligneTableau.setGravity(Gravity.CENTER);
        ligneTableau.setLayoutParams(params);

        // Ajout de la ligne dans le tableau
        layoutTableauScores.addView(ligneTableau);

        // Elements de la ligne
        TextView elementLigne1 = new TextView(this);
        TextView elementLigne2 = new TextView(this);
        TextView elementLigne3 = new TextView(this);
        TextView elementLigne4 = new TextView(this);
        elementLigne1.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne1.setTextColor(getResources().getColor(color.blanc));
        elementLigne1.setTextSize(15);
        elementLigne1.setPadding(2,2,2,2);
        elementLigne1.setLayoutParams(params);
        elementLigne1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        elementLigne2.setTextColor(getResources().getColor(color.blanc));
        elementLigne2.setTextSize(15);
        elementLigne2.setPadding(2,2,2,2);
        elementLigne2.setLayoutParams(params);
        elementLigne2.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne2.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        elementLigne3.setTextColor(getResources().getColor(color.blanc));
        elementLigne3.setTextSize(15);
        elementLigne3.setPadding(2,2,2,2);
        elementLigne3.setLayoutParams(params);
        elementLigne3.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne3.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        elementLigne4.setTextColor(getResources().getColor(color.blanc));
        elementLigne4.setTextSize(15);
        elementLigne4.setPadding(2,2,2,2);
        elementLigne4.setLayoutParams(params);
        elementLigne4.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        // Ajout de la case 1 de la ligne
        elementLigne1.setText(position);
        ligneTableau.addView(elementLigne1, 0);
        // Ajout de la case 2 de la ligne
        elementLigne2.setText(score);
        ligneTableau.addView(elementLigne2, 1);
        // Ajout de la case 3 de la ligne
        elementLigne3.setText(nom);
        ligneTableau.addView(elementLigne3, 2);
        // Ajout de la case 4 de la ligne
        elementLigne4.setText(date);
        ligneTableau.addView(elementLigne4, 3);

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
