package com.ibm.mysampleapp.core;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.graph.algorithms.Dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/**
 * Trieda sluzi na postupne zobrazovanie obrazkov, ktore su ulozene v TraceListe
 * @author Martin Marič
 * @author Radoslav Sojak
 */
public class Navigation extends AppCompatActivity implements TraceList {

    private int pozicia = 0;
    private int distance = 0;
    private TextToSpeech tts_engine;
    private String language;
    private String intro_sk = "Vitajte v aplikácii pre autistov. Vaša úroveň autizmu je " +
            "dostačujúca, aby ste mohli používať túto aplikáciu!";
    private String intro_en = "Welcome to text to speech mode! " +
            "Your level of autism is high enough, " +
            "that you are authorized to use this app!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_navigation);
        TextView distanceView;
        final ImageView sceneImage = (ImageView) findViewById(R.id.imageView);
        final Button forwardArrow = (Button) findViewById(R.id.buttonForward);
        final Button leftArrow = (Button) findViewById(R.id.buttonLeft);
        final Button rightArrow = (Button) findViewById(R.id.buttonRight);
        distanceView = (TextView) findViewById(R.id.distance);

        forwardArrow.setVisibility(View.VISIBLE);

        setLanguage("Slovak");
        textToSpeechInit(language);

        update(sceneImage, traceList, pozicia, distanceView, rightArrow, leftArrow, forwardArrow);

        forwardArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia, distanceView, rightArrow, leftArrow,
                        forwardArrow);
                // Vypne zobrazovanie sipky pokial sa dostaneme do ciela.
                if(pozicia == traceList.size() - 1){
                    forwardArrow.setVisibility(View.GONE);
                }
            }
        });
        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia, distanceView, rightArrow, leftArrow,
                        forwardArrow);
            }
        });
        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pozicia++;
                update(sceneImage, traceList, pozicia, distanceView, rightArrow, leftArrow,
                        forwardArrow);
            }
        });

    }

    /**
     * Metoda, ktorá ziska zo stringu ciselne id obrazka
     * a nasledne ho zobrazi na obrazovke
     */
    private void update(ImageView sceneImage, ArrayList<StepImage> traceList,
                        int pozicia, TextView distanceView, Button rightArrow,
                        Button leftArrow, Button forwardArrow) {

        int imageID = sceneImage.getContext().getResources().getIdentifier(
                traceList.get(pozicia).getSceneImage(),
                "drawable",
                sceneImage.getContext().getPackageName()
        );
        sceneImage.setImageResource(imageID);

        // Tu sa pri kazdom pohybe do noveho vrcholu vzdialenost prepocitava
        String text_vypis = distanceMessage();
        distanceView.setText(text_vypis);
        textToSpeech(text_vypis);

        if (traceList.get(pozicia).getArrow() == Arrow.RIGHT){
            rightArrow.setVisibility(View.VISIBLE);
        } else {
            rightArrow.setVisibility(View.GONE);
        }

        if (traceList.get(pozicia).getArrow() == Arrow.LEFT){
            leftArrow.setVisibility(View.VISIBLE);
        } else {
            leftArrow.setVisibility(View.GONE);
        }

        if (traceList.get(pozicia).getArrow() == Arrow.FORWARD){
            forwardArrow.setVisibility(View.VISIBLE);
        } else {
            forwardArrow.setVisibility(View.GONE);
        }

    }

    /**
     * Ak je na zaciatku scita cely zoznam vzdialenosti
     * ak je niekde na ceste odcita poslednu prejdenu vzdialenost
     * Poznamka, ak StepImage v traceListe na aktualnej pozicii ma getDistance (vzdialenost)
     * rovnu 0, to znamena ze sa dalej iba otocime, teda message zobrazi vyzvu na otocenie.
     * @return sprava na zobrazenie
     */
    private String distanceMessage(){
        if (pozicia == 0) {
            for (StepImage stepImage : traceList) {
                distance += stepImage.getDistance();
            }
        }
        else{
            distance -= traceList.get(pozicia - 1).getDistance();
        }

        if(traceList.get(pozicia).getDistance() == 0 && traceList.get(pozicia).getArrow()
                == Arrow.LEFT){
            return "Otočte sa doľava.";
        } else if(traceList.get(pozicia).getDistance() == 0 && traceList.get(pozicia).getArrow()
                == Arrow.RIGHT) {
            return "Otočte sa doprava.";
        } else {
            return "Vzdialenosť do ciela je: " + distance +
                    (distance <= 4 && distance > 0 ? (distance >= 2 ? " metre." : " meter.") :
                            " metrov.");
        }

    }

    /**
     * Inicializácia interfacu pre TextToSpeech funkciu.
     * @param language nastavenie lokalizácie
     */
    private void textToSpeechInit(String language){
        tts_engine = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    if (Objects.equals(language, "Slovak")){
                        tts_engine.setLanguage(new Locale("sk", "SK"));
                        textToSpeech(intro_sk);
                    } else {
                        tts_engine.setLanguage(new Locale("en", "US"));
                        textToSpeech(intro_en);
                    }
                }

            }
        });
    }

    /**
     * Metóda, ktorá z daného stringu vytvorí hlasovú nahrávku, ktorú hneď prehrá.
     * @param text text na prehratie
     */
    private void textToSpeech(String text){
        tts_engine.speak(text, TextToSpeech.QUEUE_ADD, null);
    }

    /**
     * Nastavenie jazyka (v nastavení aplikácie TODO)
     * @param lang jazyk
     */
    private void setLanguage(String lang){
        language = lang;
    }

}