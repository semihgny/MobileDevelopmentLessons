package com.example.hesapmakinesi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    Button buton1;
    EditText et1,et2,et3;
    RadioButton rb1,rb2,rb3,rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buton1 = (Button) findViewById(R. id.button1);
        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        rb1 = (RadioButton) findViewById(R.id.radioButton1);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);


        buton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x,y,z;

                // x = Float.valueOf( et1.getText().toString() );
                // y = Float.valueOf( et2.getText().toString() );

                x = Float.parseFloat( et1.getText().toString() );
                y = Float.parseFloat( et2.getText().toString() );
                z = (float) 0;

                if(rb1.isChecked())  z = x + y;
                if(rb2.isChecked())  z = x - y;
                if(rb3.isChecked())  z = x * y;
                if(rb4.isChecked())  z = x / y;

                et3.setText(Float.toString(z));
            }
        });
    }
}