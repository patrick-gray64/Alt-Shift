package com.assignment.alt_shift_cs991;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.widget.Toast;

public class ShiftSwapDialogue extends DialogFragment {
//hello
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.swap_message)
                .setPositiveButton(R.string.accept_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getContext(), "Your shift has been swapped!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(R.string.reject_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the proposed swap
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();


    }
}