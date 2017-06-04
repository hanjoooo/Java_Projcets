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

        boolean[] week = { false, _toggleSun.isChecked(), _toggleMon.isChecked(), _toggleTue.isChecked(), _toggleWed.isChecked(),
                _toggleThu.isChecked(), _toggleFri.isChecked(), _toggleSat.isChecked() }; // sunday=1 이라서 0의 자리에는 아무 값이나 넣었음

        if(week[1]){

        }
        if(week[2]){
        }
        if(week[3]){

        }
        if(week[4]){

        }
        if(week[5]){

        }
        if(week[6]){

        }
        if(week[7]){

        }

    }

    public void onUnregist(View v)
    {

    }
}
