package com.ibm.mysampleapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
         * Do arraylistu sa načíta trasa v budove k vybranej miestnosti z inej aktivity.
         * Načítaná trasa sa skladá z objektov typu StepImage, ktorý obsahuje obrázok a smery pohybu
         * Na zobrazenie obrázka bude ako vstupný údaj názov obrázka ako String, ktorý sa pred
         * zobrazením prekonvertuje na imageID - integer.
         *
         * Vytvorí sa toľko objektov, koľko je "záchytných" bodov počas trasy.
         * (neskôr sa bude vytvárať v iterácii zo vstupných údajov inej aktivity)
         * Každá trasa bude obsahovať záchytné body a na jej aktivovanie sa použije button.
         */


        final ArrayList<StepImage> traceList = new ArrayList<>();
        traceList.add(new StepImage("obrazok1",
                true, true, true,false));
        traceList.add(new StepImage("obrazok2",
                true, false, true,true));

        /**
         * Nastaví sa pozícia na začiatok
         */

        pozicia = 0;

        /**
         * Zavolanie funkcii showFirstImage, ktorá zobrazí prvý obrázok a šípky podľa údajov v objekte
         */

        showFirstImage(sceneImage, traceList, pozicia, rightArrow, leftArrow);

        /**
         * OnClickListener na šípky (zatiaľ 2)
         */

        //TODO OSTATNÉ SMERY

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
         * Nastavenie obrázku - vygenerujeme si ID daného obrázka a potom nastavíme obrázok podľa ID
         */

        int imageID = convertImage(sceneImage.getContext(), traceList.get(pozicia).getSceneImage());

        sceneImage.setImageResource(imageID);

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


    /**
     * Funkcia, ktorá transferne názov obrázka do integeru(jeho ID)
     * @param imageContext - context
     * @param imageIdentifier - názov obrázka vo forme stringu
     * @return
     */
    protected int convertImage(Context imageContext, String imageIdentifier){
        int imageId = imageContext.getResources().getIdentifier(imageIdentifier, "drawable",
                imageContext.getPackageName());
        return imageId;
    }

    /**
     * Funkcia, ktorá zavolá funkciu update po 4 sekundách - vykreslí prvý obrázok
     * @param sceneImage - obrázok
     * @param traceList - trasa
     * @param pozicia - pozicia
     * @param rightArrow - arrowButton
     * @param leftArrow - arrowButton
     */


    protected void showFirstImage(final ImageView sceneImage, final ArrayList<StepImage> traceList,
                                  final int pozicia, final Button rightArrow,
                                  final Button leftArrow){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                update(sceneImage, traceList, pozicia, rightArrow, leftArrow);
            }
        }, 4000);
    }

}
