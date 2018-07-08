package com.example.gebruiker.hroschedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HelpPop extends LoginActivity {
    private int helpcnt = 1;
    private boolean lock = false;
    private String currentPic = "LayoutHelp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_help_pop);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        final int height = displayMetrics.heightPixels;

        getWindow().setLayout(width, height);

        final ImageView imageView = findViewById(R.id.Helpscreen);

        final Button volgende = findViewById(R.id.Volgende);
        volgende.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lock = true;
                if (helpcnt < 4) {
                    helpcnt = helpcnt + 1;
                    if (helpcnt == 1) {
                        imageView.setImageResource(R.drawable.layouthelp1);
                    }
                    if (helpcnt == 2) {
                        imageView.setImageResource(R.drawable.layouthelp2);
                    }
                    if (helpcnt == 3) {
                        imageView.setImageResource(R.drawable.layouthelp3);
                    }
                    if (helpcnt >= 4) {
                        helpcnt = 1;
                        imageView.setImageResource(R.drawable.layouthelp1);
                    }
                }


            }
        });
        final Button vorige = findViewById(R.id.Vorige);
        vorige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (currentPic.equals("LayoutHelp")) {
                    helpcnt = helpcnt - 1;
                    if (helpcnt == 1) {
                        imageView.setImageResource(R.drawable.layouthelp1);
                    }
                    if (helpcnt == 2) {
                        imageView.setImageResource(R.drawable.layouthelp2);
                    }
                    if (helpcnt == 3) {
                        imageView.setImageResource(R.drawable.layouthelp3);
                    }
                    if (helpcnt <= 0) {
                        helpcnt = 3;
                        imageView.setImageResource(R.drawable.layouthelp3);
                    }
                }

            }
        });
    }
}