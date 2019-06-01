package com.apps.expensesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView mListButtonTv;
    TextView mBalance;
    List<Debtor> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
        mBalance = findViewById(R.id.a_main_balance);
        mData = DebtorsRepo.getInstance(this).getDebtors();
        mBalance.setText(setBalance());

        mListButtonTv = findViewById(R.id.a_main_openlist);
        mListButtonTv.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, DebtorsActivity.class)));
    }

    public double getBalance(List<Debtor> data) {

        int balance = 0;

        for (Debtor debtor: data) {
            balance += debtor.gap;
        }

        return balance/100d;
    }

    public String setBalance() {

        NumberFormat formatter = new DecimalFormat("#0.00");

        double balance = getBalance(mData);
        String negative = "−" + formatter.format(balance*(-1));
        String positive = "+" + formatter.format(balance);

        if (balance > 0) return positive;
        else if (balance < 0) return negative;
        else return formatter.format(balance);

    }

    // TODO: 5/31/2019 add listener

}
