//package com.example.hours.utils;
//
//import android.os.Bundle;
//
//import androidx.preference.PreferenceDialogFragmentCompat;
//
//public class EditTimeDialog extends PreferenceDialogFragmentCompat {
//    private static EditTimeDialog mInstance;
//
//    private EditTimeDialog(){
//
//    }
//
//    public static EditTimeDialog newInstance(String key){
//        if(mInstance == null)
//            mInstance = new EditTimeDialog();
//        Bundle args = new Bundle();
//        args.putString("key", key);
//        mInstance.setArguments(args);
//        return mInstance;
//    }
//
//    @Override
//    public void onDialogClosed(boolean positiveResult) {
//
//    }
//}
