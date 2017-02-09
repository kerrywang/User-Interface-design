package com.example.kaiyuewang.cs160project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class WorkOutConversion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Personal Trainer");
        setContentView(R.layout.activity_work_out_conversion);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        double targetcalburn = b.getDouble("targetCal");
        TextView output = (TextView)findViewById(R.id.outputTargetWorkout);

        //create conversion data base
        HashMap<String,Double> repPerCal = new HashMap<>();
        HashMap<String,Double> MinPerCal = new HashMap<>();
        repPerCal.put("Push Up",3.5);
        repPerCal.put("Sit Up",2.0);
        repPerCal.put("Squats",2.25);
        repPerCal.put("Pullup",1.0);
        MinPerCal.put("Cycling",0.12);
        MinPerCal.put("Walking",0.2);
        MinPerCal.put("Swimming",0.13);
        MinPerCal.put("Stair-Climbing",0.15);
        MinPerCal.put("Plank",0.25);
        MinPerCal.put("Leg-lift",0.25);
        MinPerCal.put("Jumping Jacks",0.1);
        MinPerCal.put("Jogging",0.12);

        //https://darkwebnews.com/deep-web-links/

        String tablestring = "";
        //traverse repPerCall
        for(String key:repPerCal.keySet()){
            double val = repPerCal.get(key);
            double convert = targetcalburn*val;
            tablestring =tablestring + "You need to do: " + Long.toString(Math.round(convert)) + " Rep " + key +"\n";

        }

        for(String key:MinPerCal.keySet()){
            double val = MinPerCal.get(key);
            double convert = targetcalburn*val;
            String result = String.format("%.2f", convert);
            tablestring =tablestring + "You need to do: " + result + " Min " + key +"\n";

        }

        output.setText(tablestring);

    }

    public void backtoFront(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
