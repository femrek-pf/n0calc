package com.eksioglu.faruk.a2asal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import java.util.Collections;

public class OrtakBolen extends AppCompatActivity {

    EditText in1;
    EditText in2;
    TextView bolenler;
    Button btn;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortak_bolen);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        in1 = findViewById(R.id.input1);
        in2 = findViewById(R.id.input2);
        btn = findViewById(R.id.button2);
        bolenler = findViewById(R.id.bolenler);
        constraintLayout=findViewById(R.id.ortaklayout);

        Intent i = getIntent();
        setMyTheme(i.getIntExtra("tema", 0));

    }

    public void setMyTheme(int isDark){
        if (isDark == 1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            bolenler.setTextColor(getResources().getColor(R.color.colorDarkText));

            in1.setTextColor(getResources().getColor(R.color.colorLight));
            in2.setTextColor(getResources().getColor(R.color.colorLight));
            in1.setHintTextColor(getResources().getColor(R.color.colorDarkText));
            in2.setHintTextColor(getResources().getColor(R.color.colorDarkText));

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
            bolenler.setTextColor(getResources().getColor(R.color.colorLightText));

            in1.setTextColor(getResources().getColor(R.color.colorDark));
            in2.setTextColor(getResources().getColor(R.color.colorDark));
            in1.setHintTextColor(getResources().getColor(R.color.colorLightText));
            in2.setHintTextColor(getResources().getColor(R.color.colorLightText));

            btn.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
            btn.setTextColor(getResources().getColor(R.color.colorLightButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryLight));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }

    public void konetrolEt(View view){
        islemler();
    }

    public void islemler(){
        InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(in1.getWindowToken(), 0);

        InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm2.hideSoftInputFromWindow(in2.getWindowToken(), 0);
        int int1;
        int int2;

        if (in1.getText().toString().length() < 10) {
            try{
                int1 = Integer.parseInt(in1.getText().toString());
            }catch (Exception e){
                int1 = -1;
            }
        }else{
            int1 = -2;
        }

        if (in2.getText().toString().length() < 10) {
            try{
                int2 = Integer.parseInt(in2.getText().toString());
            }catch (Exception e){
                int2 = -1;
            }
        }else {
            int2 = -2;
        }

        //try {
        //    int1 = Integer.parseInt(in1.getText().toString());
        //    int2 = Integer.parseInt(in2.getText().toString());
        //}catch (Exception e){
        //    int1 = -1;
        //    int2 = -1;
        //}

        boolean isReady=true;
        boolean isEqual=false;
        if (int1 == -1 || int2 == -1){
            bolenler.setText(getString(R.string.sayi_giriniz));
            isReady = false;
        }else if (int1 == -2 || int2 == -2){
            bolenler.setText(getString(R.string.daha_kucuk_sayi));
            isReady = false;
        }
        else if (int1 == int2){
            bolenler.setText(getString(R.string.farklı_sayi));
            isReady = false;
            isEqual = true;
        }
        else if (int1 > int2){
            int c = int1;
            int1 = int2;
            int2 = c;
        }
        if (int1 == 0 && !isEqual){
            bolenler.setText(getString(R.string.tam_bolen_yok));
            isReady = false;
        }
        if (isReady){
            isle(int1, int2);
        }
    }

    private void isle(int int1, int int2){
        ArrayList<Integer> list = new ArrayList();
        list.add(1);

        ArrayList tambi = new ArrayList();

        int a;
        for (int i = int1 / 2; i > 0; i--) {
            if (int1 % i == 0) {
                a = int1 / i;
                tambi.add(i);
                if (i == 1) {
                    tambi.add(a);
                }
            }
        }
        int a1 = 0;
        for (int i = int2 / 2; i > 0; i--) {
            if (int2 % i == 0) {
                if (a1 != i) {
                    a1 = int2 / i;
                    for (int k = 0; k < tambi.size() ; k++){
                        if ((int)tambi.get(k) == i){
                            list.add(i);
                        }else if ((int)tambi.get(k) == a1){
                            list.add(a1);
                        }
                    }
                } else break;
            }
        }
        Collections.sort(list);
        ArrayList<Integer> copy = new ArrayList<>();

        for (int i = 0; i<list.size(); i++){
            boolean isEqual = false;

            for (int j = 0; j<copy.size(); j++){
                if ((int)list.get(i) == (int)copy.get(j)){
                    isEqual = true;
                }
            }
            if (!isEqual){
                copy.add(list.get(i));
            }

        }
        bolenler.setText(getString(R.string.ortak_bolenler));
        for (int i = 0; i<copy.size(); i++){
            bolenler.setText("" + bolenler.getText() + "\n" + copy.get(i));
        }
    }

}
