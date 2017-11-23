package com.ibm.mysampleapp.core;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.graph.algorithms.Dijkstra;

import java.util.ArrayList;

/**
 * Trieda sluzi na postupne zobrazovanie obrazkov, ktore su ulozene v TraceListe
 * @author Martin Marič
 * @author Radoslav Sojak
 */
public class Navigation extends AppCompatActivity implements TraceList {

    private int pozicia = 0;
    private int distance = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_navigation);
        TextView distanceView;
        final ImageView sceneImage = (ImageView) findViewById(R.id.imageView);
        final Button forwardArrow = (Button) findViewById(R.id.buttonForward);
        distanceView = (TextView) findViewById(R.id.distance);

        forwardArrow.setVisibility(View.VISIBLE);
        update(sceneImage, traceList, pozicia, distanceView);

        forwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia, distanceView);
                // Vypne zobrazovanie sipky pokial sa dostaneme do ciela.
                if(pozicia == traceList.size() - 1){
                    forwardArrow.setVisibility(View.GONE);
                }
            }
        });
    }

    /**
     * Metoda, ktorá ziska zo stringu ciselne id obrazka
     * a nasledne ho zobrazi na obrazovke
     */
    private void update(ImageView sceneImage, ArrayList<StepImage> traceList,
                        int pozicia, TextView distanceView) {

        int imageID = sceneImage.getContext().getResources().getIdentifier(
                traceList.get(pozicia).getSceneImage(),
                "drawable",
                sceneImage.getContext().getPackageName()
        );
        sceneImage.setImageResource(imageID);

        // Tu sa pri kazdom pohybe do noveho vrcholu vzdialenost prepocitava
        distanceView.setText(distanceMessage());

    }

    /**
     * Ak je na zaciatku scita cely zoznam vzdialenosti
     * ak je niekde na ceste odcita poslednu prejdenu vzdialenost
     * @return sprava na zobrazenie
     */
    private String distanceMessage(){
        if (pozicia == 0) {
            for (StepImage stepImage : traceList) {
                distance += stepImage.getDistance();
            }
        }
        else{
            distance -= traceList.get(pozicia-1).getDistance();
        }

        String message = "Vzdialenosť do ciela je: " + distance +
                (distance <= 4 && distance > 0 ? (distance >= 2 ? " metre." : " meter.") :
                        " metrov.");

        return message;
    }

}