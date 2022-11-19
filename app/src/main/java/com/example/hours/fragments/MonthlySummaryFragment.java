package com.example.hours.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.Utils;

import java.util.Calendar;

public class MonthlySummaryFragment extends Fragment implements IMonthlyFragment{

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_summary);
    private boolean mIsInitialized = false;
    private HoursManager mHoursManager;
    private TextView mLblTxtRegularHours;
    private TextView mLblTxtZeroHours;
    private TextView mLblTxtAdditionalHours;
    private TextView mLblTxtAdditional125Hours;
    private TextView mLblTxtAdditional150Hours;
    private TextView mLblTxtGlobalAbsenceHours;
    private TextView mLblTxtUnpaidAbsenceHours;
    private View mView;
    private int mCurrentMonth;
    private int mCurrentYear;


    public static MonthlySummaryFragment newInstance() {

        return new MonthlySummaryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void initialize() {
        mIsInitialized = true;
        mLblTxtRegularHours = mView.findViewById(R.id.lbl_txt_regular_hours);
        mLblTxtZeroHours = mView.findViewById(R.id.lbl_txt_zero_hours);
        mLblTxtZeroHours.setTextColor(getResources().getColor(R.color.yellow));
        mLblTxtAdditionalHours = mView.findViewById(R.id.lbl_txt_additional_hours);
        mLblTxtAdditionalHours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtAdditional125Hours = mView.findViewById(R.id.lbl_txt_additional_125_hours);
        mLblTxtAdditional125Hours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtAdditional150Hours = mView.findViewById(R.id.lbl_txt_additional_150_hours);
        mLblTxtAdditional150Hours.setTextColor(getResources().getColor(R.color.green));
        mLblTxtGlobalAbsenceHours = mView.findViewById(R.id.lbl_txt_global_absence_hours);
        mLblTxtGlobalAbsenceHours.setTextColor(getResources().getColor(R.color.red));
        mLblTxtUnpaidAbsenceHours = mView.findViewById(R.id.lbl_txt_unpaid_absence_hours);
        mLblTxtUnpaidAbsenceHours.setTextColor(getResources().getColor(R.color.red));
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_monthly_summary, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mCurrentMonth = bundle.getInt(App.getStr(R.string.arg_current_month), 1);
            mCurrentYear = bundle.getInt(App.getStr(R.string.arg_current_year), 2000);
        }

        initialize();
        update();

        return mView;
    }

    @Override
    public void onResume() {

        super.onResume();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MonthlyReportModel.class);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    private void update() {
        updateLabels();
        updateVisibility();
    }


    private void updateLabels() {
        mHoursManager = HoursManager.getInstance();
        //TODO: loop over all dates in current month & year
        //mHoursManager.CalcDayWithExit();

        Timestamp regularHours = new Timestamp(186, 20);
        Timestamp zeroHours = new Timestamp(2, 13);
        Timestamp additionalHours = new Timestamp(52, 14);
        Timestamp additional125Hours = new Timestamp(10, 42);
        Timestamp additional150Hours = new Timestamp(3, 17);
        Timestamp globalAbsence = new Timestamp(1, 5);
        Timestamp unpaidAbsence = new Timestamp(4, 13);
        mLblTxtRegularHours.setText(regularHours.toString());
        mLblTxtZeroHours.setText(zeroHours.toString());
        mLblTxtAdditionalHours.setText(additionalHours.toString());
        mLblTxtAdditional125Hours.setText(additional125Hours.toString());
        mLblTxtAdditional150Hours.setText(additional150Hours.toString());
        mLblTxtGlobalAbsenceHours.setText(globalAbsence.toString());
        mLblTxtUnpaidAbsenceHours.setText(unpaidAbsence.toString());
    }

    private void updateVisibility() {
        Utils.updateViewVisibility(mLblTxtRegularHours);
        Utils.updateViewVisibility(mLblTxtZeroHours);
        Utils.updateViewVisibility(mLblTxtAdditionalHours);
        Utils.updateViewVisibility(mLblTxtAdditional125Hours);
        Utils.updateViewVisibility(mLblTxtAdditional150Hours);
        Utils.updateViewVisibility(mLblTxtGlobalAbsenceHours);
        Utils.updateViewVisibility(mLblTxtUnpaidAbsenceHours);
    }

    @Override
    public void update(int month, int year) {
        //TODO: update stuff by month & year
    }
}