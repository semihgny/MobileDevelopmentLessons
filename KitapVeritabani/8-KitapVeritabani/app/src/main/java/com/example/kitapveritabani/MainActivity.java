package com.example.kitapveritabani;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    //Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnKaydet, btnAraDüzeltSil, btnListe, btnÇık, btnTabloSil;

        btnKaydet = (Button) findViewById(R.id.btnKaydet);
        btnAraDüzeltSil = (Button) findViewById(R.id.btnAraDüzeltSil);
        btnListe = (Button) findViewById(R.id.btnListe);
        btnÇık = (Button) findViewById(R.id.btnÇık);
        btnTabloSil = (Button) findViewById(R.id.buttontablosil);

         btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KaydetActivity.class);
                startActivity(intent);
            }
        });

        btnAraDüzeltSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AraDuzeltSilActivity.class);
                startActivity(intent);
            }
        });

        btnListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListeActivity.class);
                startActivity(intent);
            }
        });

        btnÇık.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        btnTabloSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db=new Database(getApplicationContext());
                db.resetTables();
            }
        });
    }
}