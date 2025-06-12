package com.example.sensorkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity5 extends AppCompatActivity implements SensorEventListener {

    TextView tv5, tv6, tv7;
    SensorManager sensörYönetici;
    Sensor yönÖlçer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tv5= (TextView) findViewById(R.id.textView5);
        tv6= (TextView) findViewById(R.id.textView6);
        tv7= (TextView) findViewById(R.id.textView7);

        sensörYönetici = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        yönÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_ORIENTATION);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float sensorX = sensorEvent.values[0];
        float sensorY = sensorEvent.values[1];
        float sensorZ = sensorEvent.values[2];

        tv5.setText("Azimuth : " + sensorX);
        tv6.setText("Pitch : " + sensorY);
        tv7.setText("Roll : " + sensorZ);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        sensörYönetici.registerListener(this, yönÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause()
    {
        super.onPause();
        sensörYönetici.unregisterListener(this);
    }
}