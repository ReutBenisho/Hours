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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.calcUtils.CustomDate;
import com.example.hours.calcUtils.HoursInfo;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.calcUtils.UserInfo;
import com.example.hours.db.HoursDbContract.DailyReportEntry;
import com.example.hours.utils.App;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.TimestampTextWatcher;
import com.example.hours.utils.Utils;

public class MonthlyDailyReportRecyclerAdapter extends RecyclerView.Adapter<MonthlyDailyReportRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater mLayoutInflater;
    private Cursor mCursor;
    private int mIdPos;
    private int mDatePos;
    private int mArrivalPos;
    private int mExitPos;
    private HoursManager mHoursManager;

    public MonthlyDailyReportRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
        mLayoutInflater = LayoutInflater.from(mContext);
        populateColumnPositions();
        mHoursManager = HoursManager.getInstance();
    }

    private void populateColumnPositions() {
        if(mCursor == null)
            return;
        mIdPos = mCursor.getColumnIndex(DailyReportEntry._ID);
        mDatePos = mCursor.getColumnIndex(DailyReportEntry.COLUMN_DATE);
        mArrivalPos = mCursor.getColumnIndex(DailyReportEntry.COLUMN_ARRIVAL);
        mExitPos = mCursor.getColumnIndex(DailyReportEntry.COLUMN_EXIT);
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
        View itemView = mLayoutInflater.inflate(R.layout.monthly_report_daily_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("all")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.mId = mCursor.getInt(mIdPos);

        //Date
        holder.mLblDate.setText(CustomDate.convertToFormat(mCursor.getString(mDatePos), "yyyyMMdd", "dd.MM"));

        //Arrival and exit
        UserInfo userInfo = new UserInfo();
        userInfo.arrivalTime = new Timestamp(mCursor.getString(mArrivalPos));
        userInfo.exitTime = new Timestamp(mCursor.getString(mExitPos));

        String arrivalAndExit = "";
        arrivalAndExit += userInfo.arrivalTime.toString();
        arrivalAndExit += "-";
        arrivalAndExit += userInfo.exitTime.toString();
        holder.mLblArrivalAndExit.setText(arrivalAndExit);

        // Total additional or absence
        HoursInfo info = mHoursManager.CalcDayWithExit(userInfo);
        String totalStr = mHoursManager.getTotalAdditionalOrAbsenceHours(info);
        if(totalStr.charAt(0) == '-'){
            holder.mLblSignTotalHours.setText("-");
            holder.mLblSignTotalHours.setTextColor(App.getRes().getColor(R.color.red));
            holder.mLblAdditionalOrAbsenceHours.setText(totalStr.substring(1));
            holder.mLblAdditionalOrAbsenceHours.setTextColor(App.getRes().getColor(R.color.red));
        }
        else if(totalStr.charAt(0) == '+')
        {
            holder.mLblSignTotalHours.setText("+");
            holder.mLblAdditionalOrAbsenceHours.setText(totalStr.substring(1));
            Timestamp total = new Timestamp(totalStr.substring(1));
            // TODO: adjust condition to also match students
            if(total.equalsOrLessThan(Defaults.getZeroHours())){
                holder.mLblSignTotalHours.setTextColor(App.getRes().getColor(R.color.yellow));
                holder.mLblAdditionalOrAbsenceHours.setTextColor(App.getRes().getColor(R.color.yellow));
            }
            else
            {
                holder.mLblSignTotalHours.setTextColor(App.getRes().getColor(R.color.green));
                holder.mLblAdditionalOrAbsenceHours.setTextColor(App.getRes().getColor(R.color.green));
            }
        }
        else
        {
            holder.mLblSignTotalHours.setText("");
            holder.mLblAdditionalOrAbsenceHours.setText(totalStr);
            holder.mLblAdditionalOrAbsenceHours.setTextColor(App.getRes().getColor(R.color.white));
        }
    }

    @Override
    public int getItemCount() {

        return mCursor == null ? 0 : mCursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public int mId;
        public final TextView mLblDate;
        public final TextView mLblArrivalAndExit;
        public final TextView mLblSignTotalHours;
        public final TextView mLblAdditionalOrAbsenceHours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mLblDate = itemView.findViewById(R.id.lbl_current_report_date);
            mLblArrivalAndExit = itemView.findViewById(R.id.lbl_current_report_arrival_and_exit);
            mLblSignTotalHours = itemView.findViewById(R.id.lbl_current_report_sign_total_hours);
            mLblAdditionalOrAbsenceHours = itemView.findViewById(R.id.lbl_current_report_additional_or_absence_hours);
        }
    }

}
