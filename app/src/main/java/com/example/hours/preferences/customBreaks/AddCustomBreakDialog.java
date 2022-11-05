package com.example.hours.preferences.customBreaks;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.hours.R;

public class AddCustomBreakDialog extends AppCompatDialogFragment {
    private EditText mTxtBreakStart;
    private EditText mTxtBreakEnd;
    private AppCompatCheckBox[] mCkbxDays;
    private AddCustomBreakListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_custom_break_preference, null);

        builder.setView(view)
                .setTitle("Login")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String breakStart = mTxtBreakStart.getText().toString();
                        String breakEnd = mTxtBreakEnd.getText().toString();
                        boolean[] days = new boolean[6];
                        for(int j = 0; j < 6; j ++)
                            days[j] = mCkbxDays[j].isChecked();
                        listener.addBreak(breakStart,
                                breakEnd,
                                days);
                    }
                });

        mTxtBreakStart = view.findViewById(R.id.txt_break_start_time);
        mTxtBreakEnd = view.findViewById(R.id.txt_break_end_time);
        mCkbxDays = new AppCompatCheckBox[6];
        mCkbxDays[0] = view.findViewById(R.id.btn_sunday);
        mCkbxDays[1] = view.findViewById(R.id.btn_monday);
        mCkbxDays[2] = view.findViewById(R.id.btn_tuesday);
        mCkbxDays[3] = view.findViewById(R.id.btn_wednesday);
        mCkbxDays[4] = view.findViewById(R.id.btn_thursday);
        mCkbxDays[5] = view.findViewById(R.id.btn_friday);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (AddCustomBreakListener) context;
    }


    public interface AddCustomBreakListener {
        void addBreak(String breakStart, String breakEnd, boolean[] days);
    }
}
