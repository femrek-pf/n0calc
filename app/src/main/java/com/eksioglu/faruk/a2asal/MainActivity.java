package com.eksioglu.faruk.a2asal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int isDark = 0;
    int savedTheme;
    TextView tw;
    Button btnP;
    Button btnM;
    Button btnR;
    Button btnC;
    ConstraintLayout constraintLayout;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        constraintLayout=findViewById(R.id.mainlayout);
        btnM =findViewById(R.id.kontrol);
        btnP =findViewById(R.id.bulma);
        btnR =findViewById(R.id.ks);
        btnC =findViewById(R.id.ortakc);
        tw=findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.eksioglu.faruk.a2asal", Context.MODE_PRIVATE);
        try {
            savedTheme = sharedPreferences.getInt("tema", 0);
        }catch (Exception e){
            savedTheme = 0;
        }

        isDark = savedTheme;
        setMyTheme(isDark);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent i = new Intent(this, LauncherActivity.class);
            i.putExtra("tema", isDark);
            startActivity(i);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void findingPrime(View view){
        Intent intent = new Intent(this, Bulma.class);
        intent.putExtra("tema", isDark);
        startActivity(intent);
    }

    public void findingMultipliers(View view){
        Intent intent = new Intent(this, kontrol.class);
        intent.putExtra("tema", isDark);
        startActivity(intent);
    }

    public void simplifyingRoots(View view){
        Intent intent = new Intent(this, KokSadelestirme.class);
        intent.putExtra("tema", isDark);
        startActivity(intent);
    }

    public void findigCommonDivisor(View view){
        Intent intent = new Intent(this, OrtakBolen.class);
        intent.putExtra("tema", isDark);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.getItem(0);
        if (sharedPreferences.getInt("tema", 0) == 1){
            item.setTitle(getResources().getString(R.string.light));
        }else {
            item.setTitle(getResources().getString(R.string.dark));
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_light) {
            if (sharedPreferences.getInt("tema", 0) == 0){
                isDark = 1;
                sharedPreferences.edit().putInt("tema", isDark).apply();
                setMyTheme(isDark);
                item.setTitle(getResources().getString(R.string.light));

            }else {
                isDark = 0;
                sharedPreferences.edit().putInt("tema", isDark).apply();
                setMyTheme(isDark);
                item.setTitle(getResources().getString(R.string.dark));
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setMyTheme(int isDark){

        if (isDark == 1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));
            btnP.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));
            btnM.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));
            btnC.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));
            btnR.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));

            btnP.setTextColor(getResources().getColor(R.color.colorDarkButtonText));
            btnM.setTextColor(getResources().getColor(R.color.colorDarkButtonText));
            btnC.setTextColor(getResources().getColor(R.color.colorDarkButtonText));
            btnR.setTextColor(getResources().getColor(R.color.colorDarkButtonText));

            tw.setTextColor(getResources().getColor(R.color.colorDarkText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryDark));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryDark));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryDark));
            }
        }
        else if (isDark == 0){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));
            btnP.setBackgroundColor(getResources().getColor(R.color.colorLight));
            btnM.setBackgroundColor(getResources().getColor(R.color.colorLight));
            btnC.setBackgroundColor(getResources().getColor(R.color.colorLight));
            btnR.setBackgroundColor(getResources().getColor(R.color.colorLight));

            btnP.setTextColor(getResources().getColor(R.color.colorLightButtonText));
            btnM.setTextColor(getResources().getColor(R.color.colorLightButtonText));
            btnC.setTextColor(getResources().getColor(R.color.colorLightButtonText));
            btnR.setTextColor(getResources().getColor(R.color.colorLightButtonText));

            tw.setTextColor(getResources().getColor(R.color.colorLightText));

            toolbar.setBackgroundColor(getResources().getColor(R.color.colorSecondaryLight));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }
}
