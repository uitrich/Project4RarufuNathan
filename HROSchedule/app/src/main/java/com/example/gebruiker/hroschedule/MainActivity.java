package com.example.gebruiker.hroschedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView theDate;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theDate = findViewById(R.id.date);
        final Button button = findViewById(R.id.Agenda);
        Calendar calendar = Calendar.getInstance();


        int hours = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        final Date currentdate = calendar.getTime();
        String currenttimestring = hours + "-" + month + "-" + year;
        theDate.setText(currenttimestring);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderView.class);
                intent.putExtra("CurrentDate", currentdate.getTime());
                startActivity(intent);

            }
        });
    }
}


