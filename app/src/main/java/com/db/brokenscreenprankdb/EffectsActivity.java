package com.db.brokenscreenprankdb;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class EffectsActivity extends AppCompatActivity {
    public static final String MY_PREF = "mypref";
    public static final String SELECTED_CRACK_TYPE = "SELECTED_CRACK_TYPE";
    private static final String TAG = EffectsActivity.class.getSimpleName();
    public SharedPreferences mSharedPreferences;
    private ImageView type1ImageView;
    private ImageView type2ImageView;
    private ImageView type3ImageView;
    private ImageView type4ImageView;
    private ImageView type5ImageView;
    private ImageView type6ImageView;

//    private void postNotification() {
//        PendingIntent activity = PendingIntent.getActivity(this, 45712, new Intent(this, SplashActivity.class), 268435456);
//        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
//        if (Build.VERSION.SDK_INT >= 26) {
//            NotificationChannel notificationChannel = new NotificationChannel("BROKEN_SCREEN_NOTIFICATION", "My Notifications", 3);
//            notificationChannel.setDescription("");
//            notificationChannel.enableLights(false);
//            notificationChannel.enableVibration(false);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder((Context) this, "BROKEN_SCREEN_NOTIFICATION").setSmallIcon((int) R.mipmap.ic_launcher).setContentTitle("GlassBreakPrank").setContentText("Click to remove broken Screen.").setContentIntent(activity).setAutoCancel(true);
//        if (notificationManager != null) {
//            notificationManager.notify(1145, autoCancel.build());
//        }
//    }

    private void postNotification() {
        PendingIntent activity = PendingIntent.getActivity(this, 45712, new Intent(this, SplashActivity.class), PendingIntent.FLAG_IMMUTABLE);
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel("BROKEN_SCREEN_NOTIFICATION", "My Notifications", 3);
            notificationChannel.setDescription("");
            notificationChannel.enableLights(false);
            notificationChannel.enableVibration(false);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder((Context) this, "BROKEN_SCREEN_NOTIFICATION").setSmallIcon((int) R.mipmap.ic_launcher).setContentTitle("GlassBreakPrank").setContentText("Click to remove broken Screen.").setContentIntent(activity).setAutoCancel(true);
        if (notificationManager != null) {
            notificationManager.notify(1145, autoCancel.build());
        }
    }


    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_effects);
        this.type1ImageView = (ImageView) findViewById(R.id.crack1_imageview);
        this.type2ImageView = (ImageView) findViewById(R.id.crack2_imageview);
        this.type3ImageView = (ImageView) findViewById(R.id.crack3_imageview);
        this.type4ImageView = (ImageView) findViewById(R.id.crack4_imageview);
        this.type5ImageView = (ImageView) findViewById(R.id.crack5_imageview);
        this.type6ImageView = (ImageView) findViewById(R.id.crack6_imageview);
        ((ImageView) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.finish();
            }
        });
        this.type1ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(1);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
        this.type2ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(2);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
        this.type3ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(3);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
        this.type4ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(4);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
        this.type5ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(5);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
        this.type6ImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EffectsActivity.this.saveSelectedEffect(6);
                EffectsActivity.this.willShowAdOrStartService();
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void saveSelectedEffect(int i) {
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", 0);
        this.mSharedPreferences = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (this.mSharedPreferences.contains("SELECTED_CRACK_TYPE")) {
            edit.remove("SELECTED_CRACK_TYPE");
            edit.apply();
        }
        edit.putInt("SELECTED_CRACK_TYPE", i);
        edit.apply();
    }

    public void startBrokenService() {
        startService(new Intent(this, BrokenScreenService.class));
        startService(new Intent(this, NotificationService.class));
        postNotification();
        finishAffinity();
    }

    public void willShowAdOrStartService() {
        startBrokenService();
    }
}
