package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity4 extends AppCompatActivity {

    Button btn13, btn14, btn15, btn16;

    TextView tv1, tv2, tv3;
    MediaPlayer mp;

    private Handler işleyici = new Handler();

    private double geçen=0;
    private double kalan=0;
    private double toplam=0;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btn13=(Button) findViewById(R.id.button13);
        btn14=(Button) findViewById(R.id.button14);
        btn15=(Button) findViewById(R.id.button15);
        btn16=(Button) findViewById(R.id.button16);

        tv1=(TextView) findViewById(R.id.textView1);
        tv2=(TextView) findViewById(R.id.textView2);
        tv3=(TextView) findViewById(R.id.textView3);


        mp = MediaPlayer.create(MainActivity4.this, R.raw.muzik3);

        toplam = mp.getDuration();

        tv3.setText(String.format(
                "Toplam süre:\n %d saat\n %d dakika\n %d saniye\n %d milisaniye\n %d micro\n %d nano",
                TimeUnit.MILLISECONDS.toHours((long) toplam ),
                TimeUnit.MILLISECONDS.toMinutes((long) toplam),
                TimeUnit.MILLISECONDS.toSeconds((long) toplam),
                TimeUnit.MILLISECONDS.toMillis((long) toplam),
                TimeUnit.MILLISECONDS.toMicros((long) toplam),
                TimeUnit.MILLISECONDS.toNanos((long) toplam)
        ));

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                işleyici.postDelayed(yenile, 100);
            }

            private final Runnable yenile;
            {
                yenile = new Runnable() {
                    public void run() {
                        geçen = mp.getCurrentPosition();
                        kalan = toplam - geçen;

                        tv1.setText(String.format(
                                "Geçen süre:\n%d dakika\n%d saniye\n%d milisaniye",
                                TimeUnit.MILLISECONDS.toMinutes((long) geçen),
                                TimeUnit.MILLISECONDS.toSeconds((long) geçen),
                                TimeUnit.MILLISECONDS.toMillis((long) geçen)
                        ));

                        tv2.setText(String.format(
                                "Kalan süre:\n%d dakika\n%d saniye\n%d milisaniye",
                                TimeUnit.MILLISECONDS.toMinutes((long) kalan),
                                TimeUnit.MILLISECONDS.toSeconds((long) kalan),
                                TimeUnit.MILLISECONDS.toMillis((long) kalan)
                        ));
                        işleyici.postDelayed(this, 100);
                    }
                };
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
            }
        });

        btn16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.seekTo(0);
            }
        });
    }
}