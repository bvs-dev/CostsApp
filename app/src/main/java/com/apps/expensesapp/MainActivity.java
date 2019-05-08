package com.apps.expensesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String extra_id = "EXTRA_ID";
    TextView mListButtonTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        mListButtonTv = findViewById(R.id.debtor_list);
        mListButtonTv.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DebtorsActivity.class));
        });
    }



}
