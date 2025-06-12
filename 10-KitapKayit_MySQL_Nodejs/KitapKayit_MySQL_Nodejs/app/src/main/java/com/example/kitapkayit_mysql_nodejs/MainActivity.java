package com.example.kitapkayit_mysql_nodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    EditText etIsbn, etKitapAdi, etBasimYili;
    TextView tvSonuc;
    String baseUrl = "http://10.200.80.123:3000"; // Android Emulator'da localhost

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etIsbn = findViewById(R.id.etIsbn);
        etKitapAdi = findViewById(R.id.etKitapAdi);
        etBasimYili = findViewById(R.id.etBasimYili);
        tvSonuc = findViewById(R.id.tvSonuc);

        findViewById(R.id.btnKaydet).setOnClickListener(v -> kaydet());
        findViewById(R.id.btnAra).setOnClickListener(v -> ara());
        findViewById(R.id.btnListele).setOnClickListener(v -> listele());
    }

    void kaydet() {
        String isbn = etIsbn.getText().toString();
        String ad = etKitapAdi.getText().toString();
        String yil = etBasimYili.getText().toString();

        JSONObject json = new JSONObject();
        try {
            json.put("isbn_no", isbn);
            json.put("kitap_adi", ad);
            json.put("basim_yili", Integer.parseInt(yil));
        } catch (JSONException e) { e.printStackTrace(); }

        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.get("application/json")
        );

        new OkHttpClient().newCall(
                new Request.Builder()
                        .url(baseUrl + "/ekle")
                        .post(body)
                        .build()
        ).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                runOnUiThread(() -> tvSonuc.setText("Kayıt başarılı."));
            }
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> tvSonuc.setText("Hata: " + e.getMessage()));
            }
        });
    }

    void ara() {
        String isbn = etIsbn.getText().toString();
        new OkHttpClient().newCall(
                new Request.Builder()
                        .url(baseUrl + "/ara/" + isbn)
                        .build()
        ).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                String sonuc = response.body().string();
                try {
                    JSONArray kitaplar = new JSONArray(sonuc);
                    StringBuilder metin = new StringBuilder();
                    metin.append(String.format("%-15s %-30s %s\n", "ISBN", "Kitap Adı", "Basım Yılı"));
                    metin.append("---------------------------------------------------------------\n");

                    for (int i = 0; i < kitaplar.length(); i++) {
                        JSONObject kitap = kitaplar.getJSONObject(i);
                        metin.append(String.format(
                                "%-15s %-30s %d\n",
                                kitap.getString("isbn_no"),
                                kitap.getString("kitap_adi"),
                                kitap.getInt("basim_yili")
                        ));
                    }

                    runOnUiThread(() -> tvSonuc.setText(metin.toString()));
                } catch (JSONException e) {
                    runOnUiThread(() -> tvSonuc.setText("Hatalı veri formatı: " + e.getMessage()));
                }
            }

            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> tvSonuc.setText("Hata: " + e.getMessage()));
            }
        });
    }


    void listele() {
        new OkHttpClient().newCall(
                new Request.Builder()
                        .url(baseUrl + "/listele")
                        .build()
        ).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                String sonuc = response.body().string();
                try {
                    JSONArray liste = new JSONArray(sonuc);
                    StringBuilder metin = new StringBuilder();
                    metin.append(String.format("%-15s %-30s %s\n", "ISBN", "Kitap Adı", "Basım Yılı"));
                    metin.append("---------------------------------------------------------------\n");
                    for (int i = 0; i < liste.length(); i++) {
                        JSONObject kitap = liste.getJSONObject(i);
                        metin.append(String.format(
                                "%-15s %-30s %d\n",
                                kitap.getString("isbn_no"),
                                kitap.getString("kitap_adi"),
                                kitap.getInt("basim_yili")
                        ));
                    }
                    runOnUiThread(() -> tvSonuc.setText(metin.toString()));
                } catch (JSONException e) {
                    runOnUiThread(() -> tvSonuc.setText("Hatalı veri formatı"));
                }
            }

            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> tvSonuc.setText("Hata: " + e.getMessage()));
            }
        });
    }
}


//----- VERİLERİN JSON FORMATINDA DÖNMESİ İÇİN KODLAR ---------------
/*
    void ara() {
    String isbn = etIsbn.getText().toString();
    new OkHttpClient().newCall(
            new Request.Builder()
                    .url(baseUrl + "/ara/" + isbn)
                    .build()
    ).enqueue(new Callback() {
        public void onResponse(Call call, Response response) throws IOException {
            String sonuc = response.body().string();
            runOnUiThread(() -> tvSonuc.setText("Sonuç: " + sonuc));
        }
        public void onFailure(Call call, IOException e) {
            runOnUiThread(() -> tvSonuc.setText("Hata: " + e.getMessage()));
        }
    });
}


    void listele() {
        new OkHttpClient().newCall(
                new Request.Builder()
                        .url(baseUrl + "/listele")
                        .build()
        ).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                String sonuc = response.body().string();
                runOnUiThread(() -> tvSonuc.setText("Kayıtlar:\n" + sonuc));
            }
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> tvSonuc.setText("Hata: " + e.getMessage()));
            }
        });
    }
*/




