package com.studio.suku.made.Service;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.studio.suku.made.Model.ReleaseResults;
import com.studio.suku.made.Model.RequestRelease;
import com.studio.suku.made.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReleaseReminder extends BroadcastReceiver {

  public final List<ReleaseResults.ResultsBean> releaseResultsList = new ArrayList<>();
  final String baseUrl = "https://api.themoviedb.org";
  final String api_key = "24f2356bed948a69b6ce4946afbf4f67";
  private final int REQUEST_CODE_RELEASE = 12;
  public ReleaseReminder() {

  }

  @Override
  public void onReceive(final Context context, Intent intent) {

    Date cal = Calendar.getInstance().getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    final String dateToday = dateFormat.format(cal);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RequestRelease release = retrofit.create(RequestRelease.class);
    Call<ReleaseResults> call = release.getUpcomingFilm(api_key, dateToday, dateToday);

    call.enqueue(new Callback<ReleaseResults>() {
      @Override
      public void onResponse(Call<ReleaseResults> call, Response<ReleaseResults> response) {
        Log.d("Bisa", response.toString());
        if (response.body() != null){
          //showAlarmNotification(context, "Hey !!!", "There Is Something New", 8);
          releaseResultsList.add(response.body().getResults().get(0));
          for (ReleaseResults.ResultsBean r : releaseResultsList){
            String date = r.getRelease_date();
            showAlarmNotification(context, "Release Today", r.getTitle(), 8);
          }
        }
      }

      @Override
      public void onFailure(Call<ReleaseResults> call, Throwable t) {
        t.printStackTrace();
        Log.e("Ada Error", t.getMessage());
      }
    });

  }

  private void showAlarmNotification(Context context, String title, String message, int notifId) {
    String CHANNEL_ID = "Channel_2";
    String CHANNEL_NAME = "Release channel";
    NotificationCompat.Builder builder;

    NotificationManager notificationManagerCompat = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    builder = new NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_video_library_black_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
            .setSound(alarmSound);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

      NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
              CHANNEL_NAME,
              NotificationManager.IMPORTANCE_DEFAULT);

      channel.enableVibration(true);
      channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

      builder.setChannelId(CHANNEL_ID);

      if (notificationManagerCompat != null) {
        notificationManagerCompat.createNotificationChannel(channel);
      }
    }

    android.app.Notification notification = builder.build();

    if (notificationManagerCompat != null) {
      notificationManagerCompat.notify(notifId, notification);
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

  private void showToast(Context context, String title, String message){
    Toast.makeText(context, title, Toast.LENGTH_LONG).show();
  }
}