package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lv2 =(ListView) findViewById(R.id.listView2);

        String gunler[] = getResources().getStringArray(R.array.günler);

        ArrayAdapter<String> adaptör = new ArrayAdapter<String>(   getApplicationContext(), R.layout.litview_gorunum, R.id.textView2,  gunler  );

        lv2.setAdapter(adaptör);


        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity2.this, "Seçilen gün numarası : " + position, Toast.LENGTH_LONG ).show();
                Toast.makeText(MainActivity2.this, "Seçilen gün : " + gunler[position], Toast.LENGTH_LONG ).show();
            }
        });
    }
}