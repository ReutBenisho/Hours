package com.example.hours.ui.calcDayWithExit;

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

import com.example.hours.Defaults;
import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.OnUpdateListener;
import com.example.hours.R;
import com.example.hours.Timestamp;
import com.example.hours.Utils;

public class CalcDayWithExitFragment extends Fragment implements OnUpdateListener {

    private CalcDayWithExitViewModel mViewModel;
    public static final String TAG = "CALC_DAY_WITH_EXIT_TAG";

    @Override
    public void onDestroy() {
        super.onDestroy();
        Utils.removeListener(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.addListener(this);
    }

    private Button mBtnArrivalTime;
    private Button mBtnExitTime;
    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private HoursInfo mHoursInfo;
    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private Button mBtnAddMiddayRow;

    public static CalcDayWithExitFragment newInstance() {
        return new CalcDayWithExitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc_day_with_exit, container, false);

        mBtnArrivalTime = view.findViewById(R.id.btn_arrival_time);
        mBtnExitTime = view.findViewById(R.id.btn_exit_time);
        mLblTxtFullDay = view.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = view.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtAdditionalHours = view.findViewById(R.id.lbl_txt_additional_hours);
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
        mHoursInfo.mArrivalTime = new Timestamp(7, 30);
        mBtnArrivalTime.setText(mHoursInfo.mArrivalTime.toString());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.popTimePicker(view, getContext());
            }
        };
        mBtnArrivalTime.setOnClickListener(listener);
        mBtnExitTime.setOnClickListener(listener);

        updateHours();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcDayWithExitViewModel.class);
    }

    private void updateHours() {
        mHoursInfo.mArrivalTime.setTime(mBtnArrivalTime.getText().toString());
        mHoursInfo.mExitTime.setTime(mBtnExitTime.getText().toString());
        mHoursInfo.mCustomBreaks.clear();
        for(int i = 0; i < mLayoutMiddayTimes.getChildCount(); i++){
            Timestamp middayExit = new Timestamp();
            Timestamp middayArrival = new Timestamp();
            Utils.GetTimestampsFromViewIndex(mLayoutMiddayTimes, i, middayExit, middayArrival);
            mHoursInfo.mCustomBreaks.add(new HoursInfo.Break(new HoursInfo.BreakTimes(middayExit, middayArrival), false));
        }
        mHoursInfo = mHoursManager.CalcDayWithExit(mHoursInfo);
        if(mHoursInfo.mTotalTime.isFullDay){
            mLblTxtFullDay.setText(Defaults.FULL_DAY.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.white));
        }
        else
        {
            mLblTxtFullDay.setText(mHoursInfo.mTotalTime.total.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.red));
        }
        mLblTxtZeroHours.setText(mHoursInfo.mTotalTime.zeroHours.toString());
        mLblTxtAdditionalHours.setText(mHoursInfo.mTotalTime.additionalHours.toString());
    }

    @Override
    public void onUpdate(OnUpdateListener listener) {
        if(listener == this)
            updateHours();
    }
}