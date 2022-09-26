package com.example.hours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
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

        mHoursManager = HoursManager.getInstance();
        mHoursInfo = new HoursInfo();
        mHoursInfo.mArrivalTime = new Timestamp(7, 30);
        mBtnArrivalTime.setText(mHoursInfo.mArrivalTime.toString());
        mHoursInfo.mCustomBreaks.set(0, new Pair(new Timestamp(0, 0), new Timestamp(0, 0)));
        mBtnMiddayExitTime.setText("00:00");
        mBtnMiddayArrivalTime.setText("00:00");

        updateHours();
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