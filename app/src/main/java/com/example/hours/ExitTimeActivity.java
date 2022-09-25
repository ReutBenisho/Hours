package com.example.hours;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class ExitTimeActivity extends AppCompatActivity {

    private Button mBtnArrivalTime;
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
        mLblTxtHalfDay = findViewById(R.id.lbl_txt_half_day);
        mLblTxtFullDay = findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = findViewById(R.id.lbl_txt_zero_hours);
        mLblTxt3AndHalfHours = findViewById(R.id.lbl_txt_3_and_half_hours);
        mLblTxt6Hours = findViewById(R.id.lbl_txt_6_hours);

        mHoursManager = HoursManager.getInstance();
        mHoursInfo = new HoursInfo();
        mHoursInfo.mArrivalTime = new Timestamp(7, 30);
        mBtnArrivalTime.setText(mHoursInfo.mArrivalTime.toString());

        updateHours();
    }

    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                mHoursInfo.mArrivalTime.setTime(selectedHour, selectedMinute);
                mBtnArrivalTime.setText(mHoursInfo.mArrivalTime.toString());

                updateHours();
            }
        };
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        mHoursInfo.mArrivalTime.getHour(), mHoursInfo.mArrivalTime.getMinute(),
                        true);
        timePickerDialog.setTitle("הזן שעת כניסה");
        timePickerDialog.show();
    }

    private void updateHours() {
        mHoursInfo = mHoursManager.GetInfoByArrivalTime(mHoursInfo.mArrivalTime);
        mLblTxtHalfDay.setText(mHoursInfo.mHalfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.mFullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.mZeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.m3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.m6Hours.toString());
    }
}