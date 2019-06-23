package com.eksioglu.faruk.a2asal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KokSadelestirme extends AppCompatActivity {

    EditText girdi;
    Button button;
    TextView textView;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kok_sadelestirme);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        girdi=findViewById(R.id.kokedit);
        button=findViewById(R.id.sadelestir);
        textView=findViewById(R.id.yazdirma);
        constraintLayout = findViewById(R.id.koklayout);

        Intent i = getIntent();
        setMyTheme(i.getIntExtra("tema", 0));

    }

    public void setMyTheme(int isDark){
        if (isDark == 1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            textView.setTextColor(getResources().getColor(R.color.colorDarkText));

            girdi.setTextColor(getResources().getColor(R.color.colorLight));
            girdi.setHintTextColor(getResources().getColor(R.color.colorDarkText));

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

            girdi.setTextColor(getResources().getColor(R.color.colorDark));
            girdi.setHintTextColor(getResources().getColor(R.color.colorLightText));

            button.setBackgroundColor(getResources().getColor(R.color.colorLightButton));
            button.setTextColor(getResources().getColor(R.color.colorLightButtonText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryLight));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }

    public void sadelestir(View view){
        int sayi;

        if (girdi.getText().toString().length() < 10) {
            try{
                sayi = Integer.parseInt(girdi.getText().toString());
            }catch (Exception e){
                sayi = -1;
            }
        }else{
            sayi = -2;
        }

        //try {
        //    sayi1 = Long.parseLong(girdi.getText().toString());
        //}catch (Exception e){
        //    sayi1=-1;
        //}

        int a = 0;
        int j = 0;
        boolean bolenivarmı=false;
        boolean tamkarebolenivarmi=false;
        int enbuyuktamkare=0;
        int enbuyukkok=0;

        for (int i = sayi / 2; i > 1; i--) {
            if (sayi % i == 0) {
                a = sayi / i;
                if (a == i){
                    j=-1;
                    i=0;
                }else{
                    if (bolenivarmı==false){
                        j=-3;
                        bolenivarmı=true;
                    }
                    if (tamkarebolenivarmi == false) {
                        for (int s = i / 2; s > 1; s--) { //tam bölenin tam kare olup olmadığı kontrol ediliyor
                            if (i % s == 0 && i / s == s) {
                                enbuyuktamkare = i;
                                enbuyukkok = s;
                                j = -2;
                                tamkarebolenivarmi = true;
                                continue;
                            }
                        }
                    }
                }
            } else if(bolenivarmı == false) j++;
        }
        if (j == sayi/2-1) j = -3;
        if (j == -1) textView.setText(""+a);
        else if (j == -3) textView.setText("√(" + sayi + ")");
        else if (j == -2) textView.setText(enbuyukkok + "√(" + (sayi/enbuyuktamkare) + ")");
        else if (sayi == -1) textView.setText(getString(R.string.deger_giriniz));
        else if (sayi == -2) textView.setText(getString(R.string.daha_kucuk_deger_gir));
        else if (sayi == 0) textView.setText("0");
        else if (sayi == 1) textView.setText("1");
    }

}
