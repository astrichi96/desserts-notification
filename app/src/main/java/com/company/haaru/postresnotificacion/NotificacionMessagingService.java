package com.company.haaru.postresnotificacion;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Haaru on 18/11/2016.
 */

public class NotificacionMessagingService extends FirebaseMessagingService {
    public static final String TAG = "NOTICIAS";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String from = remoteMessage.getFrom();
        Log.d(TAG,"MEnsaje recibido de"+ from);
        if(remoteMessage.getNotification() != null){
            Log.d(TAG,"Notificacion: " + remoteMessage.getNotification().getBody());
            mostrarNotificacion(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    }

    private void mostrarNotificacion(String title, String body) {
        Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i, PendingIntent.FLAG_ONE_SHOT);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cupcake_sticker)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
