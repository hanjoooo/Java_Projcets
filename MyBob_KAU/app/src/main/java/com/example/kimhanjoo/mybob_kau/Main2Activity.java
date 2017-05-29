package com.example.kimhanjoo.mybob_kau;

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;

public class Main2Activity extends Activity
{
    private static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/myapp";
    private static final String NORMAL_PATH = BASE_PATH + "/normal";

    private AlarmManager _am;

    private ToggleButton _toggleSun, _toggleMon, _toggleTue, _toggleWed, _toggleThu, _toggleFri, _toggleSat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        _am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        _toggleSun = (ToggleButton) findViewById(R.id.toggle_sun);
        _toggleMon = (ToggleButton) findViewById(R.id.toggle_mon);
        _toggleTue = (ToggleButton) findViewById(R.id.toggle_tue);
        _toggleWed = (ToggleButton) findViewById(R.id.toggle_wed);
        _toggleThu = (ToggleButton) findViewById(R.id.toggle_thu);
        _toggleFri = (ToggleButton) findViewById(R.id.toggle_fri);
        _toggleSat = (ToggleButton) findViewById(R.id.toggle_sat);
    }

    public void onRegist(View v)
    {
        Log.i("MainActivity.java | onRegist", "|" + "========= regist" + "|");

        File file = new File(NORMAL_PATH + "/drop_1235.m4a");
        Log.i("MainActivity.java | onRegist", "| file exists? : " + file.exists() + "|" + file.hashCode());

        boolean[] week = { false, _toggleSun.isChecked(), _toggleMon.isChecked(), _toggleTue.isChecked(), _toggleWed.isChecked(),
                _toggleThu.isChecked(), _toggleFri.isChecked(), _toggleSat.isChecked() }; // sunday=1 이라서 0의 자리에는 아무 값이나 넣었음

        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("file", file.toString());
        intent.putExtra("weekday", week);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, file.hashCode(), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + 10); // 10초 뒤

        long oneday = 24 * 60 * 60 * 1000;// 24시간

        // 10초 뒤에 시작해서 매일 같은 시간에 반복하기
        _am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), oneday, pIntent);
    }

    public void onUnregist(View v)
    {
        Log.i("MainActivity.java | onUnregist", "|" + "========= unregist" + "|");

        File file = new File(NORMAL_PATH + "/drop_1235.m4a");
        Log.i("MainActivity.java | onRegist", "| file exists? : " + file.exists() + "|" + file.hashCode());

        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this, file.hashCode(), intent, 0);

        _am.cancel(pIntent);
    }
}
