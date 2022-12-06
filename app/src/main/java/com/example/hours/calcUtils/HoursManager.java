package com.example.hours.calcUtils;

import com.example.hours.utils.Defaults;

public class HoursManager {
    private static HoursManager mInstance = null;
    public final HoursInfo mInfo;

    private HoursManager()
    {
        mInfo = new HoursInfo();
    }

    public static HoursManager getInstance(){
        if(mInstance == null)
            mInstance = new HoursManager();
        return mInstance;
    }

    public void CalcDayNoExit() {
        mInfo.clearCalculatedInfo();
        sumAllBreaks();
        if(mInfo.userInfo.isFriday)
        {
            if(mInfo.userInfo.isStudent)
            {
                mInfo.calcInfo.student.additional125Hours = mInfo.userInfo.arrivalTime.add(Defaults.getAdditional125Hours());
                mInfo.calcInfo.student.additional125Hours = adjustBreaks(mInfo.calcInfo.student.additional125Hours);
            }
            mInfo.calcInfo.additional6Hours = mInfo.userInfo.arrivalTime.add(Defaults.getMaxAdditionalHours());
            mInfo.calcInfo.additional6Hours = adjustBreaks(mInfo.calcInfo.additional6Hours);
            if(mInfo.userInfo.isStudent)
                mInfo.calcInfo.student.additional150Hours = new Timestamp(mInfo.calcInfo.additional6Hours);

        }
        else {
            adjustArrivalToLunchBreak();
            mInfo.calcInfo.halfDay = mInfo.userInfo.arrivalTime.add(Defaults.getHalfDay());
            mInfo.calcInfo.halfDay = adjustBreaks(mInfo.calcInfo.halfDay);
            mInfo.calcInfo.fullDay = mInfo.calcInfo.halfDay.add(Defaults.getHalfDay());
            mInfo.calcInfo.fullDay = adjustBreaks(mInfo.calcInfo.fullDay);
            if(!mInfo.userInfo.isStudent){
                mInfo.calcInfo.zeroHours = mInfo.calcInfo.fullDay.add(Defaults.getZeroHours());
                mInfo.calcInfo.zeroHours = adjustBreaks(mInfo.calcInfo.zeroHours);
                mInfo.calcInfo.additional3AndHalfHours = mInfo.calcInfo.zeroHours.add(Defaults.getAdditionalHours());
            }
            else {
                mInfo.calcInfo.additional3AndHalfHours = mInfo.calcInfo.fullDay.add(Defaults.getZeroHours()).add(Defaults.getAdditionalHours());
                mInfo.calcInfo.student.additional125Hours = mInfo.calcInfo.fullDay.add(Defaults.getAdditional125Hours());
                mInfo.calcInfo.student.additional125Hours = adjustBreaks(mInfo.calcInfo.student.additional125Hours);


            }

            mInfo.calcInfo.additional3AndHalfHours = adjustBreaks(mInfo.calcInfo.additional3AndHalfHours);
            mInfo.calcInfo.additional6Hours = mInfo.calcInfo.additional3AndHalfHours.add(Defaults.getExtraAdditionalHours());
            mInfo.calcInfo.additional6Hours = adjustBreaks(mInfo.calcInfo.additional6Hours);
            if(mInfo.userInfo.isStudent){
                mInfo.calcInfo.student.additional150Hours = new Timestamp(mInfo.calcInfo.additional6Hours);
            }
        }
    }

    private void sumAllBreaks() {
        mInfo.breaks.allBreaks.clear();
        if(mInfo.userInfo.isFriday){
            for(int i = 0; i < mInfo.breaks.customBreaks.size(); i++){
                Break breakToAdd = mInfo.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
        else{
            for(int i = 0; i < mInfo.breaks.preDefinedBreaks.size(); i++){
                Break breakToAdd = mInfo.breaks.preDefinedBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
            for(int i = 0; i < mInfo.breaks.customBreaks.size(); i++){
                Break breakToAdd = mInfo.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
    }

    private void addBreakIfOverlap(Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < mInfo.breaks.allBreaks.size(); i++){
            Break currBreak = mInfo.breaks.allBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            mInfo.breaks.allBreaks.add(new Break(breakToAdd));
    }

    public HoursInfo CalcDayWithExit() {
        mInfo.clearCalculatedInfo();
        sumAllBreaks();
        if(mInfo.userInfo.arrivalTime.isAfter(mInfo.userInfo.exitTime))
            return mInfo;

        //check what's the total if exiting at evening break -
        //if it's less than a full day - there's no break
        Timestamp exitTime = mInfo.userInfo.exitTime;
        mInfo.userInfo.exitTime = Defaults.getEveningStart();
        Timestamp totalIfExitingAtBreak = removeOverlaps();
        mInfo.userInfo.exitTime = exitTime;
        mInfo.clearCalculatedInfo();
        sumAllBreaks();

        Timestamp totalNoOverlaps = removeOverlaps();
        if(mInfo.userInfo.isFriday)
        {
            if(mInfo.userInfo.isStudent){
                if(totalNoOverlaps.lessThan(Defaults.getAdditional125Hours())){
                    mInfo.calcInfo.totalTime.additional125Hours.setTime(totalNoOverlaps);
                }
                else {
                    mInfo.calcInfo.totalTime.additional125Hours.setTime(Defaults.getAdditional125Hours());
                    mInfo.calcInfo.totalTime.additional150Hours.setTime(totalNoOverlaps.sub(Defaults.getAdditional125Hours()));
                }
            }
            else{
                mInfo.calcInfo.totalTime.additionalHours.setTime(totalNoOverlaps);
            }
        }
        else
        {
            mInfo.calcInfo.totalTime.total = totalNoOverlaps;

            if(mInfo.userInfo.exitTime.isAfter(Defaults.getEveningStart())
//                    && info.breaks.customBreaks.size() == 0
            //&&info.calcInfo.totalTime.total.equalsOrGreaterThan(Defaults.FULL_DAY)
                    && totalIfExitingAtBreak.equalsOrGreaterThan(Defaults.getFullDayWithLunchBreak())
            )
                mInfo.calcInfo.totalTime.total = mInfo.calcInfo.totalTime.total.sub(Defaults.getEveningDuration());

            if(mInfo.calcInfo.totalTime.total.equalsOrGreaterThan(Defaults.getFullDay())){
                mInfo.calcInfo.totalTime.isFullDay = true;
                Timestamp additional = mInfo.calcInfo.totalTime.total.sub(Defaults.getFullDay());
                if(mInfo.userInfo.isStudent){
                    if(additional.lessThan(Defaults.getAdditional125Hours())){
                        mInfo.calcInfo.totalTime.additional125Hours.setTime(additional);
                    }
                    else {
                        mInfo.calcInfo.totalTime.additional125Hours.setTime(Defaults.getAdditional125Hours());
                        mInfo.calcInfo.totalTime.additional150Hours.setTime(additional.sub(Defaults.getAdditional125Hours()));
                    }
                }
                else {
                    if (additional.equalsOrGreaterThan(Defaults.getZeroHours())) {
                        mInfo.calcInfo.totalTime.additionalHours.setTime(additional);
                    } else {
                        mInfo.calcInfo.totalTime.zeroHours.setTime(additional);
                    }
                }
            }
            else {
                mInfo.calcInfo.totalTime.isFullDay = false;
                if(mInfo.calcInfo.totalTime.total.lessThan(Defaults.getHalfDay()) || mInfo.userInfo.isStudent)
                    mInfo.calcInfo.totalTime.unpaidAbsence.setTime(Defaults.getFullDay().sub(mInfo.calcInfo.totalTime.total));
                else
                    mInfo.calcInfo.totalTime.globalAbsence.setTime(Defaults.getFullDay().sub(mInfo.calcInfo.totalTime.total));
            }
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return mInfo;
    }

    private Timestamp removeOverlaps() {
        Timestamp duration = mInfo.userInfo.exitTime.sub(mInfo.userInfo.arrivalTime);
        for(int i = 0; i < mInfo.breaks.allBreaks.size(); i++)
            duration = removeOverlap(mInfo.userInfo.arrivalTime, mInfo.userInfo.exitTime,
                    mInfo.breaks.allBreaks.get(i).breakTimes.start, mInfo.breaks.allBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < mInfo.breaks.allBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!mInfo.breaks.tookEveningBreak && exitTime.isAfter(Defaults.getEveningStart())) {
            mInfo.breaks.tookEveningBreak = true;
            exitTime = exitTime.add(Defaults.getEveningDuration());
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(mInfo.breaks.allBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = mInfo.breaks.allBreaks.get(i).breakTimes.start;
        Timestamp endBreak = mInfo.breaks.allBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(mInfo.userInfo.arrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(mInfo.userInfo.arrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            mInfo.breaks.allBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLunchBreak() {
        if(mInfo.userInfo.arrivalTime.isBetween(Defaults.getLunchStart(), Defaults.getLunchEnd())){
            mInfo.userInfo.arrivalTime = Defaults.getLunchEnd();
        }
    }

    public void clear() {
        mInfo.clear();
    }

    public HoursInfo CalcDayWithExit(UserInfo userInfo) {
        mInfo.userInfo = userInfo;
        return CalcDayWithExit();
    }

    public String getTotalAdditionalOrAbsenceHours(HoursInfo info){
        Timestamp totalHours = new Timestamp();
        if(mInfo.userInfo.isFriday)
        {
            totalHours = mInfo.calcInfo.totalTime.total;
        }
        else
        {
            totalHours = mInfo.calcInfo.totalTime.total.sub(Defaults.getFullDay());
        }
        String str = totalHours.extractSign();
        return str;
    }

}
