package com.example.sensorkullanimi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity implements SensorEventListener {

    TextView tv2, tv3, tv4;
    SensorManager sensörYönetici;
    Sensor ivmeÖlçer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        tv2= (TextView) findViewById(R.id.textView2);
        tv3= (TextView) findViewById(R.id.textView3);
        tv4= (TextView) findViewById(R.id.textView4);

        sensörYönetici = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ivmeÖlçer = sensörYönetici.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float sensörX = sensorEvent.values[0];
        float sensörY = sensorEvent.values[1];
        float sensörZ = sensorEvent.values[2];

        tv2.setText("X değeri = " + sensörX);
        tv3.setText("Y değeri = " + sensörY);
        tv4.setText("Z değeri = " + sensörZ);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume()
    {
        super.onResume();
        sensörYönetici.registerListener(this, ivmeÖlçer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected  void onPause()
    {
        super.onPause();
        sensörYönetici.unregisterListener(this);
    }
}