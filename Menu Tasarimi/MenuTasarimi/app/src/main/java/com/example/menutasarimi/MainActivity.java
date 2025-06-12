package com.example.menutasarimi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.itemDiğerEkran) {
            Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
            startActivity(intent);
            //overridePendingTransition(R.anim.anim, R.anim.anim_out);
            return true;
        }
        else
        if (id == R.id.itemToast1) {
            Toast.makeText(getApplicationContext(), "Bu bir tost mesajıdır", Toast.LENGTH_LONG).show();
            return true;
        }
        else
        if (id == R.id.itemToast2) {
            Toast tost = Toast.makeText(getApplicationContext(), "Ağa bağlanıldı", Toast.LENGTH_SHORT);

            //View view = tost.getView();
            //view.setBackgroundColor(Color.BLUE);
            //view.setBackgroundColor(Color.TRANSPARENT);
            //view.setBackgroundColor(Color.parseColor("#0000FF"));

            //TextView text = (TextView) view.findViewById(android.R.id.message);

            //text.setTextColor(Color.RED);
            //text.setTextColor(Color.parseColor("#FF0000"));

            tost.setText("Ağ bağlantısı kuruldu");
            tost.setDuration(Toast.LENGTH_LONG);

            tost.show();

            return true;
        }

        if (id == R.id.itemSnackBar1) {
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Bu bir Snackbar mesajıdır", Snackbar.LENGTH_LONG);
            snackbar.show();
            return true;
        }
        else
        if (id == R.id.itemSnackBar2) {

            final Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Sayfa kapatıldı", Snackbar.LENGTH_LONG);
            snackbar.setAction("Geri Al", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                    Toast.makeText(MainActivity.this,"Sayfa yeniden yüklendi",Toast.LENGTH_LONG).show();
                }
            });

            snackbar.show();
            return true;
        }
        else
        if (id == R.id.itemÇıkış) {

            AlertDialog.Builder uyarıOluşturucu = new AlertDialog.Builder(MainActivity.this);

            uyarıOluşturucu.setTitle("Uyarı");

            uyarıOluşturucu.setMessage("Değişiklikler kaydedilsin mi?");
            //uyarıOluşturucu.setCancelable(false);
            uyarıOluşturucu.setPositiveButton("Evet",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Toast.makeText(getApplicationContext(), "Değişiklikler Kaydedildi", Toast.LENGTH_LONG).show();
                    MainActivity.this.finish();
                }
            });
            uyarıOluşturucu.setNegativeButton("Hayır",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    Toast.makeText(getApplicationContext(), "Değişiklikler Kaydedilmedi", Toast.LENGTH_LONG).show();
                    MainActivity.this.finish();
                }
            });
            uyarıOluşturucu.setNeutralButton("İptal",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    Toast.makeText(getApplicationContext(), "İşlem iptal edildi", Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            });

            AlertDialog uyarıPenceresi = uyarıOluşturucu.create();
            uyarıPenceresi.show();


            /*
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Uyarı");
            alertDialog.setMessage("Değişiklikler kaydedilisin mi?");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Tamam",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            */
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}