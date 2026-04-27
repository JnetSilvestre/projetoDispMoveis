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

/**
 * Tela de apresentação (Splash Screen).
 * Exibe a logo e o nome do app por 2,5 segundos antes de abrir a MainActivity.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2500; // ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imgLogo = findViewById(R.id.img_logo_splash);
        TextView tvNome   = findViewById(R.id.tv_app_name_splash);
        TextView tvSlogan = findViewById(R.id.tv_slogan_splash);

        // Animações de entrada
        Animation fadeIn   = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUp  = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        imgLogo.startAnimation(fadeIn);
        tvNome.startAnimation(slideUp);
        tvSlogan.startAnimation(slideUp);

        // Navega para MainActivity após o delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }, SPLASH_DURATION);
    }
}
