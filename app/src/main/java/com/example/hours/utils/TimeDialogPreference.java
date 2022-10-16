//package com.example.hours.utils;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.NumberPicker;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.preference.DialogPreference;
//
//public class TimeDialogPreference extends DialogPreference {
//
//    private NumberPicker mPicker;
//    private int mNumber = 1024;
//
//    public TimeDialogPreference(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public TimeDialogPreference(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        setPositiveButtonText(android.R.string.ok);
//        setNegativeButtonText(android.R.string.cancel);
//    }
////
////    @Override
////    protected View onCreateDialogView() {
////        mPicker = new NumberPicker(getContext());
////        mPicker.setMinValue(1024);
////        mPicker.setMaxValue(2048);
////        mPicker.setValue(mNumber);
////        return mPicker;
////    }
////
////    @Override
////    protected void onDialogClosed(boolean positiveResult) {
////        if (positiveResult) {
////            mPicker.clearFocus();
////            setValue(mPicker.getValue());
////        }
////    }
//
//    @Override
//    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
//        setValue(restoreValue ? getPersistedInt(mNumber) : (Integer) defaultValue);
//    }
//
//    public void setValue(int value) {
//        if (shouldPersist()) {
//            persistInt(value);
//        }
//
//        if (value != mNumber) {
//            mNumber = value;
//            notifyChanged();
//        }
//    }
//
//    @Override
//    protected Object onGetDefaultValue(TypedArray a, int index) {
//        return a.getInt(index, 0);
//    }
//
//
//}
