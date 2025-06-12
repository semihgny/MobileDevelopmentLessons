package com.example.metindosyasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button buton1, buton2;
    EditText editText1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton1 = (Button) findViewById(R.id.button1);
        buton2 = (Button) findViewById(R.id.button2);
        editText1 = (EditText) findViewById(R.id.editText1);

        File dizin = getDir("Dosyalar", MODE_APPEND);
        File dosya = new File (dizin, "Örnek.Txt");

        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream yazılacakDosya = new FileOutputStream(dosya);
                    OutputStreamWriter dosyaYazıcı = new OutputStreamWriter(yazılacakDosya);
                    dosyaYazıcı.write(editText1.getText().toString());
                    dosyaYazıcı.close();
                    Toast.makeText(MainActivity.this, "Dosya kaydedildi", Toast.LENGTH_LONG).show();
                } catch (IOException e) {e.printStackTrace();}
            }
        });

        buton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String metin="";
                    String satır ;

                    FileInputStream okunacakDosya = new FileInputStream(dosya);
                    InputStreamReader dosyaOkuyucu = new InputStreamReader(okunacakDosya);
                    BufferedReader arabellekOkuyucu = new BufferedReader(dosyaOkuyucu);

                    while(   (satır = arabellekOkuyucu.readLine())   != null )
                        metin += satır + "\n";

                    dosyaOkuyucu.close();
                    editText1.setText(metin);
                    Toast.makeText(MainActivity.this, "Dosya açıldı", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {e.printStackTrace();}
            }
        });
    }
}