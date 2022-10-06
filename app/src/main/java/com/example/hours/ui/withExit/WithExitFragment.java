package com.example.hours.ui.withExit;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hours.Defaults;
import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.R;
import com.example.hours.ui.calcDay.CalcDayFragment;

public class WithExitFragment extends Fragment implements CalcDayFragment.IExitFragment {

    public static final String TAG = "WITH_EXIT_FRAGMENT";
    private WithExitViewModel mViewModel;

    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private HoursInfo mHoursInfo;
    private boolean mIsInitialized = false;

    public static WithExitFragment newInstance() {
        return new WithExitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_with_exit, container, false);

        initialize(view);
        updateLabels();

        return view;
    }

    private void initialize(View view) {
        mIsInitialized = true;
        mLblTxtFullDay = view.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = view.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtAdditionalHours = view.findViewById(R.id.lbl_txt_additional_hours);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WithExitViewModel.class);
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
            initialize(getView());
        updateLabels();
    }

    private void updateLabels() {
        mHoursInfo = HoursManager.getInstance().CalcDayWithExit(mHoursInfo);
        if(mHoursInfo.totalTime.isFullDay){
            mLblTxtFullDay.setText(Defaults.FULL_DAY.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.white));
        }
        else
        {
            mLblTxtFullDay.setText(mHoursInfo.totalTime.total.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.red));
        }
        mLblTxtZeroHours.setText(mHoursInfo.totalTime.zeroHours.toString());
        mLblTxtAdditionalHours.setText(mHoursInfo.totalTime.additionalHours.toString());
    }
}