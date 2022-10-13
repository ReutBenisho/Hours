//package com.example.hours.utils;
//
//import android.content.Context;
//import android.content.res.Resources;
//import android.os.Bundle;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.Window;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.preference.EditTextPreference;
//import androidx.preference.PreferenceViewHolder;
//
//public class EditTimePreference extends EditTextPreference {
//
//    public EditTimePreference(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//    }
//
//    public EditTimePreference(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    //    /**
////     * {@inheritDoc}
////     */
////    @Override
////    protected void showDialog(Bundle state) {
////        super.showDialog(state);
////        final Resources res = getContext().getResources();
////        final Window window = getDialog().getWindow();
////        final int green = res.getColor(android.R.color.holo_green_dark);
////
////        // Title
////        final int titleId = res.getIdentifier("alertTitle", "id", "android");
////        final View title = window.findViewById(titleId);
////        if (title != null) {
////            ((TextView) title).setTextColor(green);
////        }
////
////        // Title divider
////        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
////        final View titleDivider = window.findViewById(titleDividerId);
////        if (titleDivider != null) {
////            titleDivider.setBackgroundColor(green);
////        }
////
////        // EditText
////        final View editText = window.findViewById(android.R.id.edit);
////        if (editText != null) {
////            editText.setBackground(res.getDrawable(R.drawable.apptheme_edit_text_holo_light));
////        }
////    }
//}
