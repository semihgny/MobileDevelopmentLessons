package com.example.tercihler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4, tv5, tv6;

    Button btn;
    String ad;
    int yaş, sınıf;
    boolean öğretim, cinsiyet, askerlik;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn=(Button) findViewById(R.id.button3);
        tv1=(TextView) findViewById(R.id.textView1);
        tv2=(TextView) findViewById(R.id.textView2);
        tv3=(TextView) findViewById(R.id.textView3);
        tv4=(TextView) findViewById(R.id.textView4);
        tv5=(TextView) findViewById(R.id.textView5);
        tv6=(TextView) findViewById(R.id.textView6);

        sharedPreferences = getSharedPreferences("com.example.tercihler.MAIN", MODE_PRIVATE);

        ad = sharedPreferences.getString("com.example.tercihler.AD", "Ad Soyad bulunamadı");
        yaş = sharedPreferences.getInt("com.example.tercihler.YAŞ", -1);
        öğretim = sharedPreferences.getBoolean("com.example.tercihler.ÖĞRETİM", false);
        cinsiyet = sharedPreferences.getBoolean("com.example.tercihler.CİNSİYET", false);
        askerlik = sharedPreferences.getBoolean("com.example.tercihler.ASKERLİK", false);
        sınıf = sharedPreferences.getInt("com.example.tercihler.SINIF", -1);


        tv1.setText(ad);
        tv2.setText(yaş + "");

        if(öğretim)  tv3.setText("1. öğretim");
        else tv3.setText("2. öğretim");

        if(cinsiyet)  tv4.setText("Kız");
        else tv4.setText("Erkek");

        if(askerlik)  tv5.setText("Yaptı");
        else tv5.setText("Yapmadı");

        if(sınıf==1) tv6.setText("1. sınıf");
        else tv6.setText("2. sınıf");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}