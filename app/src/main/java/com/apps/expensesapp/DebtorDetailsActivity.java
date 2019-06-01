package com.apps.expensesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DebtorDetailsActivity extends AppCompatActivity implements DebtorsRepo.Listener, ConfirmDeleteDialog.Host{

    private TextView mNameTv;
    private TextView mUserDebt;
    private TextView mDebt;
    private TextView mGap;
    public static final String EXTRA_ID = "EXTRA_ID";

    private int mDebtorId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debtor_details);
        mDebtorId = getIntent().getIntExtra(EXTRA_ID, -1);

        Toolbar toolbar = findViewById(R.id.details_toolbar);
        toolbar.inflateMenu(R.menu.details);
        toolbar.setOnMenuItemClickListener(menuItem -> {

            if (menuItem.getItemId() == R.id.m_details_delete) {
                new ConfirmDeleteDialog().show(getSupportFragmentManager(), null);
            }
            return true;
        });

        mUserDebt = findViewById(R.id.a_details_user_debt);
        mDebt = findViewById(R.id.a_details_debt);
        mGap = findViewById(R.id.a_details_gap);
        mNameTv = findViewById(R.id.a_details_name);

        onDataChanged();
        DebtorsRepo.getInstance(this).setListener(this);

    }

    private Debtor getDebtor() {
        return DebtorsRepo.getInstance(this).getDebtor(mDebtorId);
    }


    @Override
    public void onDataChanged() {
        NumberFormat formatter = new DecimalFormat("#0.00");

        Debtor debtor = getDebtor();
        if (debtor != null) {
            String uDText = String.format(getString(R.string.user_debt), formatter.format(debtor.userDebt/100d));
            String dText = String.format(getString(R.string.person_debt), formatter.format(debtor.debt/100d));
            String gapText = String.format(getString(R.string.gap), formatter.format(debtor.gap/100d));
            mNameTv.setText(String.valueOf(debtor.name));
            mUserDebt.setText(uDText);
            mDebt.setText(dText);
            mGap.setText(gapText);
            mNameTv.setText(String.valueOf(debtor.name));
        } else  {
            finish();
        }
    }

    @Override
    public void onConfirm() {
        DebtorsRepo.getInstance(this).deleteDebtor(mDebtorId);
    }
}
