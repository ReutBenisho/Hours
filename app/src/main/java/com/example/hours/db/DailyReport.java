package com.example.hours.db;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.hours.calcUtils.Timestamp;

import java.sql.Time;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class DailyReport implements Parcelable{
    private int mId;
    private Date mDate;
    private Timestamp mArrival;
    private Timestamp mExit;

    public DailyReport(int id, Date date, Timestamp arrival, Timestamp exit) {
        mId = id;
        mDate = new Date(String.valueOf(date));
        mArrival = new Timestamp(arrival);
        mExit = new Timestamp(exit);
    }

    private DailyReport(Parcel parcel) {
        mId = 0;
        mDate = new Date(parcel.readString());
        mArrival = new Timestamp();
        mExit = new Timestamp();
    }

    public DailyReport() {
        mId = 0;
        mDate = new Date();
        mArrival = new Timestamp();
        mExit = new Timestamp();

    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {

        mDate = new Date(String.valueOf(date));
    }

    public Timestamp getArrival() {
        return mArrival;
    }

    public void setArrival(Timestamp arrival) {

        mArrival = new Timestamp(arrival);
    }

    public Timestamp getExit() {

        return mExit;
    }

    public void setExit(Timestamp exit) {

        mExit = new Timestamp(exit);
    }


    private String getCompareKey() {
        return mDate.toString() + "|" + mArrival.toString() + "|" + mExit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyReport that = (DailyReport) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mDate.toString());
        parcel.writeString(mArrival.toString() + "");
        parcel.writeString(mExit.toString() + "");
    }

    public static final Creator<DailyReport> CREATOR =
            new Creator<DailyReport>() {
                @Override
                public DailyReport createFromParcel(Parcel parcel) {
                    return new DailyReport(parcel);
                }

                @Override
                public DailyReport[] newArray(int size) {
                    return new DailyReport[size];
                }
            };

    public int getId() {
        return mId;
    }
}
