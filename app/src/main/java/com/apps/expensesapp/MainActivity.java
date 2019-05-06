package com.apps.costsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView mListTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        mListTv = findViewById(R.id.debitor_list);
        mListTv.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, DebitorActivity.class)
        .putExtra(DebitorActivity.EXTRA_ID, "message")));
    }
}
