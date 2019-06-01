package com.apps.expensesapp;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

public class ConfirmDeleteDialog extends AppCompatDialogFragment {

    public interface Host {

        void onConfirm();

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(requireContext())
                .setTitle(R.string.delete_entry)
                .setPositiveButton(R.string.yes, (dialog, which) ->
                        ((Host) requireActivity()).onConfirm())
                .setNegativeButton(R.string.cancel, null)
                .create();

    }
}
