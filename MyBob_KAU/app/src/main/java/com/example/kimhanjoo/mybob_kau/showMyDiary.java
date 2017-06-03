package com.example.kimhanjoo.mybob_kau;

        import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.auth.api.Auth;
        import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

public class showMyDiary extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef;
    DatabaseReference mConditionRef1 = mRootRef.child("Diary");
    DatabaseReference mchildRef;
    DatabaseReference mchild1Ref;
    DatabaseReference mchild2Ref;
    DatabaseReference mchild3Ref;
    DatabaseReference mchild4Ref;
    DatabaseReference mchild5Ref;
    DatabaseReference mchild6Ref;


    String today;
    TextView  editTitle, editTime, editMemo;
    TextView editDate;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_my_diary);



        editDate = (TextView) findViewById(R.id.editdate);
        editTitle = (TextView) findViewById(R.id.edittitle);
        editTime = (TextView) findViewById(R.id.edittime);
        editMemo = (TextView) findViewById(R.id.editmemo);
        int mId;
        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.KOREA);
        String str_date = df.format(new Date());
        SimpleDateFormat dd = new SimpleDateFormat("HH:mm",Locale.KOREA);
        String str_time = dd.format(new Date());

        Intent intent = getIntent();
        today = intent.getStringExtra("ParamDate");
        mAuth = FirebaseAuth.getInstance();
        editDate.setText(today);
        Log.d("ageagaesgeasgaesgasga",today);

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
                    mchild1Ref = mchildRef.child(editDate.getText().toString());
                    mchild2Ref=mchild1Ref.child("제목");
                    mchild3Ref=mchild1Ref.child("시간");
                    mchild4Ref=mchild1Ref.child("내용");
                    mchild2Ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String temp = dataSnapshot.getValue(String.class);
                            editTitle.setText(temp);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                    mchild3Ref.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             String temp = dataSnapshot.getValue(String.class);
                             editTime.setText(temp);
                         }
                         @Override
                         public void onCancelled(DatabaseError databaseError) {
                         }
                     });
                    mchild4Ref.addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                             String temp = dataSnapshot.getValue(String.class);
                             editMemo.setText(temp);
                         }
                         @Override
                         public void onCancelled(DatabaseError databaseError) {
                         }
                     });
                }
            }
        };
    }



    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();

    }
}
