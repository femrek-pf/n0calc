package com.eksioglu.faruk.a2asal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Bulma extends AppCompatActivity {

    static int altLimit;
    static int ustLimit;
    static int alt;
    static ArrayList<String> arrayList;

    TextView sonuc;
    EditText altl;
    EditText ustl;
    Button btn;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulma);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sonuc=findViewById(R.id.sonuc);
        altl=findViewById(R.id.altl);
        ustl=findViewById(R.id.ustl);
        btn=findViewById(R.id.kontrolb);
        constraintLayout = findViewById(R.id.bulmalayout);
        arrayList = new ArrayList<>();

        Intent i = getIntent();
        setMyTheme(i.getIntExtra("tema", 0));
    }

    public void kontrolet(View view){
        if (altl.getText().toString().length() < 10) {
            try{
                altLimit = Integer.parseInt(altl.getText().toString());
            }catch (Exception e){
                altLimit = -1;
            }
        }else{
            altLimit = -2;
        }

        if (ustl.getText().toString().length() < 10) {
            try{
                ustLimit = Integer.parseInt(ustl.getText().toString());
            }catch (Exception e){
                ustLimit = -1;
            }
        }else {
            ustLimit = -2;
        }

        alt=altLimit;

        InputMethodManager immalt = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        immalt.hideSoftInputFromWindow(altl.getWindowToken(), 0);

        InputMethodManager immust = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        immust.hideSoftInputFromWindow(ustl.getWindowToken(), 0);

        sonuc.setText(getString(R.string.bekleyiniz));

        if (ustLimit > 30000  &&  ustLimit-altLimit > 5000){
            sonuc.setText(getString(R.string.daha_kucuk_aralik));
        }else {
            DoProgress doProgress = new DoProgress();
            doProgress.execute();
        }
    }

    private class DoProgress extends AsyncTask<Void, Integer, Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            kontrol(altLimit, ustLimit, alt);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (arrayList.size() == 0){
                sonuc.setText(getString(R.string.asal_yoktur));
            }
            else {
                if (arrayList.get(0).toString().equalsIgnoreCase("-2")){
                    sonuc.setText(getString(R.string.deger_giriniz));
                }else if (arrayList.get(0).toString().equalsIgnoreCase("-4")){
                    sonuc.setText(getString(R.string.daha_kucuk_deger));
                }else if (arrayList.get(0).toString().equalsIgnoreCase("-1")){
                    sonuc.setText(getString(R.string.kucuk_degil));
                }else if (arrayList.get(0).toString().equalsIgnoreCase("-3")){
                    sonuc.setText(R.string.asal_yoktur);
                }else {
                    sonuc.setText(getString(R.string.asal_sayilar));
                    for (int i = 0; i < arrayList.size(); i++) {
                        sonuc.setText("" + sonuc.getText() + "\n" + arrayList.get(i));
                    }
                }
            }
        }

        public void kontrol(int altLimit, int ustLimit, int alt){
            ArrayList<String> asallar=new ArrayList<>();

            int o;

            if (altLimit == -1  ||  ustLimit == -1){
                asallar.add("-2");
            }else if (altLimit == -2 || ustLimit == -2){
                asallar.add("-4");
            }
            else if (altLimit >= ustLimit){
                asallar.add("-1");
            }
            else {
                if (altLimit < 2){
                    if (ustLimit > 2){
                        alt=2;
                    }else{
                        asallar.add("-3");
                    }
                }
                int progress=ustLimit-alt;
                for (int i = alt; i < ustLimit; i++) {
                    o = 0;
                    for (int a = i / 2; a > 1; a--) {
                        if (i % a == 0) o++;
                        else continue;
                    }
                    if (o == 0) {
                        asallar.add(Integer.toString(i));
                    }
                }
            }
            arrayList = asallar;
        }
    }

    public void setMyTheme(int isDark){
        if (isDark == 1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            sonuc.setTextColor(getResources().getColor(R.color.colorDarkText));

            altl.setTextColor(getResources().getColor(R.color.colorLight));
            ustl.setTextColor(getResources().getColor(R.color.colorLight));
            altl.setHintTextColor(getResources().getColor(R.color.colorDarkText));
            ustl.setHintTextColor(getResources().getColor(R.color.colorDarkText));

            btn.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));
            btn.setTextColor(getResources().getColor(R.color.colorDarkButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryDark));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryDark));
            }
        }
        else{
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
            sonuc.setTextColor(getResources().getColor(R.color.colorLightText));

            altl.setTextColor(getResources().getColor(R.color.colorDark));
            ustl.setTextColor(getResources().getColor(R.color.colorDark));
            altl.setHintTextColor(getResources().getColor(R.color.colorLightText));
            ustl.setHintTextColor(getResources().getColor(R.color.colorLightText));

            btn.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
            btn.setTextColor(getResources().getColor(R.color.colorLightButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryLight));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }
}
