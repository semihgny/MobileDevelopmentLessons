package com.example.kitapveritabani;

import java.util.ArrayList;
import java.util.HashMap;
//import com.example.kitapveritabani.R;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class ListeActivity extends Activity {
    ListView lv;
    Button btnGeri;
    ArrayAdapter<String> adapter;
    ArrayList<HashMap<String, String>> kitap_liste;
    String kitap_adlari[];
        //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        btnGeri = (Button) findViewById(R.id.buttonGeri8);

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void onResume() {
        super.onResume();

        Database db = new Database(getApplicationContext());

        lv = (ListView) findViewById(R.id.listView);

        kitap_liste = db.kitaplar();

        if (kitap_liste.size() == 0) {
            Toast.makeText(getApplicationContext(), "Henüz Kitap Eklenmemiş.", Toast.LENGTH_LONG).show();
        } else {
            kitap_adlari = new String[kitap_liste.size()];


            for (int i = 0; i < kitap_liste.size(); i++) {
               kitap_adlari[i] = kitap_liste.get(i).get("kitap_adı");
           }

            adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textview25, kitap_adlari);
            lv.setAdapter(adapter);

        }
    }

}
