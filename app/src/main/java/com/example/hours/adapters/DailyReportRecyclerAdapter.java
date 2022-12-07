package com.example.hours.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.text.TextWatcher;
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
import com.example.hours.calcUtils.CustomDate;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.db.DailyReport;
import com.example.hours.utils.App;
import com.example.hours.utils.TimestampTextWatcher;
import com.example.hours.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DailyReportRecyclerAdapter extends RecyclerView.Adapter<DailyReportRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private TextWatcher mTimestampTextWatcher;
    private Cursor mCursor;
    private int mIdPos;
    private int mDatePos;
    private int mArrivalPos;
    private int mExitPos;

    public DailyReportRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();
    }

    private void populateColumnPositions() {
        if(mCursor == null)
            return;
        mIdPos = mCursor.getColumnIndex(HoursProviderContract.DailyReports._ID);
        mDatePos = mCursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_DATE);
        mArrivalPos = mCursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_ARRIVAL);
        mExitPos = mCursor.getColumnIndex(HoursProviderContract.DailyReports.COLUMN_EXIT);
    }

    public void changeCursor(Cursor cursor){
        if(mCursor != null)
            mCursor.close();
        mCursor = cursor;
        populateColumnPositions();
        notifyDataSetChanged();
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
        mCursor.moveToPosition(position);
        holder.mId = mCursor.getInt(mIdPos);
        holder.mLblDate.setText(CustomDate.convertToFormat(mCursor.getString(mDatePos), "yyyyMMdd", "dd.MM"));
        holder.mTxtArrival.setText(new Timestamp(mCursor.getString(mArrivalPos)).toString());
        holder.mTxtExit.setText(new Timestamp(mCursor.getString(mExitPos)).toString());
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
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
