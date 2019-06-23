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

public class kontrol extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        textView=findViewById(R.id.sonuc);
        editText=findViewById(R.id.input);
        button=findViewById(R.id.button);
        constraintLayout=findViewById(R.id.kontrolayout);

        Intent i = getIntent();
        setMyTheme(i.getIntExtra("tema", 0));
    }

    public void setMyTheme(int isDark){
        if (isDark == 1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            textView.setTextColor(getResources().getColor(R.color.colorDarkText));

            editText.setTextColor(getResources().getColor(R.color.colorLight));
            editText.setHintTextColor(getResources().getColor(R.color.colorDarkText));

            button.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));
            button.setTextColor(getResources().getColor(R.color.colorDarkButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryDark));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryDark));
            }
        }
        else{
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
            textView.setTextColor(getResources().getColor(R.color.colorLightText));

            editText.setTextColor(getResources().getColor(R.color.colorDark));
            editText.setHintTextColor(getResources().getColor(R.color.colorLightText));

            button.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
            button.setTextColor(getResources().getColor(R.color.colorLightButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryLight));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }

    public void kontrol(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        int sayi;

        if (editText.getText().toString().length() < 10) {
            try{
                sayi = Integer.parseInt(editText.getText().toString());
            }catch (Exception e){
                sayi = -1;
            }
        }else{
            sayi = -2;
        }

        kontrolet(sayi);
    }

    private void kontrolet(int sayi){
        if (sayi == -1)
            textView.setText(getString(R.string.deger_giriniz));
        else if (sayi == -2){
            textView.setText(getString(R.string.daha_kucuk_deger_gir));
        }
        else if (sayi == 0){
            textView.setText(getString(R.string.sayi_asal_degildir));
        }
        else {
            ArrayList tamb = new ArrayList();
            ArrayList tami = new ArrayList();
            int a = 0;
            int j = 0;
            for (int i = sayi / 2; i > 1; i--) {
                if (sayi % i == 0) {
                    if (a != i) {
                        a = sayi / i;
                        if (a != i) {
                            tamb.add(i);
                            tami.add(a);
                        } else {
                            tamb.add(i);
                            tami.add(a);
                            break;
                        }
                    } else break;
                } else
                    j++;
            }
            if (j == sayi / 2 - 1) textView.setText(getString(R.string.sayi_asaldir));
            else {
                textView.setText(getString(R.string.tam_bolundugu_sayılar));
                for (int g = 0; g < tamb.size(); g++) {
                    if (tamb.get(g) != tami.get(g)) {
                        textView.setText("" + textView.getText() + "\n" + tami.get(g) +" "+ getString(R.string.ve) +" "+ tamb.get(g));
                    } else
                        textView.setText("" + textView.getText() + "\n" + tamb.get(g));
                }
            }
        }
    }

}
