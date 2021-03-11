package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Policier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policier);

        //Venant de recherche
        String mot = this.getIntent().getStringExtra("titre");
        TextView titreDemande = findViewById(R.id.titreDemande);
        titreDemande.setText(mot);


        //Gestion du bouton btnRetour
        final Button btnRetour = (Button) findViewById(R.id.btnRetour);
        //Utilisation d'un listener pour récupérer l'interaction avec le bouton
        btnRetour.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                //Lance l'activité qui affiche la fenêtre Fiction
                Intent intent = new Intent(Policier.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}