package com.example.hours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

public class ExitTimeActivity extends AppCompatActivity {

    private Button mBtnArrivalTime;
    private Button mBtnMiddayExitTime;
    private Button mBtnMiddayArrivalTime;
    private TextView mLblTxtHalfDay;
    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxt3AndHalfHours;
    private TextView mLblTxt6Hours;
    private HoursInfo mHoursInfo;
    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private Button mBtnAddMiddayRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit_time);
        mBtnArrivalTime = findViewById(R.id.btn_arrival_time);
        mBtnMiddayExitTime = findViewById(R.id.btn_midday_exit);
        mBtnMiddayArrivalTime = findViewById(R.id.btn_midday_arrival);
        mLblTxtHalfDay = findViewById(R.id.lbl_txt_half_day);
        mLblTxtFullDay = findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = findViewById(R.id.lbl_txt_zero_hours);
        mLblTxt3AndHalfHours = findViewById(R.id.lbl_txt_3_and_half_hours);
        mLblTxt6Hours = findViewById(R.id.lbl_txt_6_hours);
        mLayoutMiddayTimes = findViewById(R.id.layout_midday_exit_and_arrival_times);
        mBtnAddMiddayRow = findViewById(R.id.btn_add_midday_row);

        mBtnAddMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMiddayRowToLayout();
            }
        });

        mHoursManager = HoursManager.getInstance();
        mHoursInfo = new HoursInfo();
        mHoursInfo.mArrivalTime = new Timestamp(7, 30);
        mBtnArrivalTime.setText(mHoursInfo.mArrivalTime.toString());
        mHoursInfo.mCustomBreaks.set(0, new Pair(new Timestamp(0, 0), new Timestamp(0, 0)));

        updateHours();
    }

    private void addMiddayRowToLayout() {
        View viewMiddayRow = getLayoutInflater().inflate(R.layout.row_midday_exit_and_arrival_times, null, false);
        ImageView imgRemoveMiddayRow = viewMiddayRow.findViewById(R.id.img_remove_midday);
        imgRemoveMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeMiddayRowFromLayout(viewMiddayRow);
            }
        });
        mLayoutMiddayTimes.addView(viewMiddayRow);
    }

    private void removeMiddayRowFromLayout(View view) {
        mLayoutMiddayTimes.removeView(view);
    }

    private Timestamp GetTimestampByView(View view){
        Timestamp time = null;
        switch(view.getId()){
            case R.id.btn_arrival_time:
                time = mHoursInfo.mArrivalTime;
                break;
            case R.id.btn_midday_exit:
                time = mHoursInfo.mCustomBreaks.get(0).first;
                break;
            case R.id.btn_midday_arrival:
                time = mHoursInfo.mCustomBreaks.get(0).second;
                break;
        }
        return time;
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                GetTimestampByView(view).setTime(selectedHour, selectedMinute);
                ((Button)view).setText(GetTimestampByView(view).toString());

                updateHours();
            }
        };
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        GetTimestampByView(view).getHour(), GetTimestampByView(view).getMinute(),
                        true);
        timePickerDialog.setTitle("הזן שעה");
        timePickerDialog.show();
    }

    private void updateHours() {
        mHoursInfo = mHoursManager.GetInfoByArrivalTime(mHoursInfo);
        mLblTxtHalfDay.setText(mHoursInfo.mHalfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.mFullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.mZeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.m3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.m6Hours.toString());
    }
}