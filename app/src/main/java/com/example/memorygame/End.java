package com.example.memorygame;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class End extends AppCompatActivity {

    ConstraintLayout layout;
    AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end);

        Button btnsrt = (Button) findViewById(R.id.buttonrestart);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        layout = (ConstraintLayout) findViewById(R.id.endscreen);
        drawable = (AnimationDrawable) layout.getBackground();
        drawable.setEnterFadeDuration(3000);
        drawable.setExitFadeDuration(3000);
        drawable.start();


        Intent intentStart = new Intent(this, MainActivity.class);

        btnsrt.setOnClickListener(v -> startActivity(intentStart));

    }
}