package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.studio.suku.made.LocalDb.Prefences;
import com.studio.suku.made.Service.Notification;
import com.studio.suku.made.Service.ReleaseReminder;


public class SettingActivity extends AppCompatActivity {

    Switch notif_7, notif_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final Notification notification = new Notification();
        final ReleaseReminder releaseReminder = new ReleaseReminder();



        notif_7 = findViewById(R.id.switch_notif_7);
        notif_8 = findViewById(R.id.switch_notif_8);
        notif_7.setChecked(Prefences.getDaily(getApplicationContext()));
        notif_8.setChecked(Prefences.getRelease(getApplicationContext()));

        notif_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notification.setRepeatingNotification(getApplicationContext(), Notification.TYPE_7, "Bismillah");
                    Prefences.setDaily(getApplicationContext(), true);
                }
                else {
                    Prefences.setDaily(getApplicationContext(), false);
                }
            }
        });

        notif_8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    releaseReminder.StartReminder(getApplicationContext());
                    Prefences.setRelease(getApplicationContext(), true);
                }
                else {
                    Prefences.setRelease(getApplicationContext(), false);
                }
            }
        });

    }
}
