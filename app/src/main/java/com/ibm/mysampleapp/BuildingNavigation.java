package com.ibm.mysampleapp;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mato on 1. 11. 2017.
 */

public class BuildingNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.building_navigation);

        //TODO UVODNA FOTKA + ZOZNAM MIESTNOSTÍ(ŠÍPKY???)

        final ImageView start_image = (ImageView) findViewById(R.id.imageView);
        final Button rightArrow = (Button) findViewById(R.id.buttonRight);
        final Button leftArrow = (Button) findViewById(R.id.buttonLeft);

        start_image.setImageResource(R.drawable.obrazok1);

        rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_image.setImageResource(R.drawable.obrazok2);
                rightArrow.setVisibility(View.GONE);
            }
        });

        leftArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_image.setImageResource(R.drawable.obrazok3);
                leftArrow.setVisibility(View.GONE);
            }
        });



    }
}
