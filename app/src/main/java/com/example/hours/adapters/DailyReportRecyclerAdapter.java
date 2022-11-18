package com.example.hours.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.db.DailyReport;
import com.example.hours.utils.App;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.TimestampTextWatcher;
import com.example.hours.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.List;

public class DailyReportRecyclerAdapter extends RecyclerView.Adapter<DailyReportRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private TextWatcher mTimestampTextWatcher;
    private final List<DailyReport> mDailyReports;

    public DailyReportRecyclerAdapter(Context context, List<DailyReport> dailyReports) {
        mContext = context;
        mDailyReports = dailyReports;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.daily_report_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("all")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyReport report = mDailyReports.get(holder.getAdapterPosition());
        holder.mId = report.getId();
        holder.mLblDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(report.getDate()));
        holder.mTxtArrival.setText((new Timestamp(report.getArrival())).toString());
        holder.mTxtExit.setText((new Timestamp(report.getExit())).toString());

    }

    @Override
    public int getItemCount() {

        return mDailyReports.size();
    }

    @Override
    public long getItemId(int position) {
        return mDailyReports.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public int mId;
        public final TextView mLblDate;
        public final EditText mTxtArrival;
        public final LinearLayout mLayoutMiddayTimes;
        public final ImageView mBtnAddMiddayRow;
        public final EditText mTxtExit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //Date
            mLblDate = itemView.findViewById(R.id.lbl_current_report_date);

            //Arrival
            mTxtArrival = itemView.findViewById(R.id.txt_arrival_time);
            mTimestampTextWatcher = new TimestampTextWatcher(mTxtArrival);
            if(mTxtArrival.getTag() == null)
            {
                mTxtArrival.addTextChangedListener(mTimestampTextWatcher);
                mTxtArrival.setTag(mTimestampTextWatcher);
            }

            //Middays
            mLayoutMiddayTimes = itemView.findViewById(R.id.layout_midday_exit_and_arrival_times);
            mBtnAddMiddayRow = itemView.findViewById(R.id.img_add_midday_row);
            mBtnAddMiddayRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.addMiddayRowToLayout(mLayoutInflater, mLayoutMiddayTimes, App.getContext());
                }
            });

            //Exit
            mTxtExit = itemView.findViewById(R.id.txt_exit_time);
            mTimestampTextWatcher = new TimestampTextWatcher(mTxtExit);
            if(mTxtExit.getTag() == null)
            {
                mTxtExit.addTextChangedListener(mTimestampTextWatcher);
                mTxtExit.setTag(mTimestampTextWatcher);
            }
        }

    }

}