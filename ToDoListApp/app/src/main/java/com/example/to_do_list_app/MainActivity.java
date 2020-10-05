package com.example.to_do_list_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH = 3300;

    Animation topanim, bottomanim;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topanim = AnimationUtils.loadAnimation(this, R.anim.top_animasyon);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_animasyon);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.text);

        imageView.setAnimation(topanim);
        textView.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, GirisActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);

    }
}