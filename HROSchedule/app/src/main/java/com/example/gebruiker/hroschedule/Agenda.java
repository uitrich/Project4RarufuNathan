package com.example.gebruiker.hroschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Agenda extends AppCompatActivity {

    private EditText Vaknaam;
    private EditText Lokaal;

    private String Lokaalcode;
    private String gebouw;
    private Integer etage;
    private String lokaal;
    private Boolean unlockplattegrond = false;
    private String lokaalsum;

    private Bundle bundle = getIntent().getExtras();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent intent = new Intent(Agenda.this, LoginActivity.class);

        Vaknaam = findViewById(R.id.vaknaam);
        Lokaal = findViewById(R.id.lokaal);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String vaknaam = Vaknaam.getText().toString();
                String lokaal = Lokaal.getText().toString();
                if (lokaal.contains("H")) {
                    gebouw = "H";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    if (!Lokaal.getText().toString().isEmpty()) {
                        intent.putExtra("Vaknaam", vaknaam);
                        intent.putExtra("Lokaal", lokaal);
                        startActivity(intent);
                    }
                } else if (Lokaalcode.contains("WD")) {
                    gebouw = "WD";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    if (!Lokaal.getText().toString().isEmpty()) {
                        intent.putExtra("Vaknaam", vaknaam);
                        intent.putExtra("Lokaal", lokaal);
                        startActivity(intent);
                    }
                } else if (Lokaalcode.contains("WN")) {
                    gebouw = "WN";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    if (!Lokaal.getText().toString().isEmpty()) {
                        intent.putExtra("Vaknaam", vaknaam);
                        intent.putExtra("Lokaal", lokaal);
                        intent.putExtra("itemid", bundle.getString("itemid"));
                        startActivity(intent);
                    }
                } else {
                    unlockplattegrond = false;
                    Lokaal.setError("de letter(s) aan het begin van "+ Lokaalcode +" zijn incorrect");
                    Lokaal.setText("");
                }
    }

    public Integer getEtage (String lokaalnr){
        if (lokaalnr.contains(".0.")) {
            return 0;
        } else if (lokaalnr.contains(".1.")) {
            return 1;
        } else if (lokaalnr.contains(".2.")) {
            return 2;
        } else if (lokaalnr.contains(".3.")) {
            return 3;
        } else if (lokaalnr.contains(".4.")) {
            return 4;
        } else if (lokaalnr.contains(".5.")) {
            return 5;
        } else {
            Lokaal.setError("Het etagenummer van: " + lokaalnr + ", bestaat niet in het CMI");
            Lokaal.setText("");
            return null;
        }
    }

    public String getLokaal (String lokaalnr, String lokaalsum){

        return lokaalnr.replace(lokaalsum, "");

    }

        });
    }

}
