package com.example.igotjokes.controllers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.igotjokes.AlarmReceiver;

import java.util.Calendar;

public class AlarmController {

    private Context m_Context;

    private Intent alarmIntent;
    private static final long PERIOD = 1000 * 30;
    private AlarmManager alarmManager;

    public AlarmController(Context context){

        m_Context = context;
        alarmManager = (AlarmManager)m_Context.getSystemService(Context.ALARM_SERVICE);
        alarmIntent = new Intent(m_Context, AlarmReceiver.class);
    }

    public void StartAlarm(){

        PendingIntent pendingIntent = PendingIntent.getBroadcast(m_Context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmIntent.setData((Uri.parse("custom://"+System.currentTimeMillis())));
        alarmManager.cancel(pendingIntent);

        Calendar alarmStartTime = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        alarmStartTime.set(Calendar.HOUR_OF_DAY, 8);
        alarmStartTime.set(Calendar.MINUTE, 30);
        alarmStartTime.set(Calendar.SECOND, 0);
        if (now.after(alarmStartTime)) {
            Log.d("Hey","Added a day");
            alarmStartTime.add(Calendar.DATE, 1);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        Log.d("Alarm","Notification is set for everyday 8.30 am.");


    }

    public void StopAlarm(){
        Intent i = new Intent(m_Context, AlarmReceiver.class);
        PendingIntent pi=PendingIntent.getBroadcast(m_Context, 0,i, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pi);

        
    }
}
