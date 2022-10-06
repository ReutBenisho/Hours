package com.example.hours.ui.noExit;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.R;
import com.example.hours.ui.calcDay.CalcDayFragment;

public class NoExitFragment extends Fragment implements CalcDayFragment.IExitFragment {

    public static final String TAG = "NO_EXIT_FRAGMENT";
    private NoExitViewModel mViewModel;
    private HoursInfo mHoursInfo;
    private HoursManager mHoursManager;
    private TextView mLblTxtHalfDay;
    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxt3AndHalfHours;
    private TextView mLblTxt6Hours;
    private boolean mIsInitialized = false;

    public static NoExitFragment newInstance() {
        return new NoExitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_exit, container, false);

        initialized(view);
        updateLabels();

        return view;
    }

    private void initialized(View view) {
        mIsInitialized = true;
        mLblTxtHalfDay = view.findViewById(R.id.lbl_txt_half_day);
        mLblTxtFullDay = view.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = view.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxt3AndHalfHours = view.findViewById(R.id.lbl_txt_3_and_half_hours);
        mLblTxt6Hours = view.findViewById(R.id.lbl_txt_6_hours);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NoExitViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void updateHours(HoursInfo hoursInfo) {
        if(mHoursInfo == null)
            mHoursInfo = new HoursInfo();
        mHoursInfo.clear();
        mHoursInfo.setUserData(hoursInfo);
        if(getView() == null)
            return;
        if(!mIsInitialized)
           initialized(getView());
        updateLabels();
    }

    private void updateLabels() {
        if(mHoursInfo == null)
            return;
        mHoursInfo = HoursManager.getInstance().CalcDayNoExit(mHoursInfo);
        mLblTxtHalfDay.setText(mHoursInfo.halfDay.toString());
        mLblTxtFullDay.setText(mHoursInfo.fullDay.toString());
        mLblTxtZeroHours.setText(mHoursInfo.zeroHours.toString());
        mLblTxt3AndHalfHours.setText(mHoursInfo.additional3AndHalfHours.toString());
        mLblTxt6Hours.setText(mHoursInfo.additional6Hours.toString());
    }
}