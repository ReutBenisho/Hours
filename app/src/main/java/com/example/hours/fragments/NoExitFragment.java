package com.example.hours.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hours.utils.HoursManager;
import com.example.hours.R;
import com.example.hours.models.NoExitViewModel;
import com.example.hours.utils.Utils;

public class NoExitFragment extends Fragment implements CalcDayFragment.ICalcDayFragment {

    public static final String TAG = "NO_EXIT_FRAGMENT";
    private NoExitViewModel mViewModel;
    private HoursManager mHoursManager;
    private TextView mLblTxtHalfDay;
    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxt3AndHalfHours;
    private TextView mLblTxt6Hours;
    private boolean mIsInitialized = false;
    private boolean mIsFriday;
    private View mView;

    public static NoExitFragment newInstance() {
        return new NoExitFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_no_exit, container, false);

        initialize();
        update();

        return mView;
    }

    private void initialize() {
        mIsInitialized = true;
        mLblTxtHalfDay = mView.findViewById(R.id.lbl_txt_half_day);
        mLblTxtFullDay = mView.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = mView.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxt3AndHalfHours = mView.findViewById(R.id.lbl_txt_3_and_half_hours);
        mLblTxt6Hours = mView.findViewById(R.id.lbl_txt_6_hours);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NoExitViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void update(boolean isFriday) {
        if(!mIsInitialized)
            return;
        update();
    }

    private void update() {
        updateLabels();
        updateVisibility();
    }

    private void updateLabels() {

        mHoursManager = HoursManager.getInstance();
        mHoursManager.CalcDayNoExit();

        mLblTxtHalfDay.setText(mHoursManager.info.calcInfo.halfDay.toString());
        mLblTxtFullDay.setText(mHoursManager.info.calcInfo.fullDay.toString());
        mLblTxtZeroHours.setText(mHoursManager.info.calcInfo.zeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursManager.info.calcInfo.additional3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursManager.info.calcInfo.additional6Hours.toString());

    }

    private void updateVisibility() {
        Utils.updateViewVisibility(mView.findViewById(R.id.lbl_txt_half_day));
        Utils.updateViewVisibility(mView.findViewById(R.id.lbl_txt_full_day));
        Utils.updateViewVisibility(mView.findViewById(R.id.lbl_txt_zero_hours));
        Utils.updateViewVisibility(mView.findViewById(R.id.lbl_txt_3_and_half_hours));
        Utils.updateViewVisibility(mView.findViewById(R.id.lbl_txt_6_hours));
    }
}