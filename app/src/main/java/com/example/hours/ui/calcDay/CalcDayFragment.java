package com.example.hours.ui.calcDay;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.R;
import com.example.hours.Timestamp;

public class CalcDayFragment extends Fragment {

    private CalcDayViewModel mViewModel;

    private Button mBtnArrivalTime;
    private TextView mLblTxtHalfDay;
    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxt3AndHalfHours;
    private TextView mLblTxt6Hours;
    private HoursInfo mHoursInfo;
    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private Button mBtnAddMiddayRow;


    public static CalcDayFragment newInstance() {
        return new CalcDayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calc_day, container, false);

        mBtnArrivalTime = view.findViewById(R.id.btn_arrival_time);
        mLblTxtHalfDay = view.findViewById(R.id.lbl_txt_half_day);
        mLblTxtFullDay = view.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = view.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxt3AndHalfHours = view.findViewById(R.id.lbl_txt_3_and_half_hours);
        mLblTxt6Hours = view.findViewById(R.id.lbl_txt_6_hours);
        mLayoutMiddayTimes = view.findViewById(R.id.layout_midday_exit_and_arrival_times);
        mBtnAddMiddayRow = view.findViewById(R.id.btn_add_midday_row);

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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view);
            }
        };
        mBtnArrivalTime.setOnClickListener(listener);

        updateHours();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcDayViewModel.class);
    }


    private void addMiddayRowToLayout() {
        View viewMiddayRow = getLayoutInflater().inflate(R.layout.row_midday_exit_and_arrival_times, null, false);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popTimePicker(view);
            }
        };
        Button btnMiddayExit = viewMiddayRow.findViewById(R.id.btn_midday_exit);
        btnMiddayExit.setOnClickListener(listener);
        Button btnMiddayArrival = viewMiddayRow.findViewById(R.id.btn_midday_arrival);
        btnMiddayArrival.setOnClickListener(listener);

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
        updateHours();
    }

    private void GetTimestampsFromViewIndex(int i, Timestamp exit, Timestamp arrival){
        View middayView = mLayoutMiddayTimes.getChildAt(i);
        Button middayExit = middayView.findViewById(R.id.btn_midday_exit);
        Button middayArrival = middayView.findViewById(R.id.btn_midday_arrival);
        exit.setTime(middayExit.getText().toString());
        arrival.setTime(middayArrival.getText().toString());
    }
    private void updateHours() {
        mHoursInfo.mArrivalTime.setTime(mBtnArrivalTime.getText().toString());
        mHoursInfo.mCustomBreaks.clear();
        mHoursInfo.mTookCustomBreak.clear();
        for(int i = 0; i < mLayoutMiddayTimes.getChildCount(); i++){
            Timestamp middayExit = new Timestamp();
            Timestamp middayArrival = new Timestamp();
            GetTimestampsFromViewIndex(i, middayExit, middayArrival);
            mHoursInfo.mCustomBreaks.add(new HoursInfo.Midday(middayExit, middayArrival));
            mHoursInfo.mTookCustomBreak.add(false);
        }
        mHoursInfo = mHoursManager.CalcDayNoExit(mHoursInfo);
        mLblTxtHalfDay.setText(mHoursInfo.mHalfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.mFullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.mZeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.m3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.m6Hours.toString());
    }

    public void popTimePicker(View btnView) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Timestamp viewTimestamp = new Timestamp(selectedHour, selectedMinute);
                ((Button)btnView).setText(viewTimestamp.toString());
                if(btnView.getId() == R.id.btn_midday_exit){
                    ((Button)((ConstraintLayout)btnView.getParent()).findViewById(R.id.btn_midday_arrival)).setText(viewTimestamp.toString());
                }

                updateHours();
            }
        };
        Timestamp timestamp = new Timestamp();
        timestamp.setTime(((Button)btnView).getText().toString());
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        timestamp.getHour(), timestamp.getMinute(),
                        true);
        timePickerDialog.setTitle(getString(R.string.enter_time));
        timePickerDialog.show();
    }
}