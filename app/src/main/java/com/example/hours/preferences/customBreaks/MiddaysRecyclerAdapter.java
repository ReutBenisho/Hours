package com.example.hours.preferences.customBreaks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.TimestampTextWatcher;

import java.util.ArrayList;

public class MiddaysRecyclerAdapter extends RecyclerView.Adapter<MiddaysRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final ArrayList<CustomBreak> mCustomBreaksList;

    public MiddaysRecyclerAdapter(Context context, ArrayList<CustomBreak> breaks) {
        mContext = context;
        mCustomBreaksList = breaks;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.row_midday_exit_and_arrival_times, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("all")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomBreak breakPref = mCustomBreaksList.get(holder.getAdapterPosition());
        holder.mTxtMiddayExit.setText(breakPref.times.start.toString());
        holder.mTxtMiddayExit.addTextChangedListener(new TimestampTextWatcher(holder.mTxtMiddayExit));
        holder.mTxtMiddayArrival.setText(breakPref.times.end.toString());
        holder.mTxtMiddayArrival.addTextChangedListener(new TimestampTextWatcher(holder.mTxtMiddayArrival));
        holder.mBtnRemoveMidday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomBreaksList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                notifyItemRangeChanged(holder.getAdapterPosition(), getItemCount());

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCustomBreaksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final EditText mTxtMiddayExit;
        public final EditText mTxtMiddayArrival;
        public final View mBtnRemoveMidday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtMiddayExit = itemView.findViewById(R.id.txt_midday_exit_time);
            mTxtMiddayArrival = itemView.findViewById(R.id.txt_midday_arrival_time);
            mBtnRemoveMidday = itemView.findViewById(R.id.img_remove_midday);
        }
    }

}
