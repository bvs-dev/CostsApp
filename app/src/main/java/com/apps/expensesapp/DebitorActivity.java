package com.apps.costsapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DebitorActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "EXTRA_ID";

    private DebitorList mList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debitor);

        List<Debitor> data = new ArrayList<>();
        data.add(new Debitor(1, "name1", 150, 20));
        data.add(new Debitor(2, "name2", 1501, 2000));
        data.add(new Debitor(3, "name3", 1, 20));

        DebitorList list = new DebitorList(findViewById(R.id.list));
        list.setDebitors(data);
    }
}
