package com.db.brokenscreenprankdb;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Allgod_MainActivity extends Activity {
    private static final int REQ_CODE_GALLERY_CAMERA = 124;

    public ImageView btn_arati;
    public ImageView btn_moreapp;
    /* access modifiers changed from: private */

    private boolean addPermission(List<String> list, String str) {
        if (Build.VERSION.SDK_INT < 23 || checkSelfPermission(str) == 0) {
            return true;
        }
        list.add(str);
        return shouldShowRequestPermissionRationale(str);
    }

    private void checkMultiplePermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            if (!addPermission(arrayList2, "android.permission.READ_EXTERNAL_STORAGE")) {
                arrayList.add("Read Storage");
            }
            if (!addPermission(arrayList2, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                arrayList.add("Read Storage");
            }
            if (!addPermission(arrayList2, "android.permission.READ_CONTACTS")) {
                arrayList.add("Telephony");
            }
            if (!addPermission(arrayList2, "android.permission.CALL_PHONE")) {
                arrayList.add("Telephony");
            }
            if (arrayList2.size() > 0) {
                requestPermissions((String[]) arrayList2.toArray(new String[arrayList2.size()]), 124);
            }
        }
    }


    public static boolean isOnline(Activity activity) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService("connectivity");
            if (connectivityManager.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /* access modifiers changed from: private */
    public void openGallery() {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onBackPressed() {
        finishAffinity();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.allgodaarti_activity_main);
        if (Build.VERSION.SDK_INT >= 23) {
            checkMultiplePermissions();
        }
        this.btn_arati = (ImageView) findViewById(R.id.img_btn_aarti);
        this.btn_moreapp = (ImageView) findViewById(R.id.img_btn_moreapp);
        this.btn_arati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT < 23) {
                    Allgod_MainActivity.this.openGallery();
                } else if (Allgod_MainActivity.this.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                    Allgod_MainActivity.this.openGallery();
                } else if (Allgod_MainActivity.this.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    Allgod_MainActivity.this.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 3);
                }
            }
        });
        this.btn_moreapp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Allgod_MainActivity.this.rate_moreapp("market://search?q=pub:Photo+Tools+Maker");
            }
        });
    }

    @TargetApi(23)
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != 1) {
            if (i != 124) {
                super.onRequestPermissionsResult(i, strArr, iArr);
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("android.permission.WRITE_EXTERNAL_STORAGE", 0);
            hashMap.put("android.permission.READ_EXTERNAL_STORAGE", 0);
            for (int i2 = 0; i2 < strArr.length; i2++) {
                hashMap.put(strArr[i2], Integer.valueOf(iArr[i2]));
            }
            if (!(((Integer) hashMap.get("android.permission.READ_EXTERNAL_STORAGE")).intValue() == 0 && ((Integer) hashMap.get("android.permission.WRITE_EXTERNAL_STORAGE")).intValue() == 0) && Build.VERSION.SDK_INT >= 23) {
                Toast.makeText(getApplicationContext(), "My App cannot run without Storage Permissions.\nRelaunch My App or allow permissions in Applications Settings", 1).show();
                finish();
            }
        } else if (iArr[0] == 0) {
            openGallery();
        } else if (checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0) {
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1);
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void rate_moreapp(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        if (isOnline(this)) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No Internet Connection..", 0).show();
        }
    }
}
