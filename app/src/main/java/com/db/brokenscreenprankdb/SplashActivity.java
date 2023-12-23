package com.db.brokenscreenprankdb;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.allgodaarti_plash_screen);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
        stopService(new Intent(this, BrokenScreenService.class));
        new CountDownTimer(2000, 1000) {
            public void onFinish() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }

            public void onTick(long j) {
            }
        }.start();
    }
}
