package com.example.kimhanjoo.mybob_kau;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MydiaryRecord extends AppCompatActivity implements View.OnClickListener {

    EditText editDate;
    Date date = new Date();// 오늘에 날짜를 세팅 해준다.
    int year = date.getYear() + 1900;
    int mon = date.getMonth() + 1;
    String today;
    SimpleDateFormat df = new SimpleDateFormat("dd", Locale.KOREA);
    String str_date = df.format(new Date());

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydiary_record);



        editDate = (EditText) findViewById(R.id.editdate);
        today = year + "/"
                + mon + "/" + str_date;

        editDate.setText(today);

        Button btn1 = (Button) findViewById(R.id.btnsave);
        btn1.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btncancel);
        btn3.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsave:
                Intent intent = new Intent(this, showMyDiary.class);
                intent.putExtra("ParamDate", editDate.getText().toString());
                startActivityForResult(intent, 0);
                break;

            case R.id.btncancel:
                setResult(RESULT_CANCELED);
                break;
        }
        finish();
    }
}
