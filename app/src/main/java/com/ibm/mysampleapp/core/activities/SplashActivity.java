package com.ibm.mysampleapp.core.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ibm.mysampleapp.core.activities.MainActivity;

/**
 * Class slúži na zobrazovanie obrázka pri spúšťaní aplikácie. Užívateľ miesto 1-2 sekundového
 * čakania a pozerania sa na bielu obrazovku, uvidí logo spoločnosti alebo ikonu aplikácie pre
 * celkový lepší dojem. SplashScreen a jeho zapínanie je implementované v AndroidManifeste medzi
 * aktivitami a dizajn je v /res/drawable/splash_screen.xml.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}