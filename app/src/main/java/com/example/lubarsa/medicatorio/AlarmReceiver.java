package com.example.lubarsa.medicatorio;

/**
 * Created by Lubarsa on 3/08/17.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Error","Notificación");
        createNotification(context, AlarmsFragment.instance.nameNotification, AlarmsFragment
        .instance.typeNotification, AlarmsFragment.instance.measureNotification);
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();
    }

    public void createNotification(Context context, String nameNotification, String typeNotification,
                                   String measureNotification){
        PendingIntent notificationIntent = PendingIntent.getActivity(context, 0, new Intent(context,
                ContainerActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Es hora de tomar tus medicinas! :D")
                .setContentText(nameNotification + "\n Presentación: " + typeNotification +
                "\n Dosis: " + measureNotification);
        builder.setContentIntent(notificationIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        builder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

    }

}