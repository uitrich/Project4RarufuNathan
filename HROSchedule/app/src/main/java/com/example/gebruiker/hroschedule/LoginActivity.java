package com.example.gebruiker.hroschedule;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.opengl.Matrix;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.view.animation.AnimationUtils;

import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.util.Strings;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.net.wifi.WifiConfiguration.PairwiseCipher.strings;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    public int Unlock = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText lokaalnummer;
    private TextView textView;
    private Button button;
    private String Lokaalcode;
    private String gebouw;
    private String etage;
    private String lokaal;
    private Boolean unlockplattegrond = false;
    private String lokaalsum;
    private ImageView imageView;
    private Float scale = 1f;
    private ScaleGestureDetector SGD;
    private Matrix matrix;
    private PhotoViewAttacher PVA;



    //agenda

    private Button item1;
    private Button item2;
    private Button item3;

    //ext+
    private Boolean Dropdownenabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Spinner dropdownGebouw = findViewById(R.id.Spinner);
        String[] itemsGebouw = new String[]{"empty", "H.0.205", "H.0.304", "H.0.305" , "H.0.309", "H.0.319", "H.0.321", "H.0.405", "H.1.110", "H.1.112", "H.1.114", "H.1.204", "H.1.206", "H.1.306", "H.1.308", "H.1.312", "H.1.315", "H.1.316", "H.1.318", "H.1.403", "H.2.111", "H.2.112", "H.2.114", "H.2.204", "H.2.306", "H.2.307", "H.2.308", "H.2.312", "H.2.318", "H.2.403", "H.3.109", "H.3.111", "H.3.204", "H.3.206", "H.3.306", "H.3.307", "H.3.308", "H.3.312", "H.3.318", "H.3.319", "H.3.403", "H.3.405", "H.4.109", "H.4.111", "H.4.115", "H.4.204", "H.4.206", "H.4.306", "H.4.308", "H.4.312", "H.4.318", "H.4.403", "H.4.405", "H.5.109", "H.5.113", "H.5.204", "H.5.205", "H.5.208", "H.5.314", "H.5.401", "H.5.404",  "H.5.405","WD.00.001", "WD.00.004", "WD.00.013", "WD.00.018", "WD.00.026", "WD.00.034", "WD.01.003", "WD.01.016", "WD.01.019", "WD.01.021", "WD.02.002", "WD.02.016", "WD.02.019", "WD.02.021", "WD.03.001", "WD.03.005", "WD.03.013", "WD.03.019", "WD.03.021", "WD.03.023", "WD.03.028", "WD.03.033", "WD.04.001", "WD.04.002", "WD.04.005", "WD.04.016", "WD.04.020", "WD.04.022", "WD.05.001", "WD.05.002", "WD.05.005", "WD.05.013", "WD.05.018", "WD.05.021", "WD.05.027","WN.00.002", "WN.00.005", "WN.00.008", "WN.00.016", "WN.00.019", "WN.00.023", "WN.00.024", "WN.01.007", "WN.01.014", "WN.01.016", "WN.01.017", "WN.01.020", "WN.01.022", "WN.01.023", "WN.02.007", "WN.02.014", "WN.02.016", "WN.02.017", "WN.02.020", "WN.02.022", "WN.02.026", "WN.03.007", "WN.03.014", "WN.03.016", "WN.03.017", "WN.03.020", "WN.03.022", "WN.03.023", "WN.04.007", "WN.04.014", "WN.04.016", "WN.04.017", "WN.04.020", "WN.04.022", "WN.04.023", "WN.05.006", "WN.05.016", "WN.05.020", "WN.05.023", "WN.05.025", "WN.05.026"};
        ArrayAdapter<String> adapterGebouw = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, itemsGebouw);
        dropdownGebouw.setAdapter(adapterGebouw);


        lokaalnummer = findViewById(R.id.Lokaalnummer);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.tester);
        imageView = findViewById(R.id.imageView);
        PVA = new PhotoViewAttacher(imageView);
        dropdownGebouw.setVisibility(View.INVISIBLE);


        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(lokaalnummer.getText().toString());
                if (Dropdownenabled && !dropdownGebouw.getSelectedItem().toString().equals("empty")) {
                    Lokaalcode = dropdownGebouw.getSelectedItem().toString();

                }
                else {
                    Lokaalcode = lokaalnummer.getText().toString().toUpperCase();
                }
                checkExtras();
                if (Lokaalcode.contains("H")) {
                    //uitsnijder en splitter
                    gebouw = "H";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;

                    //update textView met informatie over het geselecteerde lokaal
                    String Adres = getGebouw(gebouw);
                    String settext ="Etage: " + etage + " | Gebouw: " + gebouw;
                    textView.setText(settext);

                } else if (Lokaalcode.contains("WD")) {
                    //uitsnijder en splitter
                    gebouw = "WD";
                    etage = getEtage(Lokaalcode);

                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;

                    //update textView met informatie over het geselecteerde lokaal
                    String adres = getGebouw(gebouw);
                    String settext ="Etage: " + etage + " | Gebouw: " + gebouw;
                    textView.setText(settext);
                }
                else if (Lokaalcode.contains("WN")) {
                    //uitsnijder en splitter
                    gebouw = "WN";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;

                    //update textView met informatie over het geselecteerde lokaal
                    String settext = "Etage: " + etage + " | Gebouw: " + gebouw;
                    textView.setText(settext);
                } else {
                    unlockplattegrond = false;
                    lokaalnummer.setError("de letter(s) aan het begin van "+ Lokaalcode +" zijn incorrect");
                    lokaalnummer.setText("");
                }

                String imagefile = gebouw + etage;
                //H Gebouw
                if (unlockplattegrond) {
                    imageView.setVisibility(View.VISIBLE);
                    if (imagefile.equals("H0") || imagefile.equals("H00")) {
                        if (lokaal.equals("205")){
                            imageView.setImageResource(R.drawable.h0205);
                        }
                        else if(lokaal.equals("304")){
                            imageView.setImageResource(R.drawable.h0304);
                        }
                        else if(lokaal.equals("305")){
                            imageView.setImageResource(R.drawable.h0305);
                        }
                        else if(lokaal.equals("309")){
                            imageView.setImageResource(R.drawable.h0309);
                        }
                        else if(lokaal.equals("310")){
                            imageView.setImageResource(R.drawable.h0310);
                        }
                        else if(lokaal.equals("319")){
                            imageView.setImageResource(R.drawable.h0319);
                        }
                        else if(lokaal.equals("321")){
                            imageView.setImageResource(R.drawable.h0321);
                        }
                        else if(lokaal.equals("405")){
                                imageView.setImageResource(R.drawable.h0405);
                        }
                        else {
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }
                    } else if (imagefile.equals("H1") || imagefile.equals("H01")) {
                        if(lokaal.equals("110")){
                            imageView.setImageResource(R.drawable.h1110);
                        }
                        else if(lokaal.equals("112")){
                            imageView.setImageResource(R.drawable.h1112);
                        }
                        else if(lokaal.equals("114")){
                            imageView.setImageResource(R.drawable.h1114);
                        }
                        else if(lokaal.equals("204")){
                            imageView.setImageResource(R.drawable.h1204);
                        }
                        else if(lokaal.equals("206")){
                            imageView.setImageResource(R.drawable.h1206);
                        }
                        else if(lokaal.equals("306")){
                            imageView.setImageResource(R.drawable.h1306);
                        }
                        else if(lokaal.equals("308")){
                            imageView.setImageResource(R.drawable.h1308);
                        }
                        else if(lokaal.equals("312")){
                            imageView.setImageResource(R.drawable.h1312);
                        }
                        else if(lokaal.equals("315")){
                            imageView.setImageResource(R.drawable.h1315);
                        }
                        else if(lokaal.equals("316")){
                            imageView.setImageResource(R.drawable.h1316);
                        }
                        else if(lokaal.equals("403")){
                            imageView.setImageResource(R.drawable.h1403);
                        }
                        else {
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("H2")|| imagefile.equals("H02")) {
                        if(lokaal.equals("111")){
                            imageView.setImageResource(R.drawable.h2111);
                        }
                        else if(lokaal.equals("112")){
                            imageView.setImageResource(R.drawable.h2112);
                        }
                        else if(lokaal.equals("114")){
                            imageView.setImageResource(R.drawable.h2114);
                        }
                        else if(lokaal.equals("204")){
                            imageView.setImageResource(R.drawable.h2204);
                        }
                        else if(lokaal.equals("306")){
                            imageView.setImageResource(R.drawable.h2306);
                        }
                        else if(lokaal.equals("307")){
                            imageView.setImageResource(R.drawable.h2307);
                        }
                        else if(lokaal.equals("308")){
                            imageView.setImageResource(R.drawable.h2308);
                        }
                        else if(lokaal.equals("312")){
                            imageView.setImageResource(R.drawable.h2312);
                        }
                        else if(lokaal.equals("318")){
                            imageView.setImageResource(R.drawable.h2318);
                        }
                        else if(lokaal.equals("403")){
                            imageView.setImageResource(R.drawable.h2403);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("H3")|| imagefile.equals("H03")){
                        if(lokaal.equals("109")){
                            imageView.setImageResource(R.drawable.h3109);
                        }
                        else if(lokaal.equals("111")){
                            imageView.setImageResource(R.drawable.h3111);
                        }
                        else if(lokaal.equals("204")){
                            imageView.setImageResource(R.drawable.h3204);
                        }
                        else if(lokaal.equals("206")){
                            imageView.setImageResource(R.drawable.h3206);
                        }
                        else if(lokaal.equals("306")){
                            imageView.setImageResource(R.drawable.h3306);
                        }
                        else if(lokaal.equals("307")){
                            imageView.setImageResource(R.drawable.h3307);
                        }
                        else if(lokaal.equals("308")){
                            imageView.setImageResource(R.drawable.h3308);
                        }
                        else if(lokaal.equals("312")){
                            imageView.setImageResource(R.drawable.h3312);
                        }
                        else if(lokaal.equals("318")){
                            imageView.setImageResource(R.drawable.h3318);
                        }
                        else if(lokaal.equals("319")){
                            imageView.setImageResource(R.drawable.h3319);
                        }
                        else if(lokaal.equals("403")){
                            imageView.setImageResource(R.drawable.h3403);
                        }
                        else if(lokaal.equals("405")){
                            imageView.setImageResource(R.drawable.h3405);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if(imagefile.equals("H4")|| imagefile.equals("H04")){
                        if (lokaal.equals("109")){
                            imageView.setImageResource(R.drawable.h4109);
                        }
                        else if (lokaal.equals("111")){
                            imageView.setImageResource(R.drawable.h4111);
                        }
                        else if (lokaal.equals("115")){
                            imageView.setImageResource(R.drawable.h4115);
                        }
                        else if (lokaal.equals("204")){
                            imageView.setImageResource(R.drawable.h4204);
                        }
                        else if (lokaal.equals("306")){
                            imageView.setImageResource(R.drawable.h4306);
                        }
                        else if (lokaal.equals("308")){
                            imageView.setImageResource(R.drawable.h4308);
                        }
                        else if (lokaal.equals("312")){
                            imageView.setImageResource(R.drawable.h4312);
                        }
                        else if (lokaal.equals("318")){
                            imageView.setImageResource(R.drawable.h4318);
                        }
                        else if (lokaal.equals("403")){
                            imageView.setImageResource(R.drawable.h4403);
                        }
                        else if (lokaal.equals("405")){
                            imageView.setImageResource(R.drawable.h4405);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }


                    } else if(imagefile.equals("H5")|| imagefile.equals("H05")){
                        if (lokaal.equals("109")){
                            imageView.setImageResource(R.drawable.h4109);
                        }
                        else if (lokaal.equals("113")){
                            imageView.setImageResource(R.drawable.h5113);
                        }
                        else if (lokaal.equals("204")){
                            imageView.setImageResource(R.drawable.h5204);
                        }
                        else if (lokaal.equals("205")){
                            imageView.setImageResource(R.drawable.h5205);
                        }
                        else if (lokaal.equals("208")){
                            imageView.setImageResource(R.drawable.h5208);
                        }
                        else if (lokaal.equals("314")){
                            imageView.setImageResource(R.drawable.h5314);
                        }
                        else if (lokaal.equals("401")){
                            imageView.setImageResource(R.drawable.h5401);
                        }
                        else if (lokaal.equals("404")){
                            imageView.setImageResource(R.drawable.h5404);
                        }
                        else if (lokaal.equals("405")){
                            imageView.setImageResource(R.drawable.h5405);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }



                    }
                    //WD gebouw
                    else if (imagefile.equals("WD0")|| imagefile.equals("WD00")) {
                        if(lokaal.equals("001")){
                            imageView.setImageResource(R.drawable.wd0001);
                        }
                        else if(lokaal.equals("004")){
                            imageView.setImageResource(R.drawable.wd0004);
                        }
                        else if(lokaal.equals("013")){
                            imageView.setImageResource(R.drawable.wd0013);
                        }
                        else if(lokaal.equals("018")){
                            imageView.setImageResource(R.drawable.wd0018);
                        }
                        else if(lokaal.equals("026")){
                            imageView.setImageResource(R.drawable.wd0026);
                        }
                        else if(lokaal.equals("034")){
                            imageView.setImageResource(R.drawable.wd0034);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD1") || imagefile.equals("WD01")) {
                        if(lokaal.equals("003")){
                            imageView.setImageResource(R.drawable.wd0103);
                        }
                        else if(lokaal.equals("003")){
                            imageView.setImageResource(R.drawable.wd0103);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wd0116);
                        }
                        else if(lokaal.equals("019")){
                            imageView.setImageResource(R.drawable.wd0119);
                        }
                        else if(lokaal.equals("021")){
                            imageView.setImageResource(R.drawable.wd0121);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD2") || imagefile.equals("WD02")) {
                        if(lokaal.equals("002")){
                            imageView.setImageResource(R.drawable.wd0202);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wd0216);
                        }
                        else if(lokaal.equals("019")){
                            imageView.setImageResource(R.drawable.wd0219);
                        }
                        else if(lokaal.equals("021")){
                            imageView.setImageResource(R.drawable.wd0221);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD3") || imagefile.equals("WD03")) {
                        if(lokaal.equals("001")){
                            imageView.setImageResource(R.drawable.wd0301);
                        }
                        else if(lokaal.equals("005")){
                            imageView.setImageResource(R.drawable.wd0305);
                        }
                        else if(lokaal.equals("013")){
                            imageView.setImageResource(R.drawable.wd0313);
                        }
                        else if(lokaal.equals("019")){
                            imageView.setImageResource(R.drawable.wd0319);
                        }
                        else if(lokaal.equals("021")){
                            imageView.setImageResource(R.drawable.wd0321);
                        }
                        else if(lokaal.equals("023")){
                            imageView.setImageResource(R.drawable.wd0323);
                        }
                        else if(lokaal.equals("028")){
                            imageView.setImageResource(R.drawable.wd0328);
                        }
                        else if(lokaal.equals("033")){
                            imageView.setImageResource(R.drawable.wd0333);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat");
                        }


                    } else if (imagefile.equals("WD4") || imagefile.equals("WD04")) {
                        if(lokaal.equals("001")){
                            imageView.setImageResource(R.drawable.wd0401);
                        }
                        else if(lokaal.equals("002")){
                            imageView.setImageResource(R.drawable.wd0402);
                        }
                        else if(lokaal.equals("005")){
                            imageView.setImageResource(R.drawable.wd0405);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wd0416);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wd0420);
                        }
                        else if(lokaal.equals("022")){
                            imageView.setImageResource(R.drawable.wd0422);
                        }
                        else {
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WD5") || imagefile.equals("WD05")) {
                        if(lokaal.equals("001")){
                            imageView.setImageResource(R.drawable.wd0501);
                        }
                        else if(lokaal.equals("002")){
                            imageView.setImageResource(R.drawable.wd0502);
                        }
                        else if(lokaal.equals("005")){
                            imageView.setImageResource(R.drawable.wd0505);
                        }
                        else if(lokaal.equals("013")){
                            imageView.setImageResource(R.drawable.wd0513);
                        }
                        else if(lokaal.equals("018")){
                            imageView.setImageResource(R.drawable.wd0518);
                        }
                        else if(lokaal.equals("021")){
                            imageView.setImageResource(R.drawable.wd0521);
                        }
                        else if(lokaal.equals("027")){
                            imageView.setImageResource(R.drawable.wd0527);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    }
                    //WN Gebouw
                    else if (imagefile.equals("WN0") || imagefile.equals("WN00")) {
                        if(lokaal.equals("005")){
                            imageView.setImageResource(R.drawable.wn0005);
                        }
                        else if(lokaal.equals("008")){
                            imageView.setImageResource(R.drawable.wn0008);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0016);
                        }
                        else if(lokaal.equals("019")){
                            imageView.setImageResource(R.drawable.wn0019);
                        }
                        else if(lokaal.equals("0023")){
                            imageView.setImageResource(R.drawable.wn0023);
                        }
                        else if(lokaal.equals("024")){
                            imageView.setImageResource(R.drawable.wn0024);
                        }



                    } else if (imagefile.equals("WN1") || imagefile.equals("WN01")) {
                        if(lokaal.equals("007")){
                            imageView.setImageResource(R.drawable.wn0107);
                        }
                        else if(lokaal.equals("014")){
                            imageView.setImageResource(R.drawable.wn0114);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0116);
                        }
                        else if(lokaal.equals("017")){
                            imageView.setImageResource(R.drawable.wn0117);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wn0120);
                        }
                        else if(lokaal.equals("022")){
                            imageView.setImageResource(R.drawable.wn0122);
                        }
                        else if(lokaal.equals("023")){
                            imageView.setImageResource(R.drawable.wn0123);

                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN2") || imagefile.equals("WN02")) {
                        if(lokaal.equals("007")){
                            imageView.setImageResource(R.drawable.wn0207);
                        }
                        else if(lokaal.equals("014")){
                            imageView.setImageResource(R.drawable.wn0214);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0216);
                        }
                        else if(lokaal.equals("017")){
                            imageView.setImageResource(R.drawable.wn0217);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wn0220);
                        }
                        else if(lokaal.equals("022")){
                            imageView.setImageResource(R.drawable.wn0222);
                        }
                        else if(lokaal.equals("026")){
                            imageView.setImageResource(R.drawable.wn0226);
                        }
                        else {
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN3") || imagefile.equals("WN03")) {
                        if(lokaal.equals("007")){
                            imageView.setImageResource(R.drawable.wn0307);
                        }
                        else if(lokaal.equals("014")){
                            imageView.setImageResource(R.drawable.wn0314);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0316);
                        }
                        else if(lokaal.equals("017")){
                            imageView.setImageResource(R.drawable.wn0317);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wn0320);
                        }
                        else if(lokaal.equals("022")){
                            imageView.setImageResource(R.drawable.wn0322);
                        }
                        else if(lokaal.equals("023")){
                            imageView.setImageResource(R.drawable.wn0323);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN4") || imagefile.equals("WN04")) {
                        if(lokaal.equals("007")){
                            imageView.setImageResource(R.drawable.wn0407);
                        }
                        else if(lokaal.equals("014")){
                            imageView.setImageResource(R.drawable.wn0414);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0416);
                        }
                        else if(lokaal.equals("017")){
                            imageView.setImageResource(R.drawable.wn0417);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wn0420);
                        }
                        else if(lokaal.equals("022")){
                            imageView.setImageResource(R.drawable.wn0422);
                        }
                        else if(lokaal.equals("023")){
                            imageView.setImageResource(R.drawable.wn0423);
                        }
                       else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN5") || imagefile.equals("WN05")) {
                        if(lokaal.equals("006")){
                            imageView.setImageResource(R.drawable.wn0506);
                        }
                        else if(lokaal.equals("016")){
                            imageView.setImageResource(R.drawable.wn0516);
                        }
                        else if(lokaal.equals("020")){
                            imageView.setImageResource(R.drawable.wn0520);
                        }
                        else if(lokaal.equals("023")){
                            imageView.setImageResource(R.drawable.wn0523);
                        }
                        else if(lokaal.equals("025")){
                            imageView.setImageResource(R.drawable.wn0525);
                        }
                        else if(lokaal.equals("026")){
                            imageView.setImageResource(R.drawable.wn0526);
                        }
                        else{
                            lokaalnummer.setError("Dit lokaal bestaat niet");
                        }

                    } else {
                        lokaalnummer.setError("er ging iets fout, ga naar de developers om dit na te gaan");
                    }


                }

            }
        });

    }

    public String getEtage (String lokaalnr){
        if (lokaalnr.contains(".0.")) {
            return "0";
        } else if (lokaalnr.contains(".00.")) {
            return "00";
        } else if (lokaalnr.contains(".1.")) {
            return "1";
        } else if (lokaalnr.contains(".01.")) {
            return "01";
        } else if (lokaalnr.contains(".2.")) {
            return "2";
        } else if (lokaalnr.contains(".02.")) {
            return "02";
        } else if (lokaalnr.contains(".3.")) {
            return "3";
        } else if (lokaalnr.contains(".03.")) {
            return "03";
        } else if (lokaalnr.contains(".4.")) {
            return "4";

        } else if (lokaalnr.contains(".04.")) {
            return "04";
        }
        else if (lokaalnr.contains(".5.")) {
            return "5";
        } else if (lokaalnr.contains(".05.")) {
            return "05";
        }

        else {
            lokaalnummer.setError("Het etagenummer van: " + lokaalnr + ", bestaat niet in het CMI");
            lokaalnummer.setText("");
            return null;
        }
    }

    public String getLokaal (String lokaalnr, String lokaalsum){

        return lokaalnr.replace(lokaalsum, "");

    }
    public void checkExtras () {
        String fug = "techlab WD.04.022 stadslab WN.00.019 bedrijfsbureau WN.00.008 kantine   studentbali servicebalie e&c H.5.113 greenhouse WN.05.006";
        if (lokaalnummer.getText().toString().toLowerCase().equals("techlab") || lokaalnummer.getText().toString().toLowerCase().equals("tech lab") ) {
            Lokaalcode = "WD.04.022";
        }
        if (lokaalnummer.getText().toString().toLowerCase().equals("stadslab") || lokaalnummer.getText().toString().toLowerCase().equals("stads lab") ) {
            Lokaalcode = "H.0.304";
        }
        if (lokaalnummer.getText().toString().toLowerCase().equals("bedrijfsbureau") || lokaalnummer.getText().toString().toLowerCase().equals("bedrijfs bureau") ) {
            Lokaalcode = "WN.00.008";
        }
        if (lokaalnummer.getText().toString().toLowerCase().equals("kantine") || lokaalnummer.getText().toString().toLowerCase().equals("keuken") ) {
            Lokaalcode = "H.0.110";
        }
        if (lokaalnummer.getText().toString().toLowerCase().equals("studentbalie") || lokaalnummer.getText().toString().toLowerCase().equals("student balie") ) {
            Lokaalcode = "H.0.305";
        }
        if (lokaalnummer.getText().toString().toLowerCase().equals("service balie") || lokaalnummer.getText().toString().toLowerCase().equals("service balie") || lokaalnummer.getText().toString().toLowerCase().equals("servicedesk") || lokaalnummer.getText().toString().toLowerCase().equals("service desk")) {
            Lokaalcode = "WD.04.022";
        }

    }
    public String getGebouw(String Gebouwnr) {
        if (Gebouwnr.equals("H")) {
            return "Wijnhaven 107";
        }
        if (Gebouwnr.equals("WD")) {
            return "Wijnhaven 103";
        }
        if (Gebouwnr.equals("WN")) {
            return "Wijnhaven 101";
        }
        else {
            return "";
        }
    }
    public String[] SetlokaalPos(String Gebouw,String Etage) {
        if (Gebouw.equals("H")) {
            if (Etage.equals("0")) {
                String[] DLokaal = new String[]{"205", "304", "305", "309", "310", "310", "319", "321", "405"};
                return DLokaal;
            }
            if (Etage.equals("1")) {
                String[] DLokaal = new String[]{"110", "112", "114", "204", "206", "306", "308", "312", "315", "318", "316", "403"};
                return DLokaal;
            }
            if (Etage.equals("2")) {
                String[] DLokaal = new String[]{"111", "112", "114", "204", "306", "307", "312", "318", "403"};
                return DLokaal;
            }
            if (Etage.equals("3")) {
                String[] DLokaal = new String[]{"109", "111", "204", "206", "306", "307", "308", "312", "318", "319", "403", "405"};
                return DLokaal;
            }
            if (Etage.equals("4")) {
                String[] Dlokaal = new String[]{"109", "111", "115", "204", "206", "306", "308", "312", "318", "403", "405", "109"};
                return Dlokaal;
            }
            if (Etage.equals("5")) {
                String[] Dlokaal = new String[]{"109", "113", "204", "205", "208", "314", "401", "404", "405"};
                return Dlokaal;
            }
            else {
                String[] DLokaal = new String[]{"error 404 Etage not found"};
                return DLokaal;
            }

        }
        else if (Gebouw.equals("WD")){
            if (Etage.equals("0")) {
                String[] DLokaal = new String[]{"001", "004", "013", "018", "034"};
                return DLokaal;
            }
            if (Etage.equals("1")) {
                String[] DLokaal = new String[]{"003", "016", "019", "021"};
                return DLokaal;
            }
            if (Etage.equals("2")) {
                String[] DLokaal = new String[]{"002", "016", "019", "021"};
                return DLokaal;
            }
            if (Etage.equals("3")) {
                String[] DLokaal = new String[]{"001", "005", "013", "019", "021", "023", "028", "033"};
                return DLokaal;
            }
            if (Etage.equals("4")) {
                String[] Dlokaal = new String[]{"001", "002", "005", "016", "020", "022"};
                return Dlokaal;
            }
            if (Etage.equals("5")) {
                String[] Dlokaal = new String[]{"001", "002", "005", "013", "018", "021", "027"};
                return Dlokaal;
            }
            else {
                String[] DLokaal = new String[]{"error 404 Etage not found"};
                return DLokaal;
            }
        }
        else if (Gebouw.equals("WN")){
            if (Etage.equals("0")) {
                String[] DLokaal = new String[]{"002", "005", "008", "016", "019", "023", "024"};
                return DLokaal;
            }
            if (Etage.equals("1")) {
                String[] DLokaal = new String[]{"007", "014", "016", "017", "020", "022", "023"};
                return DLokaal;
            }
            if (Etage.equals("2")) {
                String[] DLokaal = new String[]{"007", "014", "016", "017", "020", "022", "026"};
                return DLokaal;
            }
            if (Etage.equals("3")) {
                String[] DLokaal = new String[]{"007", "014", "016", "017", "020", "022", "023"};
                return DLokaal;
            }
            if (Etage.equals("4")) {
                String[] Dlokaal = new String[]{"007", "014", "016", "017", "020", "022"};
                return Dlokaal;
            }
            if (Etage.equals("5")) {
                String[] Dlokaal = new String[]{"006", "016", "020", "023", "025", "026"};
                return Dlokaal;
            }
            else {
                String[] DLokaal = new String[]{"error 404 Etage not found"};
                return DLokaal;
            }
        }
        else {
            String[] error = new String[]{"error 405 Gebouw not found"};
            return error;
        }
    }

}


