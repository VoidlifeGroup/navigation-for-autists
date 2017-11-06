package com.ibm.mysampleapp;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by mato on 1. 11. 2017.
 */

public class BuildingNavigation extends AppCompatActivity {

    int pozicia;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.building_navigation);

        //TODO TRASY DO MIESTNOSTÍ(BUTTONY)

        final ImageView sceneImage = (ImageView) findViewById(R.id.imageView);
        final Button rightArrow = (Button) findViewById(R.id.buttonRight);
        final Button leftArrow = (Button) findViewById(R.id.buttonLeft);

        /**
         * -- PRÍKLAD JEDNEJ CESTY DO MIESTNOSTI --
         * Zobrazenie trasy pomocou arraylistu traceList
         * Do arraylistu sa načíta trasa v budove k vybranej miestnosti cez button.
         * Načítaná trasa sa skladá z objektov typu StepImage, ktorý obsahuje obrázok a smery pohybu
         * Drawable object je potrebné previesť do bitovej mapy a potom uložiť do objektu,
         * preto nie je použitý len názov obrázku ako String.
         *
         * Vytvorí sa toľko objektov, koľko je "záchytných" bodov počas trasy.
         * (neskôr sa bude vytvárať v iterácii zo vstupných údajov inej aktivity)
         */


        final ArrayList<StepImage> traceList = new ArrayList<>();
        traceList.add(new StepImage(BitmapFactory.decodeResource(getResources(), R.drawable.obrazok1),
                true, true, true,false));
        traceList.add(new StepImage(BitmapFactory.decodeResource(getResources(), R.drawable.obrazok2),
                true, false, true,true));

        /**
         * Nastaví sa pozícia na začiatok
         */

        pozicia = 0;

        /**
         * Zavolanie funkcii update, ktorá zobrazí prvý obrázok a šípky podľa údajov v objekte
         */

        update(sceneImage, traceList, pozicia, rightArrow, leftArrow);

        /**
         * OnClickListener na šípky (zatiaľ 2)
         */

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //TODO PREPNUTIE NA NEXT OBJ
                pozicia++;
                update(sceneImage, traceList, pozicia, rightArrow, leftArrow);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia, rightArrow, leftArrow);
            }
        });

    }


    protected void update(ImageView sceneImage, ArrayList<StepImage> traceList, int pozicia,
                         Button rightArrow, Button leftArrow){
        /**
         * Nastavenie obrázku
         */
        sceneImage.setImageBitmap(traceList.get(pozicia).getSceneImage());

        /**
         * Keď sa načíta obrázok tak sa nastaví viditeľnosť
         * buttonov podľa uloženého stavu smerov(zatiaľ vpravo-vlavo)
         */

        if (!traceList.get(pozicia).getRight()){
            rightArrow.setVisibility(View.GONE);
        }

        if (!traceList.get(pozicia).getLeft()){
            leftArrow.setVisibility(View.GONE);
        }

    }

}
