package com.example.hours.preferences.customBreaks;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.preference.DialogPreference;

import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.SharedPreferencesUtil;

import java.util.ArrayList;

public class CustomBreaksPreference extends DialogPreference {
    ArrayList<CustomBreak> mBreaksList = new ArrayList<>();

    public CustomBreaksPreference(Context context) {

        super(context);
    }

    public CustomBreaksPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        ArrayList<CustomBreak> list = CustomBreak.deseralize(SharedPreferencesUtil.getString("CustomBreaks"));
        return list;
    }

    @Override
    public void setDefaultValue(Object defaultValue) {
        super.setDefaultValue(defaultValue);
        this.mBreaksList = (ArrayList<CustomBreak>) defaultValue;
        Defaults.setCustomBreaks(mBreaksList);

    }

    @Override
    protected void onSetInitialValue(@Nullable Object defaultValue) {
        mBreaksList = (ArrayList<CustomBreak>) defaultValue;
       Defaults.setCustomBreaks(mBreaksList);

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

    public void setValue(ArrayList<CustomBreak> list) {
        this.mBreaksList = list;
        Defaults.setCustomBreaks(mBreaksList);
        SharedPreferencesUtil.setDefaults("CustomBreaks", CustomBreak.serialize(mBreaksList));
        showValue();
    }

    public ArrayList<CustomBreak> getValue() {
        ArrayList<CustomBreak> list = new ArrayList<>();
        for(int i = 0; i < mBreaksList.size(); i++)
            list.add(mBreaksList.get(i).copy());
        return list;
    }
}
