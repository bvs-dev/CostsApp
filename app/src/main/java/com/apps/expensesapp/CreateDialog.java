package com.apps.expensesapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class CreateDialog extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.d_create, null, false);
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.create_entry)
                .setPositiveButton(R.string.create, (dialog, which) -> {
                    String name = ((EditText) view.findViewById(R.id.d_create_name))
                            .getText().toString();

                    double userDebt = Double.parseDouble(((EditText)(view.findViewById(R.id.d_create_userdebt)))
                            .getText().toString());

                    double debt = Double.parseDouble(((EditText)(view.findViewById(R.id.d_create_debt)))
                            .getText().toString());

                    DebtorsRepo.getInstance(getContext()).addDebtor(name, userDebt, debt);

                })
                .setView(view)
                .create();
    }
}
