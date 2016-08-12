package com.example.notificationlifecycle;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "onCreateTAG_";
    private int mId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doNotification("main", 0);
//        Log.d(TAG, "onCreate: ");
    }

    public void doNotification(String text, int numb) {

        // First let's define the intent to trigger when notification is selected
// Start out by creating a normal intent (in this case to open an activity)
        Intent intent = new Intent(this, MainActivity.class);
// Next, let's turn this into a PendingIntent using
//   public static PendingIntent getActivity(Context context, int requestCode,
//       Intent intent, int flags)
        int requestID = (int) System.currentTimeMillis(); //unique requestID to differentiate between various notification with same NotifId
        int flags = PendingIntent.FLAG_CANCEL_CURRENT; // cancel old intent and create new one
        PendingIntent pIntent = PendingIntent.getActivity(this, requestID, intent, flags);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText(text)
                        .setContentIntent(pIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(numb, mBuilder.build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        doNotification("onStart", 1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        doNotification("onPause", 2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        doNotification("onStop", 3);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doNotification("onDestroy", 4);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doNotification("onResume", 5);
    }
}
