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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
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
    Cursor cursor;

    MyDBHelper mDBHelper;
    Date date = new Date();// 오늘에 날짜를 세팅 해준다.
    int year = date.getYear() + 1900;
    int mon = date.getMonth() + 1;
    String today,endday;
    int mId;
    EditText editTitle, editTime,editTimeend, editMemo,editDate,editDateend;
    SimpleDateFormat df = new SimpleDateFormat("dd", Locale.KOREA);
    SimpleDateFormat dataformat = new SimpleDateFormat("yyyy/MM/dd",Locale.KOREA);


    Calendar cal = Calendar.getInstance();

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
        editTimeend = (EditText) findViewById(R.id.edittimend);

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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    mchildRef = mConditionRef1.child(user.getUid());

                }
            }
        };

    }

    public void onRegist(View v)throws Exception
    {
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        Date nDate = dataformat.parse(editDate.getText().toString());
        Date endDate = dataformat.parse(editDateend.getText().toString());
        long diff = endDate.getTime() - nDate.getTime();
        long diffDays = diff/(24*60*60*1000);
        Calendar cal = Calendar.getInstance();
        cal.setTime(nDate);
        String curday;
        boolean[] week = { false, _toggleSun.isChecked(), _toggleMon.isChecked(), _toggleTue.isChecked(), _toggleWed.isChecked(),
                _toggleThu.isChecked(), _toggleFri.isChecked(), _toggleSat.isChecked() }; // sunday=1 이라서 0의 자리에는 아무 값이나 넣었음

        for (long i=0;i<diffDays;i++) {
            int dayNum = cal.get(Calendar.DAY_OF_WEEK);
            String x;
            if(cal.get(Calendar.DATE)<10){
                x = "0" + cal.get(Calendar.DATE);
            }
            else{
                x=""+cal.get(Calendar.DATE);
            }
            curday = cal.get(Calendar.YEAR)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+x;
            if (week[1] == true && dayNum == 1) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[2] == true && dayNum == 2) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[3] == true && dayNum == 3) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[4] == true && dayNum == 4) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[5] == true && dayNum == 5) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[6] == true && dayNum == 6) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            if (week[7] == true && dayNum == 7) {
                db.execSQL("INSERT INTO today VALUES(null, '"
                        + editTitle.getText().toString() + "', '"
                        + curday + "', '"
                        + editTime.getText().toString() + "', '"
                        + editTimeend.getText().toString() + "', '"
                        + editMemo.getText().toString() + "');");
            }
            cal.add(Calendar.DATE,1);
        }
        mDBHelper.close();
        setResult(RESULT_OK);

        finish();
    }

    public void onUnregist(View v)
    {
      finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
