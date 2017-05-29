package com.example.kimhanjoo.mybob_kau;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.i("AlarmReceiver.java | onReceive", "|" + "============ receive" + "|");

        boolean[] week = intent.getBooleanArrayExtra("weekday");
        Log.i("AlarmReceiver.java | onReceive", "|일 : " + week[Calendar.SUNDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|월 : " + week[Calendar.MONDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|화 : " + week[Calendar.TUESDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|수 : " + week[Calendar.WEDNESDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|목 : " + week[Calendar.THURSDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|금 : " + week[Calendar.FRIDAY]);
        Log.i("AlarmReceiver.java | onReceive", "|토 : " + week[Calendar.SATURDAY]);

        Calendar cal = Calendar.getInstance();
        Log.i("AlarmReceiver.java | onReceive", "|" + cal.get(Calendar.DAY_OF_WEEK) + "|");
        // 오늘 요일의 알람 재생이 true이면 사운드 재생

        if (!week[cal.get(Calendar.DAY_OF_WEEK)])
            return;

        File file = new File(intent.getStringExtra("file"));
        Log.i("AlarmReceiver.java | onReceive", "|" + file.toString() + "|");

        MediaPlayer player = new MediaPlayer();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener()
        {
            @Override
            public void onPrepared(MediaPlayer mp)
            {
                mp.start();
            }
        });
        try
        {
            player.setDataSource(file.toString());
            player.prepare();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
