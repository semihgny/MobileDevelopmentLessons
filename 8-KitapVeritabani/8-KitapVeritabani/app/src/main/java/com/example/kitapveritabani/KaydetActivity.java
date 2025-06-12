package com.example.kitapveritabani;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class KaydetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaydet);


        Button btn1,btn2;

        EditText et1, et2, et3, et4, et5;

        btn1 = (Button) findViewById(R.id.buttonKaydet);
        btn2 = (Button) findViewById(R.id.buttonGeri1);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
        et5 = (EditText) findViewById(R.id.editText5);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kitap_kodu, kitap_adı, kitap_yazarı, kitap_basım_yılı,kitap_fiyatı;

                kitap_kodu= et1.getText().toString();
                kitap_adı= et2.getText().toString();
                kitap_yazarı= et3.getText().toString();
                kitap_basım_yılı= et4.getText().toString();
                kitap_fiyatı= et5.getText().toString();

                    Database db = new Database(getApplicationContext());
                    db.kitapEkle(kitap_kodu, kitap_adı, kitap_yazarı, kitap_basım_yılı, kitap_fiyatı);

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}