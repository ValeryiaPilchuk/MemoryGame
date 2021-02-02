package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.memorygame.Settings.SHARED_PREFS;
import static com.example.memorygame.Settings.SLIDE1;

public class MainActivity extends AppCompatActivity {

    int slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent2 = new Intent(this, Game2.class);
        Intent intent4 = new Intent(this, Game4.class);
        Intent intent6 = new Intent(this, Game6.class);
        Intent intent8 = new Intent(this, Game8.class);
        Intent intent10 = new Intent(this, Game10.class);
        Intent intent12 = new Intent(this, Game12.class);
        Intent intent14 = new Intent(this, Game14.class);
        Intent intent16 = new Intent(this, Game16.class);
        Intent intent18 = new Intent(this, Game18.class);
        Intent intent20 = new Intent(this, Game20.class);

        Intent intentSettings = new Intent(this, Settings.class);

        Button buttonSettings = (Button) findViewById(R.id.Settingsbutton);
        buttonSettings.setOnClickListener(v -> startActivity(intentSettings));

        Button buttonStart = (Button) findViewById(R.id.StartButton);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sp = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                slider = sp.getInt(SLIDE1, 12);
                if (slider == 2)
                    startActivity(intent2);
                else if (slider == 4)
                    startActivity(intent4);
                else if (slider == 6)
                   startActivity(intent6);
                else if (slider == 8)
                   startActivity(intent8);
                else if (slider == 10)
                    startActivity(intent10);
                else if (slider == 12)
                    startActivity(intent12);
                else if (slider == 14)
                    startActivity(intent14);
                else if (slider == 16)
                    startActivity(intent16);
                else if (slider == 18)
                    startActivity(intent18);
                else if (slider == 20)
                    startActivity(intent20);
            }
        });

    }
}