package com.example.hours.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hours.R;
import com.example.hours.adapters.MonthlyDailyReportRecyclerAdapter;
import com.example.hours.db.HoursDbContract.DailyReportEntry;
import com.example.hours.db.HoursOpenHelper;
import com.example.hours.decorators.WeekendDecorator;
import com.example.hours.models.MonthlyReportModel;
import com.example.hours.utils.App;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.util.Calendar;

public class MonthlyViewFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_MONTHLY_DAILY_REPORTS = 0;
    private MonthlyReportModel mViewModel;
    public static final String TAG = App.getStr(R.string.tag_monthly_report);
    private HoursOpenHelper mDbOpenHelper;
    private boolean mCreatedLoader;
    private int mCurrentMonth;
    private MaterialCalendarView mCalendarView;


    public static MonthlyViewFragment newInstance() {

        return new MonthlyViewFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbOpenHelper = new HoursOpenHelper(getContext());
        mCurrentMonth = 0;
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

        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarView.addDecorator(new WeekendDecorator());
        mCalendarView.setHeaderTextAppearance(R.style.CustomTextAppearance);
        mCalendarView.setWeekDayTextAppearance(R.style.CustomTextAppearance);
        mCalendarView.setDateTextAppearance(R.style.CustomTextAppearance);
        mCalendarView.setLeftArrow(R.drawable.ic_left_arrow);
        mCalendarView.setRightArrow(R.drawable.ic_right_arrow);
        mCalendarView.setSelectionColor(App.getRes().getColor(R.color.calendar_cell_selected));
        mCalendarView.setTopbarVisible(false);
        mCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                mCurrentMonth = date.getMonth();
                getActivity().getSupportLoaderManager().restartLoader(LOADER_MONTHLY_DAILY_REPORTS, null, MonthlyViewFragment.this);
            }
        });
        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        mCurrentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
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

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader loader = null;
        if(id == LOADER_MONTHLY_DAILY_REPORTS){
            loader = new CursorLoader(getContext()){
                @Override
                public Cursor loadInBackground() {
                    SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
                    String selection = "substr(" + DailyReportEntry.COLUMN_DATE + ", 5, 2) == '" + String.format("%02d", mCurrentMonth) + "'";
                    String[] selectionArgs = {String.format("%02d", mCurrentMonth)};
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
            // TODO: update calender view spans
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        if(loader.getId() == LOADER_MONTHLY_DAILY_REPORTS){
            // TODO: reset calender view spans
        }
    }
}