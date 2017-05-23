package com.example.kimhanjoo.mybob_kau;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SingleItemView extends Activity {
    // Declare Variables
    TextView txttimes;
    TextView txttitle;
    TextView txtmemo;
    String times;
    String title;
    String memo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleitemview);
        // Retrieve data from MainActivity on item click event
        Intent i = getIntent();
        // Get the results of rank
        times = i.getStringExtra("rank");
        // Get the results of country
        title = i.getStringExtra("country");
        // Get the results of population
        memo = i.getStringExtra("population");

        // Locate the TextViews in singleitemview.xml
        txttimes = (TextView) findViewById(R.id.rank);
        txttitle = (TextView) findViewById(R.id.country);
        txtmemo = (TextView) findViewById(R.id.population);

        // Load the results into the TextViews
        txttimes.setText(times);
        txttitle.setText(title);
        txtmemo.setText(memo);
    }
}
