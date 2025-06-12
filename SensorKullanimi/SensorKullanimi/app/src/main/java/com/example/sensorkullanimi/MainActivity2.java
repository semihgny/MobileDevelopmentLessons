package com.example.sensorkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = (TextView) findViewById(R.id.textView1);

        SensorManager senörYönetici = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensörler = senörYönetici.getSensorList(Sensor.TYPE_ALL);

        StringBuilder sensörListe = new StringBuilder();

        for( Sensor sensör: sensörler)
        {
           sensörListe.append( sensör.getName() + "\n");
            sensörListe.append(sensör.getType() + "\n");
            sensörListe.append(sensör.getVendor() + "\n");
            sensörListe.append(sensör.getVersion() + "\n");
            sensörListe.append(sensör.getMaximumRange() + "\n");
            sensörListe.append(sensör.getPower() + "\n\n");
        }
        tv1.setText(sensörListe.toString());
    }
}