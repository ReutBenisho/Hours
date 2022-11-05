package com.example.hours.db;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Dictionary;

public final class DailyReport implements Parcelable{
    private Date mDate;
    private Duration mArrival;
    private Duration mExit;

    public DailyReport(Date date, Duration arrival, Duration exit) {
        mDate = new Date(String.valueOf(date));
        mArrival = Duration.of(arrival.toMinutes(), ChronoUnit.MINUTES);
        mExit = Duration.of(exit.toMinutes(), ChronoUnit.MINUTES);
    }

    private DailyReport(Parcel parcel) {
        mDate = new Date(parcel.readString());
        mArrival = Duration.of(Integer.parseInt(parcel.readString()), ChronoUnit.MINUTES);
        mExit = Duration.of(Integer.parseInt(parcel.readString()), ChronoUnit.MINUTES);
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {

        mDate = new Date(String.valueOf(date));
    }

    public Duration getArrival() {
        return mArrival;
    }

    public void setArrival(Duration arrival) {

        mArrival = Duration.of(arrival.toMinutes(), ChronoUnit.MINUTES);
    }

    public Duration getExit() {
        return mExit;
    }

    public void setExit(Duration exit) {

        mExit = Duration.of(exit.toMinutes(), ChronoUnit.MINUTES);
    }


    private String getCompareKey() {
        return mDate.toString() + "|" + mArrival.toMinutes() + "|" + mExit.toMinutes();
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
        parcel.writeString(mArrival.toMinutes() + "");
        parcel.writeString(mExit.toMinutes() + "");
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
}
