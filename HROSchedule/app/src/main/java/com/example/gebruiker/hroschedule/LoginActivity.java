package com.example.gebruiker.hroschedule;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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

import java.util.ArrayList;
import java.util.List;
import net.fortuna.ical4j.model.*;

import static android.Manifest.permission.READ_CONTACTS;

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
    private Boolean ExtItemMade;
    private Boolean Agendaitemselected = true;

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

        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        ExtItemMade = false;

        item1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Agenda.class);
                intent.putExtra("itemid", "1");
                startActivity(intent);
                ExtItemMade = true;
            }
        });
        item2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Agenda.class);
                intent.putExtra("itemid", "2");
                startActivity(intent);
                ExtItemMade = true;
            }
        });
        item3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Agenda.class);
                intent.putExtra("itemid", "3");
                startActivity(intent);
                ExtItemMade = true;
            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(lokaalnummer.getText().toString());
                Lokaalcode = lokaalnummer.getText().toString();
                if (Lokaalcode.contains("H")) {
                    gebouw = "H";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    checkAgendaItem();
                    unlockplattegrond = true;
                } else if (Lokaalcode.contains("WD")) {
                    gebouw = "WD";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    checkAgendaItem();
                    unlockplattegrond = true;
                } else if (Lokaalcode.contains("WN")) {
                    gebouw = "WN";
                    etage = getEtage(Lokaalcode);
                    lokaalsum = gebouw + etage;
                    lokaal = getLokaal(Lokaalcode, lokaalsum);
                    checkAgendaItem();
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
                        imageView.setImageResource(R.drawable.h0);
                    } else if (imagefile.equals("H1")) {
                        imageView.setImageResource(R.drawable.h1);
                    } else if (imagefile.equals("H2")) {
                        imageView.setImageResource(R.drawable.h2);
                    }
                    //WD gebouw
                    else if (imagefile.equals("WD0")) {
                        imageView.setImageResource(R.drawable.wd0);
                    } else if (imagefile.equals("WD1")) {
                        imageView.setImageResource(R.drawable.wd1);
                    } else if (imagefile.equals("WD2")) {
                        imageView.setImageResource(R.drawable.wd2);
                    } else if (imagefile.equals("WD3")) {
                        imageView.setImageResource(R.drawable.wd3);
                    } else if (imagefile.equals("WD4")) {
                        imageView.setImageResource(R.drawable.wd4);
                    } else if (imagefile.equals("WD5")) {
                        imageView.setImageResource(R.drawable.wd5);
                    }
                    //WD Gebouw
                    else if (imagefile.equals("WN0")) {
                        imageView.setImageResource(R.drawable.wn0);
                    } else if (imagefile.equals("WN1")) {
                        imageView.setImageResource(R.drawable.wn1);
                    } else if (imagefile.equals("WN2")) {
                        imageView.setImageResource(R.drawable.wn2);
                    } else if (imagefile.equals("WN3")) {
                        imageView.setImageResource(R.drawable.wn3);
                    } else if (imagefile.equals("WN4")) {
                        imageView.setImageResource(R.drawable.wn4);
                    } else if (imagefile.equals("WN5")) {
                        imageView.setImageResource(R.drawable.wn5);
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
    //agenda functie
    public void checkAgendaItem() {
        if (Agendaitemselected){
            Bundle bundle = getIntent().getExtras();
            Lokaalcode = bundle.getString("Lokaal");
            textView.setText(Lokaalcode);
            if (!bundle.getString("Lokaal").isEmpty() && !bundle.getString("Vaknaam").isEmpty()) {
                lokaal = bundle.getString("Lokaal");
                if (bundle.getString("itemid").equals("1")) {
                    item1.setText(bundle.getString("Vaknaam"));
                }
                else if (bundle.getString("itemid").equals("2")) {
                    item2.setText(bundle.getString("Vaknaam"));
                }
                else if (bundle.getString("itemid").equals("3")) {
                    item3.setText(bundle.getString("Vaknaam"));
                }
            }
        }
    }
}


