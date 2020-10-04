package com.pigredorou.jeudedes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pigredorou.jeudedes.tresfute.TresFute;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTresFuteButton;
    private Button mEncoreButton;
    private Button mEncoreEtEncoreButton;
    private Button mDizzleButton;
    public static final int TRESFUTE_REQUEST_CODE=14;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_PRENOM = "PREF_KEY_PRENOM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("MainActivity::onCreate()");

        //Masque la barre de titre l'application
        getSupportActionBar().hide();

        // Passe l'aplli en plein écran et cache la barre de status.
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView greetingText = findViewById(R.id.activity_main_greeting_txt);
        mTresFuteButton = findViewById(R.id.activity_todo_button);
        mEncoreButton = findViewById(R.id.activity_main_encore_button);
        mEncoreEtEncoreButton = findViewById(R.id.activity_main_encore_et_encore_button);
        mDizzleButton = findViewById(R.id.activity_main_dizzle_button);

        mTresFuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Utilisateur clique sur le bouton
                Intent todoActivity = new Intent(MainActivity.this, TresFute.class);
                startActivityForResult(todoActivity, TRESFUTE_REQUEST_CODE);
            }
        });

        mEncoreButton.setOnClickListener(this);
        mEncoreEtEncoreButton.setOnClickListener(this);
        mDizzleButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "PROCHAINEMENT", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onStart() {
        super.onStart();

        out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        out.println("MainActivity::onDestroy()");
    }
}
