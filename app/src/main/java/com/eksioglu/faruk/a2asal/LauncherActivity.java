package com.eksioglu.faruk.a2asal;

import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LauncherActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        constraintLayout=findViewById(R.id.kapanislayout);
        button=findViewById(R.id.button3);
        textView = findViewById(R.id.textView2);

        int a;

        Intent intent = getIntent();
        a = intent.getIntExtra("tema", 2);

        if (a==2) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

        if (a==1){
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorDarkBackground));

            textView.setTextColor(getResources().getColor(R.color.colorDarkText));

            button.setTextColor(getResources().getColor(R.color.colorDarkButtonText));
            button.setBackgroundColor(getResources().getColor(R.color.colorDarkButton));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryDark));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryDark));
            }
        }
        else{
            constraintLayout.setBackgroundColor(getResources().getColor(R.color.colorLightBackground));

            textView.setTextColor(getResources().getColor(R.color.colorLightText));

            button.setTextColor(getResources().getColor(R.color.colorLightButtonText));
            button.setBackgroundColor(getResources().getColor(R.color.colorLightButton));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(getResources().getColor(R.color.colorSecondaryLight));
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorSecondaryLight));
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ActivityCompat.finishAffinity(LauncherActivity.this);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public  void comeback(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
