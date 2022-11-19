package com.example.hours.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.hours.R;
import com.example.hours.adapters.DailyReportRecyclerAdapter;
import com.example.hours.adapters.MonthlyDailyReportRecyclerAdapter;
import com.example.hours.calcUtils.HoursManager;
import com.example.hours.db.HoursDbContract;
import com.example.hours.db.HoursDbContract.DailyReportEntry;
import com.example.hours.db.HoursOpenHelper;
import com.example.hours.decorators.WeekendDecorator;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.OnSnapPositionChangeListener;
import com.example.hours.utils.SnapOnScrollListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MonthlyReportFragment extends Fragment {

    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_report);
    private int mCurrentMonth;
    private TextView mLblCurrentMonth;
    private ImageView mImagePreviousMonth;
    private ImageView mImageNextMonth;
    private int mCurrentYear;


    public static MonthlyReportFragment newInstance() {

        return new MonthlyReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;;
        mCurrentYear = Calendar.getInstance().get(Calendar.YEAR);;
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

        View view = inflater.inflate(R.layout.fragment_monthly_report, container, false);

        RadioGroup rdBtnGroup = view.findViewById(R.id.radioGroup);
        rdBtnGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int btnId) {

                // TODO: open correct fragment
            }
        });

        mLblCurrentMonth = view.findViewById(R.id.lbl_current_month);
        mImagePreviousMonth = view.findViewById(R.id.img_month_previous);
        mImagePreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentMonth = (mCurrentMonth  + 12 - 1) % 12;
                mCurrentMonth = (mCurrentMonth == 0) ? 12 : mCurrentMonth;
                mCurrentYear = (mCurrentMonth == 12) ? mCurrentYear - 1 : mCurrentYear;
                mLblCurrentMonth.setText(getCurrentMonthText());
                //TODO: send "changed month message:
            }
        });
        mImageNextMonth = view.findViewById(R.id.img_month_next);
        mImageNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentMonth = (mCurrentMonth + 12 + 1) % 12;
                mCurrentMonth = (mCurrentMonth == 0) ? 1 : mCurrentMonth;
                mCurrentYear = (mCurrentMonth == 1) ? mCurrentYear + 1 : mCurrentYear;
                mLblCurrentMonth.setText(getCurrentMonthText());
                //TODO: send "changed month message:
            }
        });
        return view;
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

    private String getCurrentMonthText(){
        String month;
        // TODO: fix function to return string in the correct language
        switch (mCurrentMonth)
        {
            case 1:
                month = App.getStr(R.string.january);
                break;
            case 2:
                month = App.getStr(R.string.february);
                break;
            case 3:
                month = App.getStr(R.string.march);
                break;
            case 4:
                month = App.getStr(R.string.april);
                break;
            case 5:
                month = App.getStr(R.string.may);
                break;
            case 6:
                month = App.getStr(R.string.june);
                break;
            case 7:
                month = App.getStr(R.string.july);
                break;
            case 8:
                month = App.getStr(R.string.august);
                break;
            case 9:
                month = App.getStr(R.string.september);
                break;
            case 10:
                month = App.getStr(R.string.october);
                break;
            case 11:
                month = App.getStr(R.string.november);
                break;
            case 12:
                month = App.getStr(R.string.december);
                break;
            default:
                month = "";
                break;

        }
        return month + " " + mCurrentYear;
    }
}