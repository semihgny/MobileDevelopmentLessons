package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity2 extends AppCompatActivity {

    Button btn4, btn5;
    ImageView iv;
    int s=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        iv = (ImageView) findViewById(R.id.imageView);

        iv.setImageResource(R.drawable.resim1);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s--;
                if(s==0) s=3;
                if(s==1) iv.setImageResource(R.drawable.resim1);
                if(s==2) iv.setImageResource(R.drawable.resim2);
                if(s==3) iv.setImageResource(R.drawable.resim3);

            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s++;
                if(s==4) s=1;

                if(s==1) iv.setImageResource(R.drawable.resim1);
                if(s==2) iv.setImageResource(R.drawable.resim2);
                if(s==3) iv.setImageResource(R.drawable.resim3);

            }
        });


    }
}