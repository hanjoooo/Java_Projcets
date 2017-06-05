package com.example.kimhanjoo.mybob_kau;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
import android.widget.EditText;
import android.widget.ToggleButton;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends Activity
{
    private static final String BASE_PATH = Environment.getExternalStorageDirectory() + "/myapp";
    private static final String NORMAL_PATH = BASE_PATH + "/normal";

    private AlarmManager _am;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef;
    DatabaseReference mConditionRef1 = mRootRef.child("Schedule");
    DatabaseReference mchildRef;
    DatabaseReference mchild1Ref;
    DatabaseReference mchild2Ref;
    DatabaseReference mchild3Ref;
    DatabaseReference mchild4Ref;
    DatabaseReference mchild5Ref;
    DatabaseReference mchild6Ref;

    MyDBHelper mDBHelper;
    Date date = new Date();// 오늘에 날짜를 세팅 해준다.
    int year = date.getYear() + 1900;
    int mon = date.getMonth() + 1;
    String today,endday;
    int mId;
    EditText editTitle, editTime, editMemo,editDate,editDateend;
    SimpleDateFormat df = new SimpleDateFormat("dd", Locale.KOREA);
    String str_date = df.format(new Date());
    private ToggleButton _toggleSun, _toggleMon, _toggleTue, _toggleWed, _toggleThu, _toggleFri, _toggleSat;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTitle = (EditText) findViewById(R.id.edittitle);
        editTime = (EditText) findViewById(R.id.edittime);
        editMemo = (EditText) findViewById(R.id.editmemo);
        editDate = (EditText) findViewById(R.id.editdate);
        editDateend = (EditText) findViewById(R.id.editdatend);

        today = year + "/"
                + mon + "/" + str_date;
        endday = year +"/"+(mon+2)+"/"+str_date;

        editDate.setText(today);
        editDateend.setText(endday);

        mDBHelper = new MyDBHelper(this, "Todays.db", null, 1);
        mAuth = FirebaseAuth.getInstance();

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
            Log.d("agegaega","okssssssssssssssssssss");
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
