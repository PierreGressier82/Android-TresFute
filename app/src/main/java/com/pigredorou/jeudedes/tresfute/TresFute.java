package com.pigredorou.jeudedes.tresfute;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pigredorou.jeudedes.R;

import java.util.Objects;

public class TresFute extends AppCompatActivity {

    public static final int TRES_FUTE_SCORE_ACTIVITY_REQUEST_CODE=21;
    public static final int TRES_FUTE_SOLO_ACTIVITY_REQUEST_CODE=22;
    public static final int TRES_FUTE_CLASSEMENT_ACTIVITY_REQUEST_CODE=23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Masque le bar de titre de l'activité
        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_tres_fute_main);

        Button mButton_feuilleScore = findViewById(R.id.feuille_score);
        Button mButton_jeuSolo = findViewById(R.id.jeu_solo);
        Button mButton_classement = findViewById(R.id.classement);
        Button mButton_quitter = findViewById(R.id.bouton_quitter);

        mButton_feuilleScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent activity = new Intent(TresFute.this, TresFuteFeuilleScore.class);
                startActivityForResult(activity, TRES_FUTE_SCORE_ACTIVITY_REQUEST_CODE);
            }
        });

        mButton_jeuSolo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent activity = new Intent(TresFute.this, TresFuteSolo.class);
                startActivityForResult(activity, TRES_FUTE_SOLO_ACTIVITY_REQUEST_CODE);
            }
        });

        mButton_classement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent activity = new Intent(TresFute.this, TresFuteAfficheClassement.class);
                startActivityForResult(activity, TRES_FUTE_CLASSEMENT_ACTIVITY_REQUEST_CODE);
            }
        });

        mButton_quitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
