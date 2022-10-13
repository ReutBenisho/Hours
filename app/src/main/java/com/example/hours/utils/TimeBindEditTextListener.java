package com.example.hours.utils;

import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;

import java.util.Calendar;

public class TimeBindEditTextListener implements EditTextPreference.OnBindEditTextListener {

    private String current = "";
    private String hhmm = "HHMM";

    @Override
    public void onBindEditText(@NonNull EditText editText) {
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.addTextChangedListener(new TimestampTextWatcher(editText));
//        if (!editText.toString().equals(current)) {
//            String str = editText.getText().toString();
//            String clean = str.replaceAll("[^\\d.]", "");
//            String cleanC = current.replaceAll("[^\\d.]", "");
//
//            int cl = clean.length();
//            int sel = cl;
//            for (int i = 2; i <= cl && i < 4; i += 2) {
//                sel++;
//            }
//            //Fix for pressing delete next to a forward slash
//            if (clean.equals(cleanC))
//                sel--;
//
//            if (clean.length() < 4){
//                clean = clean + hhmm.substring(clean.length());
//            }else{
//                //This part makes sure that when we finish entering numbers
//                //the date is correct, fixing it otherwise
//                int hour = Integer.parseInt(clean.substring(0,2));
//                int minute = Integer.parseInt(clean.substring(2,4));
//                clean = String.format("%02d%02d",hour, minute);
//            }
//
//            clean = String.format("%s:%s", clean.substring(0, 2),
//                    clean.substring(2, 4));
//
//            sel = sel < 0 ? 0 : sel;
//            current = clean;
//            editText.setText(current);
//            editText.setSelection(sel < current.length() ? sel : current.length());
//        }
    }
}
