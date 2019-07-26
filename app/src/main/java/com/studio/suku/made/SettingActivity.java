package com.studio.suku.made;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.studio.suku.made.Service.Notification;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    Switch notif_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        final Notification notification = new Notification();

        notif_7 = findViewById(R.id.switch_notif_7);

        notif_7.setChecked(true);

        notif_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notification.setRepeatingNotification(getApplicationContext(), Notification.TYPE_7, "Bismillah");
                } else {

                }
            }
        });
    }
}
