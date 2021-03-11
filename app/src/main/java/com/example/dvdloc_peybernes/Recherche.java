package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Recherche extends AppCompatActivity {

        // Def de 2 attribus prives
        private Button btnCherche;
        private EditText edtCherche;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recherche);
            //Association des attributs privés à la vue
            edtCherche = findViewById(R.id.edtRecherche);
            btnCherche = findViewById(R.id.btnRecherche);
            // Création du listener
            btnCherche.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Toast toast = Toast.makeText(getApplicationContext(), edtCherche.getText(), Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent (Recherche.this, Policier.class);
                    intent.putExtra("titre", edtCherche.getText().toString());
                    startActivity(intent);
                }
            });



            //Gestion du bouton btnRetour
            final Button btnRetour = (Button) findViewById(R.id.btnRetour);
            //Utilisation d'un listener pour récupérer l'interaction avec le bouton
            btnRetour.setOnClickListener(new Button.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    //Lance l'activité qui affiche la fenêtre Fiction
                    Intent intent = new Intent(Recherche.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

}