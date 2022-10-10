package com.example.hours.fragments;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hours.calcUtils.Break;
import com.example.hours.calcUtils.BreakTimes;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.R;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.utils.SharedPreferencesUtil;
import com.example.hours.utils.Utils;
import com.example.hours.models.CalcDayModel;
import com.google.android.material.textfield.TextInputEditText;

public class CalcDayFragment extends Fragment implements OnUpdateListener {

    private CalcDayModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_calc_day_no_exit);

    private HoursManager mHoursManager;
    private LinearLayout mLayoutMiddayTimes;
    private ImageView mBtnAddMiddayRow;
    private ICalcDayFragment mFragment;
    private AppCompatCheckBox mCkbtn_add_exit_time;
    private AppCompatCheckBox mCkbtn_friday;
    private LinearLayout mLayoutExitTime;
    private TextInputEditText mTxtArrivalTime;


    public static CalcDayFragment newInstance() {
        return new CalcDayFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListenerManager.addListener(this, ListenerManager.ListenerType.INFO_LABELS);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews(); // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_calc_day, container, false);

        mLayoutMiddayTimes = view.findViewById(R.id.layout_midday_exit_and_arrival_times);
        mLayoutExitTime = view.findViewById(R.id.layout_exit_time);
        mBtnAddMiddayRow = view.findViewById(R.id.img_add_midday_row);

        mBtnAddMiddayRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.addMiddayRowToLayout(getLayoutInflater(), mLayoutMiddayTimes, getContext());
            }
        });

        mHoursManager = HoursManager.getInstance();
        mHoursManager.info.userInfo.arrivalTime = new Timestamp(7, 30);
        mTxtArrivalTime = view.findViewById(R.id.txt_arrival_time);
        mTxtArrivalTime.setText(mHoursManager.info.userInfo.arrivalTime.toString());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.popTimePicker(view, getContext());
            }
        };
        mTxtArrivalTime.setOnClickListener(listener);

        mCkbtn_add_exit_time = view.findViewById(R.id.ckbtn_add_exit_time);
        mCkbtn_add_exit_time.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    Utils.addExitTimeLayout(getLayoutInflater(), mLayoutExitTime, getContext());
                    EditText txtExitTime = getView().findViewById(R.id.txt_exit_time);
                    mHoursManager.info.userInfo.exitTime.setTime(txtExitTime.getText().toString());
                }
                else
                    Utils.removeExitTime(mLayoutExitTime);

                openCalcDayFragment(isChecked);
            }
        });
        mCkbtn_friday = view.findViewById(R.id.ckbtn_day_friday);
        mCkbtn_friday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mHoursManager.info.userInfo.isFriday = isChecked;
                openCalcDayFragment(mCkbtn_add_exit_time.isChecked());
            }
        });
        openCalcDayFragment(false);
        updateHours();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, "");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CalcDayModel.class);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ListenerManager.removeListener(this, ListenerManager.ListenerType.INFO_LABELS);
    }

    private void updateHours() {
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
        if(mCkbtn_add_exit_time.isChecked())
        {
            EditText txtExitTime = getView().findViewById(R.id.txt_exit_time);
            mHoursManager.info.userInfo.exitTime.setTime(txtExitTime.getText().toString());
        }
        mHoursManager.info.userInfo.isFriday = mCkbtn_friday.isChecked();
        mHoursManager.info.userInfo.isStudent = SharedPreferencesUtil.getBoolean(getString(R.string.pref_student_mode));
        mFragment.update(mCkbtn_friday.isChecked());
    }

    private void openCalcDayFragment(boolean isExitTimeAdded) {
        Fragment fragment = null;
        Class fragmentClass = null;
        String tag = "";
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
                    .replace(R.id.nav_host_fragment_calc_day, fragment, tag)
                    .commit();

            mFragment = (ICalcDayFragment) fragment;
            mFragment.update(mCkbtn_friday.isChecked());
        }
    }

    @Override
    public void onUpdate(OnUpdateListener listener, Object obj) {

        updateHours();
    }

    public interface ICalcDayFragment {
        void update(boolean isFriday);
    }
}