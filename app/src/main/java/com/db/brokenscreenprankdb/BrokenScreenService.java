package com.db.brokenscreenprankdb;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.annotation.Nullable;

public class BrokenScreenService extends Service {
    public static final String MY_PREF = "mypref";
    public static final String SELECTED_CRACK_TYPE = "SELECTED_CRACK_TYPE";
    public int audioResID = 0;
    public ImageView brokenImageView;
    private int crackEffectResID = 0;
    public SharedPreferences mSharedPreferences;
    private MediaPlayer mediaPlayer;
    private WindowManager.LayoutParams params;
    private int selectedCrack = 0;
    public WindowManager windowManager;

    public void breakTheScreen(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.params = new WindowManager.LayoutParams(-1, -1, 2038, 256, -3);
        } else {
            this.params = new WindowManager.LayoutParams(-1, -1, 2006, 256, -3);
        }
        if (i2 >= 19) {
            this.params.flags = 201326616;
        } else {
            this.params.flags = 24;
        }
        this.brokenImageView.setImageResource(this.crackEffectResID);
        WindowManager windowManager2 = this.windowManager;
        if (windowManager2 != null) {
            windowManager2.addView(this.brokenImageView, this.params);
        }
        MediaPlayer create = MediaPlayer.create(this, i);
        this.mediaPlayer = create;
        create.start();
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        this.brokenImageView.setClickable(false);
        this.brokenImageView.setFocusable(false);
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.windowManager = (WindowManager) getApplicationContext().getSystemService("window");
        ImageView imageView = new ImageView(this);
        this.brokenImageView = imageView;
        imageView.setImageResource(R.drawable.transparent_image);
        this.brokenImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        if (Build.VERSION.SDK_INT >= 26) {
            this.params = new WindowManager.LayoutParams(-1, -1, 2038, 256, -3);
        } else {
            this.params = new WindowManager.LayoutParams(-1, -1, 2002, 256, -3);
        }
        WindowManager windowManager2 = this.windowManager;
        if (windowManager2 != null) {
            windowManager2.addView(this.brokenImageView, this.params);
        }
        this.audioResID = getResources().getIdentifier("glass_break_sound", "raw", getPackageName());
        this.brokenImageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BrokenScreenService brokenScreenService = BrokenScreenService.this;
                brokenScreenService.windowManager.removeView(brokenScreenService.brokenImageView);
                BrokenScreenService brokenScreenService2 = BrokenScreenService.this;
                brokenScreenService2.breakTheScreen(brokenScreenService2.audioResID);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", 0);
        this.mSharedPreferences = sharedPreferences;
        if (sharedPreferences.contains("SELECTED_CRACK_TYPE")) {
            this.selectedCrack = this.mSharedPreferences.getInt("SELECTED_CRACK_TYPE", 0);
        }
        int i = this.selectedCrack;
        if (i == 1) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type1", "drawable", getPackageName());
        } else if (i == 2) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type2", "drawable", getPackageName());
        } else if (i == 3) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type3", "drawable", getPackageName());
        } else if (i == 4) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type4", "drawable", getPackageName());
        } else if (i == 5) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type5", "drawable", getPackageName());
        } else if (i == 6) {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type6", "drawable", getPackageName());
        } else {
            this.crackEffectResID = getResources().getIdentifier("crack_screen_screen_type1", "drawable", getPackageName());
        }
    }

    public void onDestroy() {
        super.onDestroy();
        ImageView imageView = this.brokenImageView;
        if (imageView != null) {
            this.windowManager.removeView(imageView);
            this.brokenImageView = null;
        }
    }
}
