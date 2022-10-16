package com.example.hours.preferences.customBreaks;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.preferences.dateformat.DateFormatType;
import com.example.hours.preferences.dateformat.DateFormatValue;
import com.example.hours.utils.Defaults;

import java.util.ArrayList;
import java.util.Objects;

public class CustomBreaksPreference extends DialogPreference {
    ArrayList<CustomBreak> mBreaksList = new ArrayList<>();

    public CustomBreaksPreference(Context context) {

        super(context);
    }

    public CustomBreaksPreference(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    //לתקן ככה שיביא את הרשימה העדכנית לאחר השינויים
    //כך שבפתיחה חוזרת נראה את השינויים
    //וגם בעלייה מחדש נראה אותם
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return Defaults.getCustomBreaksList();
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
        this.mBreaksList = (ArrayList<CustomBreak>) defaultValue;
    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {

        showValue();
    }

    @Override
    public CharSequence getSummary() {
        long countBreaks = mBreaksList.stream()
                .filter(b -> b.isEnabled)
                .count();
        return countBreaks + " custom breaks";
    }

    private void showValue() {

        setSummary(getSummary());
    }
}
