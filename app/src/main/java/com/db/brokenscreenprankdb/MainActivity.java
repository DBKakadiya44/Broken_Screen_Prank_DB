package com.db.brokenscreenprankdb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int DRAW_OVER_CODE = 1001;
    /* access modifiers changed from: private */
    public static final String TAG = MainActivity.class.getSimpleName();
    public Dialog mRateDialog;

    private void askPermissionFirst() {
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            new AlertDialog.Builder(this).setTitle("Grant Permission").setMessage("This aap needs permission to draw overlays on device screen. Please Grant this permission on next screen").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    StringBuilder h = c.h("package:");
                    h.append(MainActivity.this.getPackageName());
                    MainActivity.this.startActivity(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(h.toString())));
                }
            }).show();
        }
    }

    private boolean checkToShowDialog() {
        SharedPreferences sharedPreferences = getSharedPreferences("GlassBreakPrank", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (sharedPreferences.getLong("dialogDisplayedTime", 0) >= System.currentTimeMillis() - 259200000) {
            return false;
        }
        edit.putLong("dialogDisplayedTime", System.currentTimeMillis()).apply();
        return true;
    }

    /* access modifiers changed from: private */


    public void askPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(getApplicationContext())) {
            playButtonSound();
            startActivity(new Intent(this, EffectsActivity.class));

            return;
        }
        new AlertDialog.Builder(this).setTitle("Grant Permission").setMessage("This aap needs permission to draw overlays on device screen. Please Grant this permission on next screen").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder h = c.h("package:");
                h.append(MainActivity.this.getPackageName());
                MainActivity.this.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(h.toString())), 1001);
            }
        }).show();
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1001) {
            super.onActivityResult(i, i2, intent);
        } else if (i2 == -1) {
            playButtonSound();
            startActivity(new Intent(this, EffectsActivity.class));

        } else {
            Toast.makeText(this, "Draw over other app permission not available.", 0).show();
        }
    }

    public void onBackPressed() {
        if (!checkToShowDialog()) {
            super.onBackPressed();
        }
        finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_main);
        ((ImageView) findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });
        ((ImageView) findViewById(R.id.brake_screen_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.askPermissionIfNeeded();
            }
        });
//        ((ImageView) findViewById(R.id.brake_image_button)).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                MainActivity.this.playButtonSound();
//                MainActivity.this.startActivity(new Intent(MainActivity.this, ImageBrakeActivity.class));
//            }
//        });
//        ((ImageView) findViewById(R.id.brake_bulb_button)).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                MainActivity.this.playButtonSound();
//                MainActivity.this.startActivity(new Intent(MainActivity.this, BulbBrakeActivity.class));
//            }
//        });
        askPermissionFirst();
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

    public void playButtonSound() {
        MediaPlayer create = MediaPlayer.create(this, getResources().getIdentifier("bubble_sound", "raw", getPackageName()));
        create.start();
        create.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }
}
