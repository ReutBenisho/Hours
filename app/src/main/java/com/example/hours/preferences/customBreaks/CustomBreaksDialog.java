package com.example.hours.preferences.customBreaks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.utils.Defaults;

import java.util.ArrayList;

public class CustomBreaksDialog extends PreferenceDialogFragmentCompat{
    private final CustomBreaksPreference preference;
    private CustomBreaksRecyclerAdapter mCustomBreaksRecyclerAdapter;
    private MiddaysRecyclerAdapter mMiddaysRecyclerAdapter;
    private ArrayList<CustomBreak> mBreaksList;
    private boolean useCustomBreaksAdapter = true;

    public CustomBreaksDialog(CustomBreaksPreference preference) {
        this.preference = preference;

        final Bundle b = new Bundle();
        b.putString(ARG_KEY, preference.getKey());
        setArguments(b);
    }

    @Override
    protected View onCreateDialogView(Context context) {
        ConstraintLayout dialogView = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.custom_breaks_preference_list, null);

        RecyclerView recyclerBreaks = dialogView.findViewById(R.id.list_custom_breaks);
        LinearLayoutManager notesLayoutManager = new LinearLayoutManager(context);
        recyclerBreaks.setLayoutManager(notesLayoutManager);

        mBreaksList = preference.getValue();
        mCustomBreaksRecyclerAdapter = new CustomBreaksRecyclerAdapter(context, mBreaksList);
        mMiddaysRecyclerAdapter = new MiddaysRecyclerAdapter(context, mBreaksList);
        if(useCustomBreaksAdapter)
            recyclerBreaks.setAdapter(mCustomBreaksRecyclerAdapter);
        else
            recyclerBreaks.setAdapter(mMiddaysRecyclerAdapter);

        return dialogView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(useCustomBreaksAdapter)
            mCustomBreaksRecyclerAdapter.notifyDataSetChanged();
        else
            mMiddaysRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {

            if (preference.callChangeListener(mBreaksList)) {
                preference.setValue(mBreaksList);
            }
        }
    }

}

