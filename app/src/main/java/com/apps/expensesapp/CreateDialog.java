package com.apps.expensesapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDialog extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog (Bundle savedInstanceState) {


        View mView = LayoutInflater.from(getContext())
                .inflate(R.layout.d_create, null, false);

        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.create_entry)
                .setPositiveButton(R.string.create, (dialog, which) -> {

                    EditText nameET, userDebtET, debtET;

                    nameET = mView.findViewById(R.id.d_create_name);
                    String name = nameET.getText().toString();

                    userDebtET = mView.findViewById(R.id.d_create_userdebt);
                    int userDebt = inputToInt(userDebtET);

                    debtET = mView.findViewById(R.id.d_create_debt);
                    int debt = inputToInt(debtET);

                    if (isValid(name, dialog, userDebt, debt)) {
                        DebtorsRepo.getInstance(CreateDialog.this.getContext())
                                .addDebtor(name, userDebt, debt);
                    }
                })
                .setView(mView)
                .create();
    }


    private boolean isValid(String name, DialogInterface dialog, int userDebt, int debt) {
        boolean isValid = true;

        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.l_toast, null, false);
        TextView text = view.findViewById(R.id.i_toast_message);
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(view);

        if (name.trim().equals("")) {
            text.setText(getString(R.string.name_valid));
            isValid = false;
            toast.show();
            dialog.cancel();
        } else if (userDebt > 21474836 || debt > 21474836) {
            text.setText(getString(R.string.out_range));
            isValid = false;
            toast.show();
            dialog.cancel();
        }

        return isValid;
    }


    private int inputToInt(EditText editText) {

        CharSequence inputText = editText.getText();
        double doubleVal = inputText.length() != 0 ? Double.parseDouble(inputText.toString()) : 0;
        doubleVal = Math.round(doubleVal*100)/100d;

        return  (int) (doubleVal * 100);
    }




}
