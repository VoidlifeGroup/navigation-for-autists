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

    int pozicia;
    private Dijkstra dijkstra;
    private int[] distances;
    private int distance;
    private ArrayList<Integer> results;
    private TextView distanceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_navigation);

        final ImageView sceneImage = (ImageView) findViewById(R.id.imageView);
        final Button forwardArrow = (Button) findViewById(R.id.buttonForward);
        distanceView = (TextView) findViewById(R.id.distance);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dijkstra = (Dijkstra) getIntent().getSerializableExtra("dijkstra");
        }
        //V tomto ife sa vypocita vzdialenost zo zaciatku do ciela - zobrazuje sa v textview
        if(dijkstra != null){
            distances = dijkstra.getTempDistance();
            results = dijkstra.getTempResults();
            distance = distances[results.get(results.size() - 1)];
            String message = "Vzdialenosť do ciela je: " + distance +
                    (distance <= 2 && distance > 0 ? (distance == 2 ? " metre." : " meter.") :
                            " metrov.");
            distanceView.setText(message);
        }
        pozicia = 0;
        forwardArrow.setVisibility(View.VISIBLE);
        update(sceneImage, traceList, pozicia);

        forwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia);
                // Vypne zobrazovanie sipky pokial sa dostaneme do ciela.
                if(pozicia == results.size() - 2){
                    forwardArrow.setVisibility(View.GONE);
                }
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
        // Tu sa pri kazdom pohybe do noveho vrcholu vzdialenost prepocitava
        if(dijkstra != null){
            distance = distances[results.get(results.size() - 1)] - distances[results.get(pozicia)];
            String message = "Vzdialenosť do ciela je: " + distance +
                    (distance <= 2 && distance > 0 ? (distance == 2 ? " metre." : " meter.") :
                            " metrov.");
            distanceView.setText(message);
        }
    }
}