package com.apps.expensesapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditDebtActivity extends AppCompatActivity {

    private EditText mAmountEt;
    private TextView mMinusTv;
    private TextView mPlusTv;
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_CHOICE = "EXTRA_CHOICE";

    private int mDebtorId;
    private String mChoice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_debt_editor);

        Bundle extras = getIntent().getExtras();
        mDebtorId = extras.getInt(EXTRA_ID, -1);
        mChoice = extras.getString(EXTRA_CHOICE);

        mAmountEt = findViewById(R.id.a_editor_amount);
        mMinusTv = findViewById(R.id.a_editor_minus);
        mPlusTv = findViewById(R.id.a_editor_plus);

        mMinusTv.setOnClickListener(v -> setDebtValue("minus"));

        mPlusTv.setOnClickListener(v -> setDebtValue("plus"));
    }

    private void setDebtValue(String operation) {

        int amount = inputToInt(mAmountEt);
        Debtor debtor = DebtorsRepo.getInstance(this).getDebtor(mDebtorId);
        int result;

        if (isValid(amount)) {
            if (operation.equals("minus")) {
                if (mChoice.equals("person")) {
                    result = debtor.debt - amount;
                    DebtorsRepo.getInstance(this).setDebtVal(debtor, result);
                } else if (mChoice.equals("user")) {
                    result = debtor.userDebt - amount;
                    DebtorsRepo.getInstance(this).setUserDebtVal(debtor, result);
                }
            } else  if (operation.equals("plus")) {
                if (mChoice.equals("person")) {
                    result = debtor.debt + amount;
                    DebtorsRepo.getInstance(this).setDebtVal(debtor, result);
                } else if (mChoice.equals("user")) {
                    result = debtor.userDebt + amount;
                    DebtorsRepo.getInstance(this).setUserDebtVal(debtor, result);
                }
            }
            finish();
        }
    }

    private int inputToInt(EditText editText) {

        CharSequence inputText = editText.getText();
        double doubleVal = inputText.length() != 0 ? Double.parseDouble(inputText.toString()) : 0;
        doubleVal = Math.round(doubleVal*100)/100d;

        return  (int) (doubleVal * 100);
    }

    private boolean isValid(int amount) {

        boolean isValid = true;

        View view = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.l_toast, null, false);
        TextView text = view.findViewById(R.id.i_toast_message);
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);

        if (amount > 21474836) {
            text.setText(getString(R.string.out_range));
            isValid = false;
            toast.show();
        }
        return isValid;
    }
}
