package com.example.hours.fragments;

import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.calcUtils.Break;
import com.example.hours.calcUtils.BreakTimes;
import com.example.hours.calcUtils.CustomBreak;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.DailyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.Defaults;
import com.example.hours.utils.ListenerManager;
import com.example.hours.utils.SharedPreferencesUtil;
import com.example.hours.utils.TimestampTextWatcher;
import com.example.hours.utils.Utils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DailyReportFragment extends Fragment implements OnUpdateListener {

    private DailyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_daily_report);

    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private ImageView mBtnAddMiddayRow;
    private ICalcDayFragment mFragment;
    private LinearLayout mLayoutExitTime;
    private TextInputEditText mTxtArrivalTime;
    private TextWatcher mTimestampTextWatcher;
    private EditText mTxtExitTime;


    public static DailyReportFragment newInstance() {
        return new DailyReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListenerManager.addListener(this, ListenerManager.ListenerType.INFO_LABELS);
    }

    @Override
    public void onDestroyView() {
        mTxtArrivalTime.removeTextChangedListener(mTimestampTextWatcher);
        super.onDestroyView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_daily_report, container, false);

        mHoursManager = HoursManager.getInstance();

        mLayoutMiddayTimes = view.findViewById(R.id.layout_midday_exit_and_arrival_times);
        mLayoutExitTime = view.findViewById(R.id.layout_exit_time);
        mBtnAddMiddayRow = view.findViewById(R.id.img_add_midday_row);

        mBtnAddMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.addMiddayRowToLayout(getLayoutInflater(), mLayoutMiddayTimes, getContext());
            }
        });


        mHoursManager.info.userInfo.arrivalTime = Defaults.getArrival();
        mTxtArrivalTime = view.findViewById(R.id.txt_arrival_time);
        String from = mTxtArrivalTime.getText().toString();
        String str = Defaults.getArrival().toString();
        Log.d("onCreateView", "chaning txtView from " + from + " to " + str);
        mTxtArrivalTime.setText(str);
        mTimestampTextWatcher = new TimestampTextWatcher(mTxtArrivalTime);
        if(mTxtArrivalTime.getTag() == null)
        {
            mTxtArrivalTime.addTextChangedListener(mTimestampTextWatcher);
            mTxtArrivalTime.setTag(mTimestampTextWatcher);
        }

        openDailyReportFragment(false);

        showEnabledBreaks();

        updateHours();

        return view;
    }

    private void showEnabledBreaks() {
        List<CustomBreak> breaksList = Defaults.getCustomBreaksList();
        Utils.removeAllMiddayRowFromLayout(mLayoutMiddayTimes);
        for(int i = 0; i < breaksList.size(); i++) {
            if(breaksList.get(i).isEnabled) {
                Utils.addMiddayRowToLayout(getLayoutInflater(), mLayoutMiddayTimes, getContext(), breaksList.get(i).toString());
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mHoursManager == null)
            mHoursManager = HoursManager.getInstance();
        String str = Defaults.getArrival().toString();
        String from = mTxtArrivalTime.getText().toString();
        Log.d("onResume", "chaning arrival txtView from " + from + " to " + str);
        mTxtArrivalTime.setText(str);
        if(mTxtExitTime != null) {
            str = Defaults.getExit().toString();
            from = mTxtExitTime.getText().toString();
            Log.d("onResume", "chaning exit txtView from " + from + " to " + str);
            mTxtExitTime.setText(str);
        }
        showEnabledBreaks();
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, R.string.empty);

        updateHours();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DailyReportModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ListenerManager.removeListener(this, ListenerManager.ListenerType.INFO_LABELS);
    }

    private void updateHours() {
        if(mHoursManager == null)
            return;
        mHoursManager.info.clear();
        mHoursManager.info.userInfo.arrivalTime.setTime(mTxtArrivalTime.getText().toString());
        mHoursManager.info.breaks.customBreaks.clear();
        for(int i = 0; i < mLayoutMiddayTimes.getChildCount(); i++){
            Timestamp middayExit = new Timestamp();
            Timestamp middayArrival = new Timestamp();
            Utils.GetTimestampsFromViewIndex(mLayoutMiddayTimes, i, middayExit, middayArrival);
            BreakTimes customBreak = new BreakTimes(middayExit, middayArrival);
            mHoursManager.info.breaks.customBreaks.add(new Break(customBreak, false));
        }
        mHoursManager.info.userInfo.isStudent = SharedPreferencesUtil.getBoolean(getString(R.string.pref_student_mode));
        mFragment.update(false);
    }

    private void openDailyReportFragment(boolean isExitTimeAdded) {
        Fragment fragment = null;
        Class fragmentClass;
        String tag;
        if(isExitTimeAdded){
            fragmentClass = WithExitFragment.class;
            tag = WithExitFragment.TAG;
        } else{
            fragmentClass = NoExitFragment.class;
            tag = NoExitFragment.TAG;
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
                    .replace(R.id.nav_host_fragment_daily_report, fragment, tag)
                    .commit();

            mFragment = (ICalcDayFragment) fragment;
            mFragment.update(false);
        }
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(obj == null || !(obj instanceof ListenerManager.Data))
            return;
        ListenerManager.Data data = (ListenerManager.Data) obj;
        switch (data.type)
        {
            case INFO_LABELS:
                updateHours();
                break;
        }
    }

}