//package com.example.hours.utils;
//
//import android.text.InputType;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.preference.EditTextPreference;
//
//import java.util.Calendar;
//
//public class TimeBindEditTextListener implements EditTextPreference.OnBindEditTextListener {
//
//    private String current = "";
//    private String hhmm = "HHMM";
//
//    @Override
//    public void onBindEditText(@NonNull EditText editText) {
//        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
//        editText.addTextChangedListener(new TimestampTextWatcher(editText));
//    }
//}
