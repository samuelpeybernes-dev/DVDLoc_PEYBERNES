package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Reservation extends AppCompatActivity
{

    private Button btnOk;
    private Button btnNotOk;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        btnOk = findViewById(R.id.btnConfirm);
        btnOk.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view){
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        btnNotOk = findViewById(R.id.btnAnnule);
        btnNotOk.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view){
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });


        //Gestion du bouton btnRetour
        final Button btnRetourReservation = (Button) findViewById(R.id.btnRetourReservation);
        //Utilisation d'un listener pour récupérer l'interaction avec le bouton
        btnRetourReservation.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                //Lance l'activité qui affiche la fenêtre Fiction
                Intent intent = new Intent(Reservation.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}