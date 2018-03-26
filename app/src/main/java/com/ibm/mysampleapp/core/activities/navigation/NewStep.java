package com.ibm.mysampleapp.core.activities.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.route.Steps;

import java.util.ArrayList;

/**
 * Created by mato on 26. 3. 2018.
 */

public class NewStep extends AppCompatActivity {

    ImageView stepImage = (ImageView) findViewById(R.id.stepImage);
    EditText stepDescription = (EditText) findViewById(R.id.stepDescription);
    EditText stepDistance = (EditText) findViewById(R.id.stepDistance);
    EditText stepArrow = (EditText) findViewById(R.id.stepArrow);
    Button createSteps = (Button) findViewById(R.id.create_steps);
    Button finishActivity = (Button) findViewById(R.id.finish_steps);

    String stepDesc;
    int stepDist;
    String stepArrowString;
    int routeID;
    Bundle extras;
    Boolean isParsetSuccessfully = false;

    ArrayList<Steps> steps = new ArrayList<Steps>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_step_layout);

        extras = getIntent().getExtras();

        if (extras != null) {
            routeID = extras.getInt("ROUTE");
        }

        createSteps.setOnClickListener((View v) -> {

            stepDesc = stepDescription.getText().toString();
            stepDist = Integer.valueOf(stepDistance.getText().toString());
            stepArrowString = stepArrow.getText().toString();

            if(descIsEmpty(stepDesc)){
                stepDescription.setError("Zadajte popis!");
            } else if(distIsEmpty(stepDist)){
                stepDistance.setError("Zadajte vzdialenosť od cieľa!");
            } else if(arrowIsEmpty(stepArrowString)){
                stepArrow.setError("Zadajte smer!");
            } else{
                //TODO PARSNUT VYSLEDOK desc, distance, arrow do route(routeID)

                isParsetSuccessfully = true;
            }

        });


        finishActivity.setOnClickListener(v -> {
            if(isParsetSuccessfully){
                Intent intent = new Intent(getApplicationContext(), RouteMenu.class);
                getApplicationContext().startActivity(intent);
                finish();
            }
        });

    }

    public boolean descIsEmpty(String stepDesc){
        return stepDesc.matches("");
    }

    public boolean distIsEmpty(int stepDist){
        return stepDist == 0;
    }

    public boolean arrowIsEmpty(String stepArrow){
        return stepArrow.matches("");
    }





}
