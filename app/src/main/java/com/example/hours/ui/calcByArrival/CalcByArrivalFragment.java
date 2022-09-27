package com.example.hours.ui.calcByArrival;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Pair;
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

public class CalcByArrivalFragment extends Fragment {

    private CalcByArrivalViewModel mViewModel;

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


    public static CalcByArrivalFragment newInstance() {
        return new CalcByArrivalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc_by_arrival, container, false);

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
        mHoursInfo.mCustomBreaks.set(0, new Pair(new Timestamp(0, 0), new Timestamp(0, 0)));

        updateHours();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcByArrivalViewModel.class);
        // TODO: Use the ViewModel
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

    private void updateHours() {
        mHoursInfo = mHoursManager.GetInfoByArrivalTime(mHoursInfo);
        mLblTxtHalfDay.setText(mHoursInfo.mHalfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.mFullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.mZeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.m3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.m6Hours.toString());
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
                new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                        GetTimestampByView(view).getHour(), GetTimestampByView(view).getMinute(),
                        true);
        timePickerDialog.setTitle("הזן שעה");
        timePickerDialog.show();
    }
}