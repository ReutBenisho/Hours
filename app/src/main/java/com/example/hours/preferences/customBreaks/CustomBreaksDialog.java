package com.example.hours.preferences.customBreaks;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceDialogFragmentCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.BreakTimes;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.LocaleHelper;

import java.util.ArrayList;

public class CustomBreaksDialog extends PreferenceDialogFragmentCompat implements AddCustomBreakDialog.AddCustomBreakListener {
    private final CustomBreaksPreference preference;
    private RecyclerView.Adapter mCustomBreaksRecyclerAdapter;
    private ArrayList<CustomBreak> mBreaksList;
    private boolean useCustomBreaksAdapter = true;

    public CustomBreaksDialog(CustomBreaksPreference preference) {
        this.preference = preference;

        final Bundle b = new Bundle();
        b.putString(ARG_KEY, preference.getKey());
        setArguments(b);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(LocaleHelper.onAttach(context));
    }

    @Override
    protected View onCreateDialogView(Context context) {
        ConstraintLayout dialogView = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.custom_breaks_preference_list, null);

        RecyclerView recyclerBreaks = dialogView.findViewById(R.id.list_custom_breaks);
        LinearLayoutManager notesLayoutManager = new LinearLayoutManager(context);
        recyclerBreaks.setLayoutManager(notesLayoutManager);

        mBreaksList = preference.getValue();
        if(useCustomBreaksAdapter)
            mCustomBreaksRecyclerAdapter = new CustomBreaksRecyclerAdapter(context, mBreaksList);
        else
            mCustomBreaksRecyclerAdapter = new MiddaysRecyclerAdapter(context, mBreaksList);

        recyclerBreaks.setAdapter(mCustomBreaksRecyclerAdapter);

        Button btn = dialogView.findViewById(R.id.button_add_custom_break);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCustomBreakDialog addBreakDialog = new AddCustomBreakDialog();
                addBreakDialog.onAttach(context);
                //addBreakDialog.show();
            }
        });
        return dialogView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mCustomBreaksRecyclerAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {

            if (preference.callChangeListener(mBreaksList)) {
                preference.setValue(mBreaksList);
            }
        }
    }

    @Override
    public void addBreak(String breakStart, String breakEnd, boolean[] days) {
        CustomBreak newBreak = new CustomBreak(true,
                new BreakTimes(new Timestamp(breakStart), new Timestamp(breakEnd)),
                days);
        int position = mBreaksList.size();
        mBreaksList.add(newBreak);
        mCustomBreaksRecyclerAdapter.notifyItemInserted(position);
        mCustomBreaksRecyclerAdapter.notifyItemRangeChanged(position, mCustomBreaksRecyclerAdapter.getItemCount());

    }
}

