package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    ListView lv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        lv3 = (ListView) findViewById(R.id.listview3);

        String bolumler[] = new String[4];

        bolumler[0] = "Bilgisayar";
        bolumler[1] = "Makine";
        bolumler[2] = "İnşaat";
        bolumler[3] = "Elektrik";

        ArrayAdapter<String> adaptör = new ArrayAdapter<String>(getApplicationContext(), R.layout.litview_gorunum, R.id.textView3, bolumler );

        lv3.setAdapter(adaptör);

        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast tost;
                tost = Toast.makeText(getApplicationContext(), "Seçilen Bölüm :" + bolumler[position], Toast.LENGTH_LONG);
                tost.show();
            }
        });
    }
}