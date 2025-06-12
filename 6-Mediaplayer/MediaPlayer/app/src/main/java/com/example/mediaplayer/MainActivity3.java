package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity3 extends AppCompatActivity {

    Button btn6, btn7, btn8, btn9, btn10, btn11, btn12;
    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);

        btn9 = (Button) findViewById(R.id.button9);
        btn10 = (Button) findViewById(R.id.button10);
        btn11 = (Button) findViewById(R.id.button11);
        btn12 = (Button) findViewById(R.id.button12);

        vv = (VideoView) findViewById(R.id.videoView1);

        MediaController mediaController = new MediaController(this);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "//" + R.raw.video1);
        vv.setVideoURI(video);

        vv.setMediaController(mediaController);
        mediaController.setAnchorView(vv);

        vv.start();

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.stopPlayback();
                Uri video = Uri.parse("android.resource://" + getPackageName() + "//" + R.raw.video1);
                vv.setVideoURI(video);
                vv.start();
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.stopPlayback();
                Uri video = Uri.parse("android.resource://" + getPackageName() + "//" + R.raw.video2);
                vv.setVideoURI(video);
                vv.start();
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.stopPlayback();
                Uri video = Uri.parse("android.resource://" + getPackageName() + "//" + R.raw.video3);
                vv.setVideoURI(video);
                vv.start();
            }
        });

        //
        // Video İşlemleri
        //
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.start();
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.pause();
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.stopPlayback();
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vv.resume();
                vv.start();
            }
        });
    }
}