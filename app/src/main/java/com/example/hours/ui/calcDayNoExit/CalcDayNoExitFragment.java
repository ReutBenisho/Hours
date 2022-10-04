package com.example.hours.ui.calcDayNoExit;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hours.Break;
import com.example.hours.BreakTimes;
import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.OnUpdateListener;
import com.example.hours.R;
import com.example.hours.Timestamp;
import com.example.hours.Utils;

public class CalcDayNoExitFragment extends Fragment implements OnUpdateListener {

    private CalcDayNoExitViewModel mViewModel;
    public static final String TAG = "CALC_DAY_NO_EXIT_TAG";

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


    public static CalcDayNoExitFragment newInstance() {
        return new CalcDayNoExitFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.addListener(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calc_day_no_exit, container, false);

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
                Utils.addMiddayRowToLayout(getLayoutInflater(), mLayoutMiddayTimes, getContext());
            }
        });

        mHoursManager = HoursManager.getInstance();
        mHoursInfo = new HoursInfo();
        mHoursInfo.arrivalTime = new Timestamp(7, 30);
        mBtnArrivalTime.setText(mHoursInfo.arrivalTime.toString());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.popTimePicker(view, getContext());
            }
        };
        mBtnArrivalTime.setOnClickListener(listener);

        updateHours();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcDayNoExitViewModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.removeListener(this);
    }

    private void updateHours() {
        mHoursInfo.arrivalTime.setTime(mBtnArrivalTime.getText().toString());
        mHoursInfo.customBreaks.clear();
        for(int i = 0; i < mLayoutMiddayTimes.getChildCount(); i++){
            Timestamp middayExit = new Timestamp();
            Timestamp middayArrival = new Timestamp();
            Utils.GetTimestampsFromViewIndex(mLayoutMiddayTimes, i, middayExit, middayArrival);
            BreakTimes customBreak = new BreakTimes(middayExit, middayArrival);
            mHoursInfo.customBreaks.add(new Break(customBreak, false));
        }
        mHoursInfo = mHoursManager.CalcDayNoExit(mHoursInfo);
        mLblTxtHalfDay.setText(mHoursInfo.halfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.fullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.zeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.additional3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.additional6Hours.toString());
    }

    @Override
    public void onUpdate(OnUpdateListener listener) {
        if(listener == this)
            updateHours();

    }
}