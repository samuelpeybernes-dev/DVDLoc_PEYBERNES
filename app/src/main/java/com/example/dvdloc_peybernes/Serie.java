package com.example.dvdloc_peybernes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class Serie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        //Tableau de séries
        ArrayList<Serie> lesSeries = new ArrayList<Serie>();

        //ListView listeSeries = (ListView) findViewById(R.id.serie_listview);
        //SerieAdapter adapterSerie = new SerieAdapter(this, R.layout.ligne);

        try{
            XmlPullParser xmlPullParser=getResources().getXml(R.xml.liste_serie);
            //Tant que la fin de fichier non atteinte
            while (xmlPullParser.getEventType()!=XmlPullParser.END_DOCUMENT){
                //Si balise ouvrante
                if (xmlPullParser.getEventType()==xmlPullParser.START_TAG){
                    //Si la balise est DVD
                    if (xmlPullParser.getName().equals("dvd")){
                        Serie uneSerie = new Serie();
                        uneSerie.setCat(xmlPullParser.getAttributeValue(0));
                        Log.i("LocDVD", "Cat = " + uneSerie.getCat());
                        uneSerie.setTitre(xmlPullParser.getAttributeValue(1));
                        Log.i("LocDVD", "Titre = " + uneSerie.getTitre());
                        uneSerie.setRealisateur(xmlPullParser.getAttributeValue(2));
                        Log.i("LocDVD", "Réalisateur = " + uneSerie.getRealisateur());

                        //Récupération du nom de l'image dans drawable
                        String path = getPackageName() + ":drawable/"
                                         + xmlPullParser.getAttributeValue(3);
                        int resID = getResources().getIdentifier(path, null, null);
                        uneSerie.setImg(resID);
                        Log.i("LocDVD", "Img = " + uneSerie.getImg());

                        //Ajout de l'objet
                        lesSeries.add(uneSerie);
                        //Alimentation de l'adapter
                        //adapterSerie.add(uneSerie);
                    }
                }
                //Enregistrement suivant
                xmlPullParser.next();
            }
        }
        catch (Exception e){
            Log.i("LocDVD", "Erreurs trouvées = " + e.getMessage());
            e.printStackTrace();
        }
        listeSeries.setAdapter(adapterSerie);





        //Gestion du bouton btnRetour
        final Button btnRetourSerie = (Button) findViewById(R.id.btnRetourSerie);
        //Utilisation d'un listener pour récupérer l'interaction avec le bouton
        btnRetourSerie.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0) {
                //Lance l'activité qui affiche la fenêtre Fiction
                Intent intent = new Intent(Serie.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}