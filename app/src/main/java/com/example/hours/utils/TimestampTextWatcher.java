package com.example.hours.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.Calendar;

public class TimestampTextWatcher implements TextWatcher {
    private final EditText mEditText;
    private String current = "";
    private String hhmm = "HHMM";
    private Calendar cal = Calendar.getInstance();

    public TimestampTextWatcher(EditText editTextView){
        mEditText = editTextView;
    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().equals(current)) {
            String clean = s.toString().replaceAll("[^\\d.]", "");
            String cleanC = current.replaceAll("[^\\d.]", "");

            int cl = clean.length();
            int sel = cl;
            for (int i = 2; i <= cl && i < 4; i += 2) {
                sel++;
            }
            //Fix for pressing delete next to a forward slash
            if (clean.equals(cleanC))
                sel--;

            if (clean.length() < 4){
                clean = clean + hhmm.substring(clean.length());
            }else{
                //This part makes sure that when we finish entering numbers
                //the date is correct, fixing it otherwise
                int hour = Integer.parseInt(clean.substring(0,2));
                int minute = Integer.parseInt(clean.substring(2,4));
                clean = String.format("%02d%02d",hour, minute);
            }

            clean = String.format("%s:%s", clean.substring(0, 2),
                    clean.substring(2, 4));

            sel = sel < 0 ? 0 : sel;
            current = clean;
            mEditText.setText(current);
            mEditText.setSelection(sel < current.length() ? sel : current.length());
        }
    }
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
