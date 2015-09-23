package se.mah.k3lara.testmap2;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class MarkerClickDialog extends DialogFragment implements Dialog.OnClickListener{


    public MarkerClickDialog() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Your Florist")
                .setMessage("THis is your Local Florist")
                .setPositiveButton("OK",this)
                .setNegativeButton("Cancel",this);
        return dialog.create();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case Dialog.BUTTON_POSITIVE:
                    break;
            }
    }
}
