package com.apps.expensesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DebtorDetailsActivity extends AppCompatActivity implements DebtorsRepo.Listener{

    private TextView mNameTv;
    private TextView mUserDebt;
    private TextView mDebt;
    private TextView mGap;
    public static final String EXTRA_ID = "EXTRA_ID";

    private int mDebtorId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDebtorId = getIntent().getIntExtra(EXTRA_ID, -1);
        setContentView(R.layout.a_debtor_details);
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
        Debtor debtor = getDebtor();
        String uDText = String.format(getString(R.string.user_debt), String.valueOf(debtor.userDebt));
        String dText = String.format(getString(R.string.person_debt), String.valueOf(debtor.debt));
        String gapText = String.format(getString(R.string.gap), String.valueOf(debtor.gap));
        mNameTv.setText(String.valueOf(debtor.name));
        mUserDebt.setText(uDText);
        mDebt.setText(dText);
        mGap.setText(gapText);
        mNameTv.setText(String.valueOf(debtor.name));
    }
}
