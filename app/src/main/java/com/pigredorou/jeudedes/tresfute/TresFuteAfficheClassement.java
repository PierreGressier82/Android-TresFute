package com.pigredorou.jeudedes.tresfute;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import static com.pigredorou.jeudedes.R.color;
import static com.pigredorou.jeudedes.R.drawable;
import static com.pigredorou.jeudedes.R.id;
import static com.pigredorou.jeudedes.R.layout;

public class TresFuteAfficheClassement extends AppCompatActivity {
    // The database
    private TresFuteClassementBDD classementDB = new TresFuteClassementBDD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Masque le bar de titre de l'activité
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(layout.activity_tres_fute_affiche_classement);
        ImageView boutonQuitter = findViewById(id.bouton_quitter);

        boutonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Tout d'abord, il faut détruire tous les éléments contenus dans le layout.
        TableLayout layoutTableauScores = findViewById(id.layout_tableau_scores);
        layoutTableauScores.removeAllViewsInLayout();

        // Entête du tableau
        //ajouteLigneTableau(layoutTableauScores, "Pos", "Score", "Nom", "Date");

        openDB();

        Cursor cursor=classementDB.getClassementTrieParSCore(20);

        if (cursor.moveToFirst()) {
            // The elements to retrieve
            int score;
            String nom;
            String date;
            int count = 0;
            do {
                score = cursor.getInt(1);
                nom = cursor.getString(2);
                date = cursor.getString(3);
                count++;
                // Affichage de la ligne
                ajouteLigneTableau(layoutTableauScores, String.valueOf(count),String.valueOf(score),nom,date);
            } while (cursor.moveToNext());
        }
            else {
            Toast.makeText(this, "Pas de score", Toast.LENGTH_LONG).show();
        }

        closeDB();
    }

    private void ajouteLigneTableau(TableLayout layoutTableauScores, String position, String score, String nom, String date) {
        TableRow ligneTableau = new TableRow(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, (float) 0.2);
        TableRow.LayoutParams paramsDate = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, (float) 0.4);
        params.setMargins(2,2,2,2);
        //ligneTableau.setPadding(3,3,3,3);
        //ligneTableau.setGravity(Gravity.CENTER);
        ligneTableau.setLayoutParams(params);

        // Ajout de la ligne dans le tableau
        layoutTableauScores.addView(ligneTableau);

        //android:layout_width="0dp"
        //android:layout_height="wrap_content"
        //android:layout_weight="0.25"


        // Elements de la ligne
        TextView elementLigne1 = new TextView(this);
        TextView elementLigne2 = new TextView(this);
        TextView elementLigne3 = new TextView(this);
        TextView elementLigne4 = new TextView(this);
        elementLigne1.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne1.setTextColor(getResources().getColor(color.blanc));
        elementLigne1.setTextSize(15);
        //elementLigne1.setPadding(2,2,2,2);
        elementLigne1.setLayoutParams(params);
        elementLigne1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //elementLigne1.setGravity(View.TEXT_ALIGNMENT_CENTER);
        elementLigne2.setTextColor(getResources().getColor(color.blanc));
        elementLigne2.setTextSize(15);
        //elementLigne2.setPadding(2,2,2,2);
        elementLigne2.setLayoutParams(params);
        elementLigne2.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //elementLigne2.setGravity(View.TEXT_ALIGNMENT_CENTER);
        elementLigne3.setTextColor(getResources().getColor(color.blanc));
        elementLigne3.setTextSize(15);
        //elementLigne3.setPadding(2,2,2,2);
        elementLigne3.setLayoutParams(params);
        elementLigne3.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //elementLigne3.setGravity(View.TEXT_ALIGNMENT_CENTER);
        elementLigne4.setTextColor(getResources().getColor(color.blanc));
        elementLigne4.setTextSize(15);
        //elementLigne4.setPadding(2,2,2,2);
        elementLigne4.setLayoutParams(paramsDate);
        elementLigne4.setBackground(getResources().getDrawable(drawable.bordure_tableau));
        elementLigne4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //elementLigne4.setGravity(View.TEXT_ALIGNMENT_CENTER);

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

    public void openDB() {
      classementDB.open();
    }

    public void closeDB() {
        classementDB.close();
    }
}
