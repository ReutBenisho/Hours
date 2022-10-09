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

import com.example.hours.utils.Defaults;
import com.example.hours.utils.HoursManager;
import com.example.hours.R;
import com.example.hours.models.WithExitViewModel;

public class WithExitFragment extends Fragment implements CalcDayFragment.IExitFragment {

    public static final String TAG = "WITH_EXIT_FRAGMENT";
    private WithExitViewModel mViewModel;

    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private boolean mIsInitialized = false;
    private HoursManager mHoursManager;
    private TextView mLblTxtGlobalAbsenceHours;
    private TextView mLblTxtUnpaidAbsenceHours;
    private View mView;

    public static WithExitFragment newInstance() {
        return new WithExitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_with_exit, container, false);

        initialize();
        updateLabels();

        return mView;
    }

    private void initialize() {
        mIsInitialized = true;
        mLblTxtFullDay = mView.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = mView.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtAdditionalHours = mView.findViewById(R.id.lbl_txt_additional_hours);
        mLblTxtGlobalAbsenceHours = mView.findViewById(R.id.lbl_txt_global_absence_hours);
        mLblTxtUnpaidAbsenceHours = mView.findViewById(R.id.lbl_txt_unpaid_absence_hours);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WithExitViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void update(boolean isFriday) {
        if(!mIsInitialized)
            return;
        updateLabels();
    }

    private void updateLabels() {
        mHoursManager = HoursManager.getInstance();
        mHoursManager.CalcDayWithExit();

        if(mHoursManager.info.userInfo.isFriday)
        {
            adjustFriday(View.GONE);
        }

        if(mHoursManager.info.calcInfo.totalTime.isFullDay){
            mLblTxtFullDay.setText(Defaults.FULL_DAY.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.white));
        }
        else
        {
            mLblTxtFullDay.setText(mHoursManager.info.calcInfo.totalTime.total.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.red));
        }
        mLblTxtZeroHours.setText(mHoursManager.info.calcInfo.totalTime.zeroHours.toString());
        mLblTxtAdditionalHours.setText(mHoursManager.info.calcInfo.totalTime.additionalHours.toString());
        mLblTxtGlobalAbsenceHours.setText(mHoursManager.info.calcInfo.totalTime.globalAbsence.toString());
        mLblTxtUnpaidAbsenceHours.setText(mHoursManager.info.calcInfo.totalTime.unpaidAbsence.toString());
    }

    private void adjustFriday(int visibility) {
        mView.findViewById(R.id.lbl_full_day).setVisibility(visibility);
        mView.findViewById(R.id.lbl_zero_hours).setVisibility(visibility);
        mView.findViewById(R.id.lbl_global_absence_hours).setVisibility(visibility);
        mView.findViewById(R.id.lbl_unpaid_absence_hours).setVisibility(visibility);
        mLblTxtFullDay.setVisibility(visibility);
        mLblTxtZeroHours.setVisibility(visibility);
        mLblTxtGlobalAbsenceHours.setVisibility(visibility);
        mLblTxtUnpaidAbsenceHours.setVisibility(visibility);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}