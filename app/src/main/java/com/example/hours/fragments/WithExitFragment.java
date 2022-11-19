package com.example.hours.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hours.utils.App;
import com.example.hours.utils.Defaults;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.R;
import com.example.hours.models.WithExitViewModel;
import com.example.hours.utils.Utils;

public class WithExitFragment extends Fragment implements ICalcDayFragment {

    public static final String TAG = App.getStr(R.string.tag_calc_day_with_exit);
    private WithExitViewModel mViewModel;

    private TextView mLblTxtFullDay;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private TextView mLblTxtAdditional125Hours;
    private TextView mLblTxtAdditional150Hours;
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
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_with_exit, container, false);

        initialize();
        update();

        return mView;
    }

    private void initialize() {
        mIsInitialized = true;
        mLblTxtFullDay = mView.findViewById(R.id.lbl_txt_full_day);
        mLblTxtZeroHours = mView.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtAdditionalHours = mView.findViewById(R.id.lbl_txt_additional_hours);
        mLblTxtAdditional125Hours = mView.findViewById(R.id.lbl_txt_additional_125_hours);
        mLblTxtAdditional150Hours = mView.findViewById(R.id.lbl_txt_additional_150_hours);
        mLblTxtGlobalAbsenceHours = mView.findViewById(R.id.lbl_txt_global_absence_hours);
        mLblTxtUnpaidAbsenceHours = mView.findViewById(R.id.lbl_txt_unpaid_absence_hours);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WithExitViewModel.class);
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
        mHoursManager.CalcDayWithExit();

        if(mHoursManager.mInfo.calcInfo.totalTime.isFullDay){
            mLblTxtFullDay.setText(Defaults.getFullDay().toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.white));
            mLblTxtFullDay.setTypeface(Typeface.DEFAULT);

        }
        else
        {
            mLblTxtFullDay.setText(mHoursManager.mInfo.calcInfo.totalTime.total.toString());
            mLblTxtFullDay.setTextColor(getResources().getColor(R.color.red));
            mLblTxtFullDay.setTypeface(null, Typeface.BOLD);
        }
        mLblTxtZeroHours.setText(mHoursManager.mInfo.calcInfo.totalTime.zeroHours.toString());
        mLblTxtAdditionalHours.setText(mHoursManager.mInfo.calcInfo.totalTime.additionalHours.toString());
        mLblTxtAdditional125Hours.setText(mHoursManager.mInfo.calcInfo.totalTime.additional125Hours.toString());
        mLblTxtAdditional150Hours.setText(mHoursManager.mInfo.calcInfo.totalTime.additional150Hours.toString());
        mLblTxtGlobalAbsenceHours.setText(mHoursManager.mInfo.calcInfo.totalTime.globalAbsence.toString());
        mLblTxtUnpaidAbsenceHours.setText(mHoursManager.mInfo.calcInfo.totalTime.unpaidAbsence.toString());
    }

    private void updateVisibility() {
        Utils.updateViewVisibility(mLblTxtFullDay);
        Utils.updateViewVisibility(mLblTxtZeroHours);
        Utils.updateViewVisibility(mLblTxtAdditionalHours);
        Utils.updateViewVisibility(mLblTxtAdditional125Hours);
        Utils.updateViewVisibility(mLblTxtAdditional150Hours);
        Utils.updateViewVisibility(mLblTxtGlobalAbsenceHours);
        Utils.updateViewVisibility(mLblTxtUnpaidAbsenceHours);
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