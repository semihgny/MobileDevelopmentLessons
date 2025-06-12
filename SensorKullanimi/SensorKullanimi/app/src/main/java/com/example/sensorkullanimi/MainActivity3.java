package com.example.sensorkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity3 extends AppCompatActivity {

   CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        cb6 = (CheckBox) findViewById(R.id.checkBox6);
        cb7 = (CheckBox) findViewById(R.id.checkBox7);
        cb8 = (CheckBox) findViewById(R.id.checkBox8);

        SensorManager sensörYönetici = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null)
            cb1.setChecked(true); else cb1.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null)
            cb2.setChecked(true); else cb2.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null)
            cb3.setChecked(true); else cb3.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null)
            cb4.setChecked(true); else cb4.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_LIGHT) != null)
            cb5.setChecked(true); else cb5.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_PRESSURE) != null)
            cb6.setChecked(true); else cb6.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null)
            cb7.setChecked(true); else cb7.setChecked(false);

        if(sensörYönetici.getDefaultSensor(Sensor.TYPE_HEART_BEAT) != null)
            cb8.setChecked(true); else cb8.setChecked(false);

    }
}