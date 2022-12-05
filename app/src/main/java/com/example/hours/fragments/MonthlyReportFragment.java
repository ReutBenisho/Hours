package com.example.hours.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.utils.App;
import com.example.hours.utils.ListenerManager;
import com.example.hours.utils.OnSnapPositionChangeListener;
import com.example.hours.utils.SnapOnScrollListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MonthlyReportFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnUpdateListener {

    private static final int LOADER_MONTHLY_DAILY_REPORTS = 0;
    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_report);
    private int mCurrentMonth;
    private int mCurrentYear;
    private TextView mLblCurrentMonth;
    private ImageView mImagePreviousMonth;
    private ImageView mImageNextMonth;
    private IMonthlyFragment mFragment;
    private Cursor mCursor;
    private boolean mCreatedLoader;
    private HoursOpenHelper mDbOpenHelper;


    public static MonthlyReportFragment newInstance() {

        return new MonthlyReportFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbOpenHelper = new HoursOpenHelper(getContext());
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

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)
                    openCalcDayFragment(compoundButton.getId());
            }
        };
        AppCompatRadioButton rdBtnSummary = view.findViewById(R.id.rdBtn_monthly_summary);
        rdBtnSummary.setOnCheckedChangeListener(listener);
        AppCompatRadioButton rdBtnView = view.findViewById(R.id.rdBtn_monthly_view);
        rdBtnView.setOnCheckedChangeListener(listener);
        AppCompatRadioButton rdBtnList = view.findViewById(R.id.rdBtn_monthly_list);
        rdBtnList.setOnCheckedChangeListener(listener);

        mLblCurrentMonth = view.findViewById(R.id.lbl_current_month);
        mImagePreviousMonth = view.findViewById(R.id.img_month_previous);
        mImagePreviousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentMonth = (mCurrentMonth  + 12 - 1) % 12;
                mCurrentMonth = (mCurrentMonth == 0) ? 12 : mCurrentMonth;
                mCurrentYear = (mCurrentMonth == 12) ? mCurrentYear - 1 : mCurrentYear;
                mLblCurrentMonth.setText(getCurrentMonthText());
                getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, MonthlyReportFragment.this);
            }
        });
        mImageNextMonth = view.findViewById(R.id.img_month_next);
        mImageNextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentMonth = (mCurrentMonth + 1) % 13;
                mCurrentMonth = (mCurrentMonth == 0) ? 1 : mCurrentMonth;
                mCurrentYear = (mCurrentMonth == 1) ? mCurrentYear + 1 : mCurrentYear;
                mLblCurrentMonth.setText(getCurrentMonthText());
                ListenerManager.NotifyListeners(ListenerManager.ListenerType.CHANGED_MONTH, mCurrentMonth + "," + mCurrentYear);
                getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, MonthlyReportFragment.this);
                //TODO: send "changed month message:
            }
        });
        rdBtnSummary.setChecked(true);
        mLblCurrentMonth.setText(getCurrentMonthText());
        ListenerManager.addListener(this, ListenerManager.ListenerType.CHANGED_MONTH_VIA_MONTH_VIEW);
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        mCurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        mCurrentYear = Calendar.getInstance().get(Calendar.YEAR);

        getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, this);

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


    private void openCalcDayFragment(int id) {
        Fragment fragment = null;
        Class fragmentClass;
        String tag;
        //TODO: macth each frag to correct radio button
        if(id == R.id.rdBtn_monthly_summary){
            fragmentClass = MonthlySummaryFragment.class;
            tag = MonthlySummaryFragment.TAG;
        } else if(id == R.id.rdBtn_monthly_view){
            fragmentClass = MonthlyViewFragment.class;
            tag = MonthlyViewFragment.TAG;
        } else {
            fragmentClass = MonthlyListFragment.class;
            tag = MonthlyListFragment.TAG;
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
                    .replace(R.id.nav_host_fragment_monthly_report, fragment, tag)
                    .commit();

            mFragment = (IMonthlyFragment) fragment;
            mFragment.update(mCurrentMonth, mCurrentYear, mCursor);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader loader = null;
        if(id == LOADER_MONTHLY_DAILY_REPORTS){
            loader = new CursorLoader(getContext()){
                @Override
                public Cursor loadInBackground() {
                    SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
                    String selection = "substr(" + DailyReportEntry.COLUMN_DATE + ", 1, 4) == '" + mCurrentYear + "'";
                    selection += " AND substr(" + DailyReportEntry.COLUMN_DATE + ", 5, 2) == '" + String.format("%02d", mCurrentMonth) + "'";
                    final String[] noteColumns = {
                            DailyReportEntry._ID,
                            DailyReportEntry.COLUMN_DATE,
                            DailyReportEntry.COLUMN_ARRIVAL,
                            DailyReportEntry.COLUMN_EXIT};
                    String noteOrderBy = DailyReportEntry.COLUMN_DATE + " ASC";

                    return db.query(DailyReportEntry.TABLE_NAME, noteColumns,
                            selection, null, null, null, noteOrderBy);

                }
            };
        }
        mCreatedLoader = true;
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (!mCreatedLoader)
            return;
        mCreatedLoader = false;
        if (loader.getId() == LOADER_MONTHLY_DAILY_REPORTS) {
            mCursor = data;
            ListenerManager.NotifyListeners(ListenerManager.ListenerType.UPDATED_MONTH_CURSOR, mCursor);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if(loader.getId() == LOADER_MONTHLY_DAILY_REPORTS){
            mCursor = null;
            ListenerManager.NotifyListeners(ListenerManager.ListenerType.UPDATED_MONTH_CURSOR, null);
        }
    }

    @Override
    public void onUpdateListener(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case CHANGED_MONTH_VIA_MONTH_VIEW:{
                    String strMonthAndYear = (String) data.obj;
                    mCurrentMonth = Integer.parseInt(strMonthAndYear.substring(0, strMonthAndYear.indexOf(",")));
                    mCurrentYear = Integer.parseInt(strMonthAndYear.substring(strMonthAndYear.indexOf(",") + 1));
                    mLblCurrentMonth.setText(getCurrentMonthText());
                    getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, this);

                    break;
                }
            }
        }
    }
}