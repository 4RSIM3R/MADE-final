package com.studio.suku.made.Service;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.studio.suku.made.MainActivity;
import com.studio.suku.made.Model.Release;
import com.studio.suku.made.Model.ReleaseResults;
import com.studio.suku.made.Model.RequestRelease;
import com.studio.suku.made.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReleaseReminder extends BroadcastReceiver {

    public List<ReleaseResults.ResultsBean> releaseResultsList = new ArrayList<>(), tmpMovies;
    String baseUrl = "https://api.themoviedb.org";
    String api_key = "24f2356bed948a69b6ce4946afbf4f67";
    private final int REQUEST_CODE_RELEASE = 12;
    public static String CHANNEL_ID = "ch_1";
    public static CharSequence CHANNEL_NAME = "release_today";
    public ReleaseReminder() {

    }

    @Override
    public void onReceive(final Context context, Intent intent) {

        RequestRelease release = Release.retrofit(baseUrl).create(RequestRelease.class);
        Call<ReleaseResults> call = release.getUpcomingFilm(api_key);

        Date cal = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String dateToday = dateFormat.format(cal);

        call.enqueue(new Callback<ReleaseResults>() {
            @Override
            public void onResponse(Call<ReleaseResults> call, Response<ReleaseResults> response) {

                  if (response.isSuccessful()){
                      if (response.body() != null){
                          tmpMovies.add((ReleaseResults.ResultsBean) response.body().getResults());
                          //Compare The Date
                          for (ReleaseResults.ResultsBean r : tmpMovies){
                              String date_release = r.getRelease_date();
                              if (date_release.equalsIgnoreCase(dateToday)){
                                  releaseResultsList.add(r);
                              }
                          }
                          //Call The Notification
                          releaseToday(context);
                      }
                  }

            }

            @Override
            public void onFailure(Call<ReleaseResults> call, Throwable t) {
                t.printStackTrace();
                Log.d("Ada Errror", t.getMessage());
            }
        });

    }

    private void releaseToday(Context context){

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri ringtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent;
        PendingIntent pendingIntent;
        String message;
        int numMovies = 0;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});
            builder.setChannelId(CHANNEL_ID);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }
        }

        try {
            numMovies = ((releaseResultsList.size() > 0) ?  releaseResultsList.size() : 0);
        }catch (Exception e){
            Log.w("ERROR", e.getMessage());
        }

        if (numMovies == 0){
            //Jika Tidak Ada Movies Yang Release Hari ini
            message = "Tidak Release Untuk Hari ini";
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = TaskStackBuilder.create(context)
                    .addNextIntent(intent)
                    .getPendingIntent(REQUEST_CODE_RELEASE, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentTitle("Hore")
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(ringtone)
                    .setAutoCancel(true);
            if (notificationManager != null) {
                notificationManager.notify(0, builder.build());
            }
        }
        else {
            intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            for (int i = 0; i < numMovies; i++){
                message = releaseResultsList.get(i).getTitle();
                pendingIntent = TaskStackBuilder.create(context)
                        .addNextIntent(intent)
                        .getPendingIntent(i, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentTitle("Bismillah")
                        .setContentText(message)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setSound(ringtone)
                        .setAutoCancel(true);
                if (notificationManager != null) {
                    notificationManager.notify(i, builder.build());
                }
            }
        }

    }

    public void StartReminder(Context context){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE_RELEASE, intent, 0);
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        Toast.makeText(context, "Alhamdulillah", Toast.LENGTH_SHORT).show();
    }

    public void stopReminder(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, ReleaseReminder.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE_RELEASE, intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
