package com.example.sensorkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity implements SensorEventListener {

    TextView tv8, tv9, tv10, tv11, tv12, tv13;

    SensorManager sensörYönetici;

    private Sensor sıcaklıkÖlçer;
    private Sensor manyetikAlanaÖlçer;
    private Sensor mesafeÖlçer;
    private Sensor ışıkÖlçer;
    private Sensor basınçÖlçer;
    private Sensor nemÖlçer;

    float sıcaklık=0, manyetikAlan=0, mesafe=0, ışık=0, basınç=0, nem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        tv8 = (TextView) findViewById(R.id.textView8);
        tv9 = (TextView) findViewById(R.id.textView9);
        tv10 = (TextView) findViewById(R.id.textView10);
        tv11 = (TextView) findViewById(R.id.textView11);
        tv12 = (TextView) findViewById(R.id.textView12);
        tv13 = (TextView) findViewById(R.id.textView13);

        sensörYönetici = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sıcaklıkÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        manyetikAlanaÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mesafeÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        ışıkÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_LIGHT);
        basınçÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_PRESSURE);
        nemÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) sıcaklık = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) manyetikAlan = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) mesafe = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) ışık = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) basınç = sensorEvent.values[0];
        if(sensorEvent.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) nem = sensorEvent.values[0];

        tv8.setText("Sıcaklık = " + sıcaklık);
        tv9.setText("Manyetik alan = " + manyetikAlan);
        tv10.setText("Mesafe = " + mesafe);
        tv11.setText("Işık = " + ışık);
        tv12.setText("Basınç = " + basınç);
        tv13.setText("Nem = " + nem);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        sensörYönetici.registerListener(this, sıcaklıkÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
        sensörYönetici.registerListener(this, manyetikAlanaÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
        sensörYönetici.registerListener(this, mesafeÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
        sensörYönetici.registerListener(this, ışıkÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
        sensörYönetici.registerListener(this, basınçÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
        sensörYönetici.registerListener(this, nemÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        sensörYönetici.unregisterListener(this);
    }
}