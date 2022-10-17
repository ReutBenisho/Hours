package com.example.hours.preferences.customBreaks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.utils.Defaults;

import java.util.ArrayList;

public class CustomBreaksRecyclerAdapter extends RecyclerView.Adapter<CustomBreaksRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<CustomBreak> mCustomBreaksList;

    public CustomBreaksRecyclerAdapter(Context context, ArrayList<CustomBreak> notes) {
        mContext = context;
        mCustomBreaksList = notes;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.custom_break_preference_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("all")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomBreak breakPref = mCustomBreaksList.get(holder.getAdapterPosition());
        holder.mCkbxIsEnabled.setChecked(breakPref.isEnabled);
        holder.mCkbxIsEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mCustomBreaksList.get(holder.getAdapterPosition()).isEnabled = b;
            }
        });
        holder.mTxtBreakTimes.setText(breakPref.times.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEnabled = mCustomBreaksList.get(holder.getAdapterPosition()).isEnabled;
                mCustomBreaksList.get(holder.getAdapterPosition()).isEnabled = !isEnabled;
                holder.mCkbxIsEnabled.setChecked(!isEnabled);
            }
        });
    }

    @Override
    public int getItemCount() {

        return Defaults.getCustomBreaksList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final CheckBox mCkbxIsEnabled;
        public final TextView mTxtBreakTimes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCkbxIsEnabled = itemView.findViewById(R.id.ckbx_customBreak_isEnabled);
            mTxtBreakTimes = itemView.findViewById(R.id.txt_customBreak_times);
        }
    }

}
