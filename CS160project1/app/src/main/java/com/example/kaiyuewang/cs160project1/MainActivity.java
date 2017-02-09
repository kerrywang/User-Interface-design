package com.example.kaiyuewang.cs160project1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.example.cs160project1.MESSAGE";
    public final static String EXTRA_Amount = "com.example.cs160project1.AMOUNT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.workout_spinner);
        createSpinner(spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                TextView unit = (TextView)findViewById(R.id.unit);
                if (item.equals("Push Up") || item.equals("Sit Up")||item.equals("Squats")||item.equals("Pullup"))
                    unit.setText("Rep");
                else
                   unit.setText("Min");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void createSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.WorkOut_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
    public void calcworkout(View view){
        Intent intent = new Intent(this,WorkOutConversion.class);
        EditText editText = (EditText) findViewById(R.id.select_cal);
        String temp = editText.getText().toString();
        double amount = 0;
        if(temp == null || temp.isEmpty())
            amount=0;
        else
            amount=Double.parseDouble(temp);
        Bundle b = new Bundle();
        b.putDouble("targetCal",amount);
        intent.putExtras(b);
        startActivity(intent);
    }

    public void sendMessage(View view){
        Intent intent = new Intent(this, ConversionDetail.class);
        Spinner spinner = (Spinner) findViewById(R.id.workout_spinner);
        String selected = spinner.getSelectedItem().toString();

        EditText editText = (EditText) findViewById(R.id.select_amount);
        String temp = editText.getText().toString();
        double amount;

        if(temp == null || temp.isEmpty())
            amount = 0.0;
        else{
            amount = Double.parseDouble(temp);
        }

        Bundle b = new Bundle();
        b.putDouble("amountkey",amount);
        intent.putExtras(b);
        intent.putExtra(EXTRA_MESSAGE, selected);
        startActivity(intent);
    }

}





