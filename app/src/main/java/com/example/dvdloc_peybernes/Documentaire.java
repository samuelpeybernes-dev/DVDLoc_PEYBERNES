package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import data.locdvd.Serie;
import data.locdvd.SerieAdapter;

public class Documentaire extends AppCompatActivity {

    private ListView listDocumentaire;
    private SerieAdapter adapterDoc;

    //Lire le JSON
    public String lireLeJSON(){
        StringBuilder builder = new StringBuilder();
        AssetManager assetManager;
        InputStreamReader isr;
        BufferedReader data;

        try {
            assetManager = getAssets();
            isr = new InputStreamReader(assetManager.open("documentaire.json"));
            data = new BufferedReader(isr);
            String line;
            while ((line = data.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentaire);

        listDocumentaire = findViewById(R.id.list_documentaires);
        Log.i("LocDVD", "Vu Documentaire");
        adapterDoc = new SerieAdapter(this, R.layout.ligne);

        String strJSON = lireLeJSON();

        try {
            JSONArray jsonArray = new JSONArray(strJSON);
            //Création et alimentation d'un tableau JSON avec la chaine de caractére
            Log.i(Documentaire.class.getName(), "Nombres d'enregistrements :"
            + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(1);
                Serie docu = new Serie();

                docu.setCat(jsonObject.getString("cat"));
                docu.setTitre(jsonObject.getString("realisateur"));
                String path = getPackageName() + ":drawable/" + jsonObject.getString("img");
                int resID = getResources().getIdentifier(path, null, null);
                Log.i(Documentaire.class.getName(), "ID image" + resID);
                docu.setImg(resID);
                adapterDoc.add(docu); //on ajoute le nouveau film à adapterFilm
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        listDocumentaire.setAdapter(adapterDoc);




        //Gestion du bouton btnRetour
        final Button btnRetourDocumentaire = (Button) findViewById(R.id.btnRetourDocumentaire);
        //Utilisation d'un listener pour récupérer l'interaction avec le bouton
        btnRetourDocumentaire.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                //Lance l'activité qui affiche la fenêtre Fiction
                Intent intent = new Intent(Documentaire.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}