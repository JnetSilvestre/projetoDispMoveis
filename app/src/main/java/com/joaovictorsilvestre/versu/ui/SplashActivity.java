package com.joaovictorsilvestre.versu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.joaovictorsilvestre.versu.R;
import com.joaovictorsilvestre.versu.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo   = findViewById(R.id.img_logo_splash);
        TextView  nome   = findViewById(R.id.tv_app_name_splash);
        TextView  slogan = findViewById(R.id.tv_slogan_splash);

        Animation fadeIn  = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        if (logo != null)   logo.startAnimation(fadeIn);
        if (nome != null)   nome.startAnimation(slideUp);
        if (slogan != null) slogan.startAnimation(slideUp);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }, DELAY);
    }
}
