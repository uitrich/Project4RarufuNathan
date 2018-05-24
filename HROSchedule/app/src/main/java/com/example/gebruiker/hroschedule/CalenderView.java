package com.example.gebruiker.hroschedule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import org.apache.log4j.chainsaw.Main;

import java.util.Calendar;
import java.util.Date;

public class CalenderView extends AppCompatActivity {
    TextView textView;
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        Button agendaitem1 = findViewById(R.id.agendaitem1);
        Button agendaitem2 = findViewById(R.id.agendaitem2);
        Button agendaitem3 = findViewById(R.id.agendaitem3);

        FloatingActionButton fabitem1 = findViewById(R.id.buttonagendaitem1);
        FloatingActionButton fabitem2 = findViewById(R.id.buttonagendaitem2);
        FloatingActionButton fabitem3 = findViewById(R.id.buttonagendaitem3);

        CharSequence agendaitem1Text = agendaitem1.getText();
        String agendaitem1String = agendaitem1Text.toString();

        CharSequence agendaitem2Text = agendaitem2.getText();
        String agendaitem2String = agendaitem2Text.toString();

        CharSequence agendaitem3Text = agendaitem3.getText();
        String agendaitem3String = agendaitem3Text.toString();


        //Date dt = new Date(getExtraData(MainActivity,"noteDate");
        //TextView textView = findViewById(R.id.datum);
        //textView.setText();
        Bundle bundle = getIntent().getExtras();
        Long date = bundle.getLong("CurrentDate");
        calendar = findViewById(R.id.Calendar);
        calendar.setDate(date);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                long selecteddate = calendar.getDate();
                Intent intent = new Intent();
                //schrijf hier de methode om afspraken aan te passen
            }
        });
    }
}
