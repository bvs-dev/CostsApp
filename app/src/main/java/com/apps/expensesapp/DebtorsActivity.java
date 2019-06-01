package com.apps.expensesapp;

import android.arch.lifecycle.ReportFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class DebtorsActivity extends AppCompatActivity implements DebtorsRepo.Listener{

    private DebtorList mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debtors);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.debtors);
        toolbar.setOnMenuItemClickListener(menuItem -> {

            if (menuItem.getItemId() == R.id.m_debtors_create) {
                new CreateDialog().show(getSupportFragmentManager(), null);
            }

            return true;
        });

        mList = new DebtorList(findViewById(R.id.list), new DebtorList.Listener() {
            @Override
            public void onOpen(Debtor debtor) {
                startActivity(new Intent(DebtorsActivity.this, DebtorDetailsActivity.class)
                        .putExtra(DebtorDetailsActivity.EXTRA_ID, debtor.id));
            }
        });
        onDataChanged();
        DebtorsRepo.getInstance(this).setListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DebtorsRepo.getInstance(this).removeListener(this);
    }

    public void onDataChanged() {
        mList.setDebtors(DebtorsRepo.getInstance(this).getDebtors());
    }
}
