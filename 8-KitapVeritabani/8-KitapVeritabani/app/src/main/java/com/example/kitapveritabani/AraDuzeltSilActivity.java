package com.example.kitapveritabani;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class AraDuzeltSilActivity extends Activity {

    EditText etAranan, et1, et2, et3, et4, et5;
    Button btn1, btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ara_duzelt_sil);

        etAranan=(EditText) findViewById(R.id.editText11);
        et1=(EditText) findViewById(R.id.editText12);
        et2=(EditText) findViewById(R.id.editText13);
        et3=(EditText) findViewById(R.id.editText14);
        et4=(EditText) findViewById(R.id.editText15);
        et5=(EditText) findViewById(R.id.editText16);

        btn1=(Button) findViewById(R.id.buttonAra);
        btn2=(Button) findViewById(R.id.buttonDüzelt);
        btn3=(Button) findViewById(R.id.buttonSil);
        btn4=(Button) findViewById(R.id.buttonGeri4);


        //------------------------------------------------ ARA --------------------------
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext());
                HashMap<String, String> kitap = db.kitapDetay(Integer.parseInt( etAranan.getText().toString()));

                et1.setText(kitap.get("kitap_kodu")+"");
                et2.setText(kitap.get("kitap_adı").toString());
                et3.setText(kitap.get("kitap_yazarı").toString());
                et4.setText(kitap.get("kitap_basım_yılı").toString());
                et5.setText(kitap.get("kitap_fiyat").toString());
            }
        });


        //------------------------------------------------ DÜZELT --------------------------
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kitap_kodu, kitap_adı, kitap_yazarı,kitap_basım_yili,kitap_fiyati;

                kitap_kodu = et1.getText().toString();
                kitap_adı = et2.getText().toString();
                kitap_yazarı = et3.getText().toString();
                kitap_basım_yili = et4.getText().toString();
                kitap_fiyati = et5.getText().toString();

                if(kitap_kodu.matches("") || kitap_adı.matches("") || kitap_yazarı.matches("") ||
                        kitap_basım_yili.matches("") || kitap_fiyati.matches("")){
                    Toast.makeText(getApplicationContext(), "Tümünü Doldurun", Toast.LENGTH_LONG).show();
                }
                else{
                    Database db = new Database(getApplicationContext());
                    db.kitapDüzenle(kitap_kodu, kitap_adı, kitap_yazarı, kitap_basım_yili, kitap_fiyati);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Kayıt güncellendi", Toast.LENGTH_LONG).show();
                }
            }
        });

        //------------------------------------------------ SİL --------------------------
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AraDuzeltSilActivity.this);
                alertDialog.setTitle("Uyarı");
                alertDialog.setMessage("Kitap Silinsin mi?");

                alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener()                 {
                    public void onClick(DialogInterface dialog,int which) {
                        Database db = new Database(getApplicationContext());
                        db.kitapSil( Integer.parseInt(etAranan.getText().toString()));
                        Toast.makeText(getApplicationContext(), "Kitap Silindi", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which)
                    {

                    }
                });

                alertDialog.show();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

    }
}