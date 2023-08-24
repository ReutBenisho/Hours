package com.example.hours.fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hours.R;
import com.example.hours.calcUtils.Timestamp;
import com.example.hours.contentProvider.HoursProviderContract;
import com.example.hours.databinding.FragmentHomeBinding;
import com.example.hours.db.DatabaseDataWorker;
import com.example.hours.db.HoursDbContract;
import com.example.hours.models.HomeViewModel;
import com.example.hours.utils.App;
import com.example.hours.utils.SharedPreferencesUtil;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class HomeFragment extends Fragment {

    public static final String TAG = App.getStr(R.string.tag_home);
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        TextView lblHello = view.findViewById(R.id.lbl_hello);
        String hello = App.getStr(R.string.hello);
        String name = "";
        lblHello.setText(hello + name + "!");
        TextView lblNumOfRows = view.findViewById(R.id.lbl_nomber_of_rows);
        lblNumOfRows.setText("HHHHIIII");
        final String[] noteColumns = {
                HoursDbContract.DailyReportEntry._ID,
                HoursDbContract.DailyReportEntry.COLUMN_DATE,
                HoursDbContract.DailyReportEntry.COLUMN_ARRIVAL,
                HoursDbContract.DailyReportEntry.COLUMN_EXIT};
        int count = GetNumOfRows(HoursProviderContract.DailyReports.CONTENT_URI, null, null);
        lblNumOfRows.setText(String.valueOf(count));
        Button addRowsButton = view.findViewById(R.id.btn_add_rows);
        addRowsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDailyReports();
                int count = GetNumOfRows(HoursProviderContract.DailyReports.CONTENT_URI, null, null);
                lblNumOfRows.setText(String.valueOf(count));
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public int GetNumOfRows(Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = App.getContext().getContentResolver().query(uri,new String[] {"count(*)"},
                selection, selectionArgs, null);
        if (cursor.getCount() == 0) {
            cursor.close();
            return 0;
        } else {
            cursor.moveToFirst();
            int result = cursor.getInt(0);
            cursor.close();
            return result;
        }
    }

    public void insertDailyReports() {
        insertDailyReport(new Date(2022 - 1900, 10, 30), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 24, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 10, 31), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 25, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 1), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(17 * 60 + 30, ChronoUnit.MINUTES));
        insertDailyReport(new Date(2022 - 1900, 11, 2), Duration.of(7 * 60 + 30, ChronoUnit.MINUTES), Duration.of(16 * 60 + 0, ChronoUnit.MINUTES));
    }

    private void insertDailyReport(Date date, Duration arrival, Duration exit) {
        ContentValues values = new ContentValues();
        values.put(HoursProviderContract.DailyReports.COLUMN_DATE, new SimpleDateFormat("yyyyMMdd").format(date));
        values.put(HoursProviderContract.DailyReports.COLUMN_ARRIVAL, (new Timestamp(arrival)).toString());
        values.put(HoursProviderContract.DailyReports.COLUMN_EXIT, (new Timestamp(exit)).toString());
        App.getContext().getContentResolver().insert(HoursProviderContract.DailyReports.CONTENT_URI, values);
    }
}