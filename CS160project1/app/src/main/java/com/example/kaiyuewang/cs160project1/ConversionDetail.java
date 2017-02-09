package com.example.kaiyuewang.cs160project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

public class ConversionDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Personal Trainer");
        setContentView(R.layout.activity_conversion_detail);
        //create conversion data base
        HashMap<String,Double> caloriePerRep = new HashMap<>();
        HashMap<String,Double> repPerCal = new HashMap<>();
        HashMap<String,Double> caloriePerMin = new HashMap<>();
        HashMap<String,Double> MinPerCal = new HashMap<>();
        caloriePerRep.put("Push Up",0.285);
        repPerCal.put("Push Up",3.5);
        caloriePerRep.put("Sit Up",0.5);
        repPerCal.put("Sit Up",2.0);
        caloriePerRep.put("Squats",0.44);
        repPerCal.put("Squats",2.25);
        caloriePerRep.put("Pullup",1.0);
        repPerCal.put("Pullup",1.0);


        caloriePerMin.put("Cycling",8.33);
        MinPerCal.put("Cycling",0.12);
        caloriePerMin.put("Walking",5.0);
        MinPerCal.put("Walking",0.2);
        caloriePerMin.put("Swimming",7.69);
        MinPerCal.put("Swimming",0.13);
        caloriePerMin.put("Stair-Climbing",6.66);
        MinPerCal.put("Stair-Climbing",0.15);
        caloriePerMin.put("Plank",4.0);
        MinPerCal.put("Plank",0.25);
        caloriePerMin.put("Leg-lift",4.0);
        MinPerCal.put("Leg-lift",0.25);
        caloriePerMin.put("Jumping Jacks",10.0);
        MinPerCal.put("Jumping Jacks",0.1);
        caloriePerMin.put("Jogging",8.33);
        MinPerCal.put("Jogging",0.12);

        //get Intent
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Bundle b = intent.getExtras();
        double amount = b.getDouble("amountkey");


        // create textview instance
        TextView cal = (TextView)findViewById(R.id.CalBurnt);
        //compute
        double totalCalorieBurnt = 0;
        if(caloriePerMin.get(message) != null){
            double calperSet = caloriePerMin.get(message);
            long output = Math.round(calperSet*amount);
            totalCalorieBurnt = calperSet*amount;
            cal.setText(Long.toString(output));
        }

        if(caloriePerRep.get(message) != null){
            double calperSet = caloriePerRep.get(message);
            long output = Math.round(calperSet*amount);
            totalCalorieBurnt = calperSet*amount;
            cal.setText(Long.toString(output));
        }

        //Output Conversion table
        TextView table = (TextView)findViewById(R.id.conversionTable);
        String tablestring = "";
        //traverse repPerCall
        for(String key:repPerCal.keySet()){
            if(key.equals(message))
                continue;
            double val = repPerCal.get(key);
            double convert = totalCalorieBurnt*val;
            tablestring =tablestring + "Equivalent to: " + Long.toString(Math.round(convert)) + " Rep " + key +"\n";

        }

        for(String key:MinPerCal.keySet()){
            if(key.equals(message))
                continue;
            double val = MinPerCal.get(key);
            double convert = totalCalorieBurnt*val;
            String result = String.format("%.2f", convert);

            tablestring =tablestring + "Equivalent to: " + result + " Min " + key +"\n";

        }

        table.setText(tablestring);

    }

    public void backtoFront(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
