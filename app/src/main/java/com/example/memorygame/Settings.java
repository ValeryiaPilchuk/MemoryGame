package com.example.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private TextView seekbarText;
    int seekbarValue = 12;
    String valueGiven;
    EditText editText;
    Button startButton;
    ToggleButton onoff;
    Boolean toggle = false;
    SharedPreferences sp;
    int slider;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";
    public static final String SLIDE1 = "slide1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Intent intent2 = new Intent(Settings.this, Game2.class);
        Intent intent4 = new Intent(Settings.this, Game4.class);
        Intent intent6 = new Intent(Settings.this, Game6.class);
        Intent intent8 = new Intent(Settings.this, Game8.class);
        Intent intent10 = new Intent(Settings.this, Game10.class);
        Intent intent12 = new Intent(Settings.this, Game12.class);
        Intent intent14 = new Intent(Settings.this, Game14.class);
        Intent intent16 = new Intent(Settings.this, Game16.class);
        Intent intent18 = new Intent(Settings.this, Game18.class);
        Intent intent20 = new Intent(Settings.this, Game20.class);

        sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        /* seekbar is the slider and text is the text above slider **/

        SeekBar seekbar = (SeekBar) findViewById(R.id.slider);
        seekbarText = (TextView) findViewById(R.id.textViewAmount);

        /* editText getting a number from 0-99 from user   **/

        editText = (EditText) findViewById(R.id.editTextNumber);

        /* turn all cards around */
        onoff = (ToggleButton) findViewById(R.id.toggleButton);
        toggle = sp.getBoolean(SWITCH1, false);
        onoff.setOnCheckedChangeListener((buttonView, isChecked) -> {

            if (isChecked) {
                toggle = true;
            } else {
                toggle = false;
            }

            editor.putBoolean(SWITCH1, toggle);

        });

        //seekbar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarValue = progress;
                int stepSize = 2;
                progress = (progress / stepSize) * stepSize;
                seekBar.setProgress(progress);
                seekbarText.setText("Amount of cards:" + progress);
                editor.putInt("seekbarValue", seekbarValue);
                editor.putInt(SLIDE1, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                TextView resultText = (TextView) findViewById(R.id.textViewAmount);
                seekbarText.setText("Amount of cards:" + seekbarValue);
             //   editor.putInt(SLIDE1, seekbarValue);
            }
        });

        /* code to start game **/

        startButton = (Button) findViewById(R.id.StartButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueGiven = editText.getText().toString();
                if (valueGiven.matches("")) {
                    valueGiven = "3";
                }
                editor.putString("seconds", valueGiven);

                editor.putString(TEXT, valueGiven);
                editor.putInt(SLIDE1, seekbarValue);
                editor.apply();

                slider = sp.getInt(SLIDE1, 12);
                if (slider == 2) {
                    startActivity(intent2);
                } else if (slider == 4)
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



