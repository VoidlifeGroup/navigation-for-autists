package com.ibm.mysampleapp.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.ibm.mysampleapp.R;

import java.util.ArrayList;

/**
 * Trieda sluzi na postupne zobrazovanie obrazkov, ktore su ulozene v TraceListe
 * @author Martin Marič
 * @author Radoslav Sojak
 */
public class Navigation extends AppCompatActivity implements TraceList {

    int pozicia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_navigation);

        final ImageView sceneImage = (ImageView) findViewById(R.id.imageView);
        final Button forwardArrow = (Button) findViewById(R.id.buttonForward);

        pozicia = 0;
        forwardArrow.setVisibility(View.VISIBLE);
        update(sceneImage, traceList, pozicia);

        forwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia);
            }
        });
    }

    /**
     * Metoda, ktorá ziska zo stringu ciselne id obrazka
     * a nasledne ho zobrazi na obrazovke
     */
    private void update(ImageView sceneImage, ArrayList<StepImage> traceList, int pozicia) {

        int imageID = sceneImage.getContext().getResources().getIdentifier(
                traceList.get(pozicia).getSceneImage(),
                "drawable",
                sceneImage.getContext().getPackageName()
        );
        sceneImage.setImageResource(imageID);
    }

}
