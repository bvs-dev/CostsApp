package com.apps.expensesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DebtorsActivity extends AppCompatActivity {

    private DebtorList mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debtors);
        mList = new DebtorList(findViewById(R.id.list));
        onDataChanged();
    }

    public void onDataChanged() {
        mList.setDebtors(DebtorsRepo.getInstance(this).getDebtors());
    }
}
