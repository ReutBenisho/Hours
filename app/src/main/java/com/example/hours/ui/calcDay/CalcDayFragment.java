package com.example.hours.ui.calcDay;

import androidx.fragment.app.FragmentManager;
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
import android.widget.RadioGroup;

import com.example.hours.Break;
import com.example.hours.BreakTimes;
import com.example.hours.HoursInfo;
import com.example.hours.HoursManager;
import com.example.hours.OnUpdateListener;
import com.example.hours.R;
import com.example.hours.Timestamp;
import com.example.hours.Utils;
import com.example.hours.ui.noExit.NoExitFragment;
import com.example.hours.ui.withExit.WithExitFragment;

public class CalcDayFragment extends Fragment implements OnUpdateListener {

    private CalcDayModel mViewModel;
    public static final String TAG = "CALC_DAY_NO_EXIT_TAG";

    private Button mBtnArrivalTime;
    private HoursInfo mHoursInfo;
    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private Button mBtnAddMiddayRow;
    private RadioGroup mRadioGroupDay;
    private IExitFragment mFragment;


    public static CalcDayFragment newInstance() {
        return new CalcDayFragment();
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

        View view = inflater.inflate(R.layout.fragment_calc_day, container, false);

        mBtnArrivalTime = view.findViewById(R.id.btn_arrival_time);
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

        mRadioGroupDay = view.findViewById(R.id.rbg_day_of_choice);
        mRadioGroupDay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                openCalcDayFragment(checkedId);
            }
        });

        mRadioGroupDay.check(R.id.rd_btn_day_weekday);
        updateHours();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcDayModel.class);
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
        mFragment.updateHours(mHoursInfo);
    }

    @Override
    public void onUpdate(OnUpdateListener listener) {
        if(listener == this)
            updateHours();
    }

    private void openCalcDayFragment(int checkedRadioId) {
        Fragment fragment = null;
        Class fragmentClass = null;
        String tag = "";
        if(checkedRadioId == R.id.rd_btn_day_weekday){
            fragmentClass = NoExitFragment.class;
            tag = NoExitFragment.TAG;
        } else if(checkedRadioId == R.id.rd_btn_day_friday){
            fragmentClass = WithExitFragment.class;
            tag = WithExitFragment.TAG;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set MyFragment Arguments
        if(fragment != null) {

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment_calc_day, fragment, tag)
                    .commit();

            mFragment = (IExitFragment) fragment;
            mFragment.updateHours(mHoursInfo);
        }
    }

    public interface IExitFragment{
        void updateHours(HoursInfo hoursInfo);
    }
}