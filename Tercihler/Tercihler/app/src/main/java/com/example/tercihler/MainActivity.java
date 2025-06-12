package com.example.tercihler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText et1, et2;
    ToggleButton tb1;
    RadioButton rb1, rb2;
    Switch sw1;
    CheckBox cb1;
    Button btn1, btn2;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editör;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        tb1 = (ToggleButton) findViewById(R.id.toggleButton1);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        sw1 = (Switch) findViewById(R.id.switch1);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        btn1= (Button) findViewById(R.id.button1);
        btn2= (Button) findViewById(R.id.button2);

        sharedPreferences = getSharedPreferences("com.example.tercihler.MAIN", MODE_PRIVATE);
        editör = sharedPreferences.edit();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editör.putString("com.example.tercihler.AD", et1.getText().toString());
                editör.putInt("com.example.tercihler.YAŞ", Integer.valueOf( et2.getText().toString() ));

                editör.putBoolean("com.example.tercihler.ÖĞRETİM", tb1.isChecked());
                editör.putBoolean("com.example.tercihler.CİNSİYET", sw1.isChecked());
                editör.putBoolean("com.example.tercihler.ASKERLİK", cb1.isChecked());

                if(rb1.isChecked()) editör.putInt("com.example.tercihler.SINIF", 1);
                if(rb2.isChecked()) editör.putInt("com.example.tercihler.SINIF", 2);

                editör.commit();

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}