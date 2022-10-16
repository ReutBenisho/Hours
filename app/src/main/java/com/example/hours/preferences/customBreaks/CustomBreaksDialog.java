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
    private ArrayList<CustomBreak> mBreaksList;

    public CustomBreaksDialog(CustomBreaksPreference preference) {
        this.preference = preference;

        final Bundle b = new Bundle();
        b.putString(ARG_KEY, preference.getKey());
        setArguments(b);
    }

    @Override
    protected View onCreateDialogView(Context context) {
        ConstraintLayout dialogView = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.custom_breaks_preference_list, null);

        RecyclerView recyclerNotes = dialogView.findViewById(R.id.list_custom_breaks);
        LinearLayoutManager notesLayoutManager = new LinearLayoutManager(context);
        recyclerNotes.setLayoutManager(notesLayoutManager);

        mBreaksList = Defaults.getCustomBreaksList();
        mCustomBreaksRecyclerAdapter = new CustomBreaksRecyclerAdapter(context, mBreaksList);
        recyclerNotes.setAdapter(mCustomBreaksRecyclerAdapter);

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
            Defaults.clearCustomBreaksList();
            for(int i = 0; i < mBreaksList.size(); i++)
            {
                Defaults.addBreakToList(mBreaksList.get(i));
            }
        }
    }

}

