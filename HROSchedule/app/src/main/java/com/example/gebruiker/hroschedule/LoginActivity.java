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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;


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
    private Integer etage;
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

    //ext
    private String ExtVaknaam;
    private String ExtLokaal;
    private Boolean ExtItemMade = false;
    private Boolean Agendaitemselected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        lokaalnummer = findViewById(R.id.Lokaalnummer);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.tester);
        imageView = findViewById(R.id.imageView);
        PVA = new PhotoViewAttacher(imageView);


        ExtItemMade = false;

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(lokaalnummer.getText().toString());
                Lokaalcode = lokaalnummer.getText().toString();
                if (Lokaalcode.contains("H")) {
                    gebouw = "H";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;
                } else if (Lokaalcode.contains("WD")) {
                    gebouw = "WD";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;
                } else if (Lokaalcode.contains("WN")) {
                    gebouw = "WN";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + "." + etage + ".";
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    unlockplattegrond = true;
                } else {
                    unlockplattegrond = false;
                    lokaalnummer.setError("de letter(s) aan het begin van "+ Lokaalcode +" zijn incorrect");
                    lokaalnummer.setText("");
                }

                String imagefile = gebouw + etage;
                //H Gebouw
                if (unlockplattegrond) {
                    imageView.setVisibility(View.VISIBLE);
                    if (imagefile.equals("H0")) {
                        if (lokaal.equals("H.0.205")){
                            imageView.setImageResource(R.drawable.h0205);
                        }
                        else if(lokaal.equals("H.0.304")){
                            imageView.setImageResource(R.drawable.h0304);
                        }
                        else if(lokaal.equals("H.0.305")){
                            imageView.setImageResource(R.drawable.h0305);
                        }
                        else if(lokaal.equals("H.0.309")){
                            imageView.setImageResource(R.drawable.h0309);
                        }
                        else if(lokaal.equals("H.0.310")){
                            imageView.setImageResource(R.drawable.h0310);
                        }
                        else if(lokaal.equals("H.0.319")){
                            imageView.setImageResource(R.drawable.h0319);
                        }
                        else if(lokaal.equals("H.0.321")){
                            imageView.setImageResource(R.drawable.h0321);
                        }
                        else if(lokaal.equals("H.0.405")){
                                imageView.setImageResource(R.drawable.h0405);
                        }
                        else {
                            button.setError("Dit lokaal bestaat niet");
                        }
                    } else if (imagefile.equals("H1")) {
                        if(lokaal.equals("H.1.110")){
                            imageView.setImageResource(R.drawable.h1110);
                        }
                        else if(lokaal.equals("H.1.112")){
                            imageView.setImageResource(R.drawable.h1112);
                        }
                        else if(lokaal.equals("H.1.114")){
                            imageView.setImageResource(R.drawable.h1114);
                        }
                        else if(lokaal.equals("H.1.204")){
                            imageView.setImageResource(R.drawable.h1204);
                        }
                        else if(lokaal.equals("H.1.206")){
                            imageView.setImageResource(R.drawable.h1206);
                        }
                        else if(lokaal.equals("H.1.306")){
                            imageView.setImageResource(R.drawable.h1306);
                        }
                        else if(lokaal.equals("H.1.308")){
                            imageView.setImageResource(R.drawable.h1308);
                        }
                        else if(lokaal.equals("H.1.312")){
                            imageView.setImageResource(R.drawable.h1312);
                        }
                        else if(lokaal.equals("H.1.315")){
                            imageView.setImageResource(R.drawable.h1315);
                        }
                        else if(lokaal.equals("H.1.316")){
                            imageView.setImageResource(R.drawable.h1316);
                        }
                        else if(lokaal.equals("H.1.403")){
                            imageView.setImageResource(R.drawable.h1403);
                        }
                        else {
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("H2")) {
                        if(lokaal.equals("H.2.111")){
                            imageView.setImageResource(R.drawable.h2111);
                        }
                        else if(lokaal.equals("H.2.112")){
                            imageView.setImageResource(R.drawable.h2112);
                        }
                        else if(lokaal.equals("H.2.114")){
                            imageView.setImageResource(R.drawable.h2114);
                        }
                        else if(lokaal.equals("H.2.204")){
                            imageView.setImageResource(R.drawable.h2204);
                        }
                        else if(lokaal.equals("H.2.306")){
                            imageView.setImageResource(R.drawable.h2306);
                        }
                        else if(lokaal.equals("H.2.307")){
                            imageView.setImageResource(R.drawable.h2307);
                        }
                        else if(lokaal.equals("H.2.308")){
                            imageView.setImageResource(R.drawable.h2308);
                        }
                        else if(lokaal.equals("H.2.312")){
                            imageView.setImageResource(R.drawable.h2312);
                        }
                        else if(lokaal.equals("H.2.318")){
                            imageView.setImageResource(R.drawable.h2318);
                        }
                        else if(lokaal.equals("H.2.403")){
                            imageView.setImageResource(R.drawable.h2403);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("H3")){
                        if(lokaal.equals("H.3.109")){
                            imageView.setImageResource(R.drawable.h3109);
                        }
                        else if(lokaal.equals("H.3.111")){
                            imageView.setImageResource(R.drawable.h3111);
                        }
                        else if(lokaal.equals("H.3.204")){
                            imageView.setImageResource(R.drawable.h3204);
                        }
                        else if(lokaal.equals("H.3.206")){
                            imageView.setImageResource(R.drawable.h3206);
                        }
                        else if(lokaal.equals("H.3.306")){
                            imageView.setImageResource(R.drawable.h3306);
                        }
                        else if(lokaal.equals("H.3.307")){
                            imageView.setImageResource(R.drawable.h3307);
                        }
                        else if(lokaal.equals("H.3.308")){
                            imageView.setImageResource(R.drawable.h3308);
                        }
                        else if(lokaal.equals("H.3.312")){
                            imageView.setImageResource(R.drawable.h3312);
                        }
                        else if(lokaal.equals("H.3.318")){
                            imageView.setImageResource(R.drawable.h3318);
                        }
                        else if(lokaal.equals("H.3.319")){
                            imageView.setImageResource(R.drawable.h3319);
                        }
                        else if(lokaal.equals("H.3.403")){
                            imageView.setImageResource(R.drawable.h3403);
                        }
                        else if(lokaal.equals("H.3.405")){
                            imageView.setImageResource(R.drawable.h3405);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if(imagefile.equals("H4")){
                        if (lokaal.equals("H.4.109")){
                            imageView.setImageResource(R.drawable.h4109);
                        }
                        else if (lokaal.equals("H.4.111")){
                            imageView.setImageResource(R.drawable.h4111);
                        }
                        else if (lokaal.equals("H.4.115")){
                            imageView.setImageResource(R.drawable.h4115);
                        }
                        else if (lokaal.equals("H.4.204")){
                            imageView.setImageResource(R.drawable.h4204);
                        }
                        else if (lokaal.equals("H.4.306")){
                            imageView.setImageResource(R.drawable.h4306);
                        }
                        else if (lokaal.equals("H.4.308")){
                            imageView.setImageResource(R.drawable.h4308);
                        }
                        else if (lokaal.equals("H.4.312")){
                            imageView.setImageResource(R.drawable.h4312);
                        }
                        else if (lokaal.equals("H.4.318")){
                            imageView.setImageResource(R.drawable.h4318);
                        }
                        else if (lokaal.equals("H.4.403")){
                            imageView.setImageResource(R.drawable.h4403);
                        }
                        else if (lokaal.equals("H.4.405")){
                            imageView.setImageResource(R.drawable.h4405);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }


                    } else if(imagefile.equals("H5")){
                        if (lokaal.equals("H.5.109")){
                            imageView.setImageResource(R.drawable.h4109);
                        }
                        else if (lokaal.equals("H.5.113")){
                            imageView.setImageResource(R.drawable.h5113);
                        }
                        else if (lokaal.equals("H.5.204")){
                            imageView.setImageResource(R.drawable.h5204);
                        }
                        else if (lokaal.equals("H.5.205")){
                            imageView.setImageResource(R.drawable.h5205);
                        }
                        else if (lokaal.equals("H.5.208")){
                            imageView.setImageResource(R.drawable.h5208);
                        }
                        else if (lokaal.equals("H.5.314")){
                            imageView.setImageResource(R.drawable.h5314);
                        }
                        else if (lokaal.equals("H.5.401")){
                            imageView.setImageResource(R.drawable.h5401);
                        }
                        else if (lokaal.equals("H.5.404")){
                            imageView.setImageResource(R.drawable.h5404);
                        }
                        else if (lokaal.equals("H.5.405")){
                            imageView.setImageResource(R.drawable.h5405);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }



                    }
                    //WD gebouw
                    else if (imagefile.equals("WD0")) {
                        if(lokaal.equals("WD.00.001")){
                            imageView.setImageResource(R.drawable.wd0001);
                        }
                        else if(lokaal.equals("WD.00.004")){
                            imageView.setImageResource(R.drawable.wd0004);
                        }
                        else if(lokaal.equals("WD.00.013")){
                            imageView.setImageResource(R.drawable.wd0013);
                        }
                        else if(lokaal.equals("WD.00.018")){
                            imageView.setImageResource(R.drawable.wd0018);
                        }
                        else if(lokaal.equals("WD.00.026")){
                            imageView.setImageResource(R.drawable.wd0026);
                        }
                        else if(lokaal.equals("WD.00.034")){
                            imageView.setImageResource(R.drawable.wd0034);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD1")) {
                        if(lokaal.equals("WD.01.003")){
                            imageView.setImageResource(R.drawable.wd0103);
                        }
                        else if(lokaal.equals("WD.01.003")){
                            imageView.setImageResource(R.drawable.wd0103);
                        }
                        else if(lokaal.equals("WD.01.016")){
                            imageView.setImageResource(R.drawable.wd0116);
                        }
                        else if(lokaal.equals("WD.01.019")){
                            imageView.setImageResource(R.drawable.wd0119);
                        }
                        else if(lokaal.equals("WD.01.021")){
                            imageView.setImageResource(R.drawable.wd0121);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD2")) {
                        if(lokaal.equals("WD.02.002")){
                            imageView.setImageResource(R.drawable.wd0202);
                        }
                        else if(lokaal.equals("WD.02.016")){
                            imageView.setImageResource(R.drawable.wd0216);
                        }
                        else if(lokaal.equals("WD.02.019")){
                            imageView.setImageResource(R.drawable.wd0219);
                        }
                        else if(lokaal.equals("WD.02.021")){
                            imageView.setImageResource(R.drawable.wd0221);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }


                    } else if (imagefile.equals("WD3")) {
                        if(lokaal.equals("WD.03.001")){
                            imageView.setImageResource(R.drawable.wd0301);
                        }
                        else if(lokaal.equals("WD.03.005")){
                            imageView.setImageResource(R.drawable.wd0305);
                        }
                        else if(lokaal.equals("WD.03.013")){
                            imageView.setImageResource(R.drawable.wd0313);
                        }
                        else if(lokaal.equals("WD.03.019")){
                            imageView.setImageResource(R.drawable.wd0319);
                        }
                        else if(lokaal.equals("WD.03.021")){
                            imageView.setImageResource(R.drawable.wd0321);
                        }
                        else if(lokaal.equals("WD.03.023")){
                            imageView.setImageResource(R.drawable.wd0323);
                        }
                        else if(lokaal.equals("WD.03.028")){
                            imageView.setImageResource(R.drawable.wd0328);
                        }
                        else if(lokaal.equals("WD.03.033")){
                            imageView.setImageResource(R.drawable.wd0333);
                        }
                        else{
                            button.setError("Dit lokaal bestaat");
                        }


                    } else if (imagefile.equals("WD4")) {
                        if(lokaal.equals("WD.04.001")){
                            imageView.setImageResource(R.drawable.wd0401);
                        }
                        else if(lokaal.equals("WD.04.002")){
                            imageView.setImageResource(R.drawable.wd0402);
                        }
                        else if(lokaal.equals("WD.04.005")){
                            imageView.setImageResource(R.drawable.wd0405);
                        }
                        else if(lokaal.equals("WD.04.016")){
                            imageView.setImageResource(R.drawable.wd0416);
                        }
                        else if(lokaal.equals("WD.04.020")){
                            imageView.setImageResource(R.drawable.wd0420);
                        }
                        else if(lokaal.equals("WD.04.022")){
                            imageView.setImageResource(R.drawable.wd0422);
                        }
                        else {
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WD5")) {
                        if(lokaal.equals("WD.05.001")){
                            imageView.setImageResource(R.drawable.wd0501);
                        }
                        else if(lokaal.equals("WD.05.002")){
                            imageView.setImageResource(R.drawable.wd0502);
                        }
                        else if(lokaal.equals("WD.05.005")){
                            imageView.setImageResource(R.drawable.wd0505);
                        }
                        else if(lokaal.equals("WD.05.013")){
                            imageView.setImageResource(R.drawable.wd0513);
                        }
                        else if(lokaal.equals("WD.05.018")){
                            imageView.setImageResource(R.drawable.wd0518);
                        }
                        else if(lokaal.equals("WD.05.021")){
                            imageView.setImageResource(R.drawable.wd0521);
                        }
                        else if(lokaal.equals("WD.05.027")){
                            imageView.setImageResource(R.drawable.wd0527);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    }
                    //WN Gebouw
                    else if (imagefile.equals("WN0")) {
                        if(lokaal.equals("WN.00.005")){
                            imageView.setImageResource(R.drawable.wn0005);
                        }
                        else if(lokaal.equals("WN.00.008")){
                            imageView.setImageResource(R.drawable.wn0008);
                        }
                        else if(lokaal.equals("WN.00.016")){
                            imageView.setImageResource(R.drawable.wn0016);
                        }
                        else if(lokaal.equals("WN.00.019")){
                            imageView.setImageResource(R.drawable.wn0019);
                        }
                        else if(lokaal.equals("WN.00.0023")){
                            imageView.setImageResource(R.drawable.wn0023);
                        }
                        else if(lokaal.equals("WN.00.024")){
                            imageView.setImageResource(R.drawable.wn0024);
                        }



                    } else if (imagefile.equals("WN1")) {
                        if(lokaal.equals("WN.01.007")){
                            imageView.setImageResource(R.drawable.wn0107);
                        }
                        else if(lokaal.equals("WN.01.014")){
                            imageView.setImageResource(R.drawable.wn0114);
                        }
                        else if(lokaal.equals("WN.01.016")){
                            imageView.setImageResource(R.drawable.wn0116);
                        }
                        else if(lokaal.equals("WN.01.017")){
                            imageView.setImageResource(R.drawable.wn0117);
                        }
                        else if(lokaal.equals("WN.01.020")){
                            imageView.setImageResource(R.drawable.wn0120);
                        }
                        else if(lokaal.equals("WN.01.022")){
                            imageView.setImageResource(R.drawable.wn0122);
                        }
                        else if(lokaal.equals("WN.01.023")){
                            imageView.setImageResource(R.drawable.wn0123);

                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN2")) {
                        if(lokaal.equals("WN.02.007")){
                            imageView.setImageResource(R.drawable.wn0207);
                        }
                        else if(lokaal.equals("WN.02.014")){
                            imageView.setImageResource(R.drawable.wn0214);
                        }
                        else if(lokaal.equals("WN.02.016")){
                            imageView.setImageResource(R.drawable.wn0216);
                        }
                        else if(lokaal.equals("WN.02.017")){
                            imageView.setImageResource(R.drawable.wn0217);
                        }
                        else if(lokaal.equals("WN.02.020")){
                            imageView.setImageResource(R.drawable.wn0220);
                        }
                        else if(lokaal.equals("WN.02.022")){
                            imageView.setImageResource(R.drawable.wn0222);
                        }
                        else if(lokaal.equals("WN.02.026")){
                            imageView.setImageResource(R.drawable.wn0226);
                        }
                        else {
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN3")) {
                        if(lokaal.equals("WN.03.007")){
                            imageView.setImageResource(R.drawable.wn0307);
                        }
                        else if(lokaal.equals("WN.03.014")){
                            imageView.setImageResource(R.drawable.wn0314);
                        }
                        else if(lokaal.equals("WN.03.016")){
                            imageView.setImageResource(R.drawable.wn0316);
                        }
                        else if(lokaal.equals("WN.03.017")){
                            imageView.setImageResource(R.drawable.wn0317);
                        }
                        else if(lokaal.equals("WN.03.020")){
                            imageView.setImageResource(R.drawable.wn0320);
                        }
                        else if(lokaal.equals("WN.03.022")){
                            imageView.setImageResource(R.drawable.wn0322);
                        }
                        else if(lokaal.equals("WN.03.023")){
                            imageView.setImageResource(R.drawable.wn0323);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN4")) {
                        if(lokaal.equals("WN.04.007")){
                            imageView.setImageResource(R.drawable.wn0407);
                        }
                        else if(lokaal.equals("WN.04.014")){
                            imageView.setImageResource(R.drawable.wn0414);
                        }
                        else if(lokaal.equals("WN.04.016")){
                            imageView.setImageResource(R.drawable.wn0416);
                        }
                        else if(lokaal.equals("WN.04.017")){
                            imageView.setImageResource(R.drawable.wn0417);
                        }
                        else if(lokaal.equals("WN.04.020")){
                            imageView.setImageResource(R.drawable.wn0420);
                        }
                        else if(lokaal.equals("WN.04.022")){
                            imageView.setImageResource(R.drawable.wn0422);
                        }
                        else if(lokaal.equals("WN.04.023")){
                            imageView.setImageResource(R.drawable.wn0423);
                        }
                       else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else if (imagefile.equals("WN5")) {
                        if(lokaal.equals("WN.05.006")){
                            imageView.setImageResource(R.drawable.wn0506);
                        }
                        else if(lokaal.equals("WN.05.016")){
                            imageView.setImageResource(R.drawable.wn0516);
                        }
                        else if(lokaal.equals("WN.05.020")){
                            imageView.setImageResource(R.drawable.wn0520);
                        }
                        else if(lokaal.equals("WN.05.023")){
                            imageView.setImageResource(R.drawable.wn0523);
                        }
                        else if(lokaal.equals("WN.05.025")){
                            imageView.setImageResource(R.drawable.wn0525);
                        }
                        else if(lokaal.equals("WN.05.026")){
                            imageView.setImageResource(R.drawable.wn0526);
                        }
                        else{
                            button.setError("Dit lokaal bestaat niet");
                        }

                    } else {
                        button.setError("er ging iets fout, ga naar de developers om dit na te gaan");
                    }


                }

            }
        });

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
            lokaalnummer.setError("Het etagenummer van: " + lokaalnr + ", bestaat niet in het CMI");
            lokaalnummer.setText("");
            return null;
        }
    }

    public String getLokaal (String lokaalnr, String lokaalsum){

        return lokaalnr.replace(lokaalsum, "");

    }

}


