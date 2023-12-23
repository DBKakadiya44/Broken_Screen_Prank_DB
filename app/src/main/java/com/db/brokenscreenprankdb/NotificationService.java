package com.db.brokenscreenprankdb;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class NotificationService extends Service {
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onTaskRemoved(Intent intent) {
        NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
        if (notificationManager != null) {
            notificationManager.cancel(1145);
        }
        super.onTaskRemoved(intent);
    }
}
