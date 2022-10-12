package com.example.hours.calcUtils;

import com.example.hours.utils.Defaults;

public class HoursManager {
    private static HoursManager mInstance = null;
    public final HoursInfo info;

    private HoursManager()
    {
        info = new HoursInfo();
    }

    public static HoursManager getInstance(){
        if(mInstance == null)
            mInstance = new HoursManager();
        return mInstance;
    }

    public void CalcDayNoExit() {
        info.clearCalculatedInfo();
        sumAllBreaks();
        if(info.userInfo.isFriday)
        {
            if(info.userInfo.isStudent)
            {
                info.calcInfo.student.additional125Hours = info.userInfo.arrivalTime.add(Defaults.getAdditional125Hours());
                info.calcInfo.student.additional125Hours = adjustBreaks(info.calcInfo.student.additional125Hours);
            }
            info.calcInfo.additional6Hours = info.userInfo.arrivalTime.add(Defaults.getMaxAdditionalHours());
            info.calcInfo.additional6Hours = adjustBreaks(info.calcInfo.additional6Hours);
            if(info.userInfo.isStudent)
                info.calcInfo.student.additional150Hours = new Timestamp(info.calcInfo.additional6Hours);

        }
        else {
            adjustArrivalToLunchBreak();
            info.calcInfo.halfDay = info.userInfo.arrivalTime.add(Defaults.getHalfDay());
            info.calcInfo.halfDay = adjustBreaks(info.calcInfo.halfDay);
            info.calcInfo.fullDay = info.calcInfo.halfDay.add(Defaults.getHalfDay());
            info.calcInfo.fullDay = adjustBreaks(info.calcInfo.fullDay);
            if(!info.userInfo.isStudent){
                info.calcInfo.zeroHours = info.calcInfo.fullDay.add(Defaults.getZeroHours());
                info.calcInfo.zeroHours = adjustBreaks(info.calcInfo.zeroHours);
                info.calcInfo.additional3AndHalfHours = info.calcInfo.zeroHours.add(Defaults.getAdditionalHours());
            }
            else {
                info.calcInfo.additional3AndHalfHours = info.calcInfo.fullDay.add(Defaults.getZeroHours()).add(Defaults.getAdditionalHours());
                info.calcInfo.student.additional125Hours = info.calcInfo.fullDay.add(Defaults.getAdditional125Hours());
                info.calcInfo.student.additional125Hours = adjustBreaks(info.calcInfo.student.additional125Hours);


            }

            info.calcInfo.additional3AndHalfHours = adjustBreaks(info.calcInfo.additional3AndHalfHours);
            info.calcInfo.additional6Hours = info.calcInfo.additional3AndHalfHours.add(Defaults.getExtraAdditionalHours());
            info.calcInfo.additional6Hours = adjustBreaks(info.calcInfo.additional6Hours);
            if(info.userInfo.isStudent){
                info.calcInfo.student.additional150Hours = new Timestamp(info.calcInfo.additional6Hours);
            }
        }
    }

    private void sumAllBreaks() {
        info.breaks.allBreaks.clear();
        if(info.userInfo.isFriday){
            for(int i = 0; i < info.breaks.customBreaks.size(); i++){
                Break breakToAdd = info.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
        else{
            for(int i = 0; i < info.breaks.preDefinedBreaks.size(); i++){
                Break breakToAdd = info.breaks.preDefinedBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
            for(int i = 0; i < info.breaks.customBreaks.size(); i++){
                Break breakToAdd = info.breaks.customBreaks.get(i);
                addBreakIfOverlap(breakToAdd);
            }
        }
    }

    private void addBreakIfOverlap(Break breakToAdd) {
        boolean isExpanded = false;
        for(int i = 0; i < info.breaks.allBreaks.size(); i++){
            Break currBreak = info.breaks.allBreaks.get(i);
            isExpanded = isExpanded || currBreak.expandBreak(breakToAdd);
        }
        if(!isExpanded)
            info.breaks.allBreaks.add(new Break(breakToAdd));
    }

    public HoursInfo CalcDayWithExit() {
        info.clearCalculatedInfo();
        sumAllBreaks();
        if(info.userInfo.arrivalTime.isAfter(info.userInfo.exitTime))
            return info;

        //check what's the total if exiting at evening break -
        //if it's less than a full day - there's no break
        Timestamp exitTime = info.userInfo.exitTime;
        info.userInfo.exitTime = Defaults.getEveningStart();
        Timestamp totalIfExitingAtBreak = removeOverlaps();
        info.userInfo.exitTime = exitTime;
        info.clearCalculatedInfo();
        sumAllBreaks();

        Timestamp totalNoOverlaps = removeOverlaps();
        if(info.userInfo.isFriday)
        {
            if(info.userInfo.isStudent){
                if(totalNoOverlaps.lessThan(Defaults.getAdditional125Hours())){
                    info.calcInfo.totalTime.additional125Hours.setTime(totalNoOverlaps);
                }
                else {
                    info.calcInfo.totalTime.additional125Hours.setTime(Defaults.getAdditional125Hours());
                    info.calcInfo.totalTime.additional150Hours.setTime(totalNoOverlaps.sub(Defaults.getAdditional125Hours()));
                }
            }
            else{
                info.calcInfo.totalTime.additionalHours.setTime(totalNoOverlaps);
            }
        }
        else
        {
            info.calcInfo.totalTime.total = totalNoOverlaps;

            if(info.userInfo.exitTime.isAfter(Defaults.getEveningStart())
//                    && info.breaks.customBreaks.size() == 0
            //&&info.calcInfo.totalTime.total.equalsOrGreaterThan(Defaults.FULL_DAY)
                    && totalIfExitingAtBreak.equalsOrGreaterThan(Defaults.getFullDayWithLunchBreak())
            )
                info.calcInfo.totalTime.total = info.calcInfo.totalTime.total.sub(Defaults.getEveningDuration());

            if(info.calcInfo.totalTime.total.equalsOrGreaterThan(Defaults.getFullDay())){
                info.calcInfo.totalTime.isFullDay = true;
                Timestamp additional = info.calcInfo.totalTime.total.sub(Defaults.getFullDay());
                if(info.userInfo.isStudent){
                    if(additional.lessThan(Defaults.getAdditional125Hours())){
                        info.calcInfo.totalTime.additional125Hours.setTime(additional);
                    }
                    else {
                        info.calcInfo.totalTime.additional125Hours.setTime(Defaults.getAdditionalHours());
                        info.calcInfo.totalTime.additional150Hours.setTime(additional.sub(Defaults.getAdditionalHours()));
                    }
                }
                else {
                    if (additional.equalsOrGreaterThan(Defaults.getZeroHours())) {
                        info.calcInfo.totalTime.additionalHours.setTime(additional);
                    } else {
                        info.calcInfo.totalTime.zeroHours.setTime(additional);
                    }
                }
            }
            else {
                info.calcInfo.totalTime.isFullDay = false;
                if(info.calcInfo.totalTime.total.lessThan(Defaults.getHalfDay()) || info.userInfo.isStudent)
                    info.calcInfo.totalTime.unpaidAbsence.setTime(Defaults.getFullDay().sub(info.calcInfo.totalTime.total));
                else
                    info.calcInfo.totalTime.globalAbsence.setTime(Defaults.getFullDay().sub(info.calcInfo.totalTime.total));
            }
        }
        //mHourInfo = CalcDayNoExit(mHourInfo);
        return info;
    }

    private Timestamp removeOverlaps() {
        Timestamp duration = info.userInfo.exitTime.sub(info.userInfo.arrivalTime);
        for(int i = 0; i < info.breaks.allBreaks.size(); i++)
            duration = removeOverlap(info.userInfo.arrivalTime, info.userInfo.exitTime,
                    info.breaks.allBreaks.get(i).breakTimes.start, info.breaks.allBreaks.get(i).breakTimes.end,
                    duration);

        return duration;
    }

    private Timestamp removeOverlap(Timestamp arrivalTime, Timestamp exitTime, Timestamp break_start, Timestamp break_end, Timestamp duration) {
        return Timestamp.removeOverlap(arrivalTime, exitTime, break_start, break_end, duration);
    }

    private Timestamp adjustBreaks(Timestamp exitTime) {
        for(int i = 0; i < info.breaks.allBreaks.size(); i++){
            exitTime = adjustToBreak(exitTime, i);
        }
        if(!info.breaks.tookEveningBreak && exitTime.isAfter(Defaults.getEveningStart())) {
            info.breaks.tookEveningBreak = true;
            exitTime = exitTime.add(Defaults.getEveningDuration());
        }

        return exitTime;
    }

    private Timestamp adjustToBreak(Timestamp exitTime, int i) {
        boolean tookBreak = false;
        if(info.breaks.allBreaks.get(i).tookBreak)
            return exitTime;
        Timestamp startBreak = info.breaks.allBreaks.get(i).breakTimes.start;
        Timestamp endBreak = info.breaks.allBreaks.get(i).breakTimes.end;

        if(endBreak.isBefore(startBreak)) // invalid range
            return exitTime;

        if(Timestamp.isOverlap(info.userInfo.arrivalTime, exitTime, startBreak, endBreak))
        {
            exitTime = Timestamp.addOverlap(info.userInfo.arrivalTime, exitTime, startBreak, endBreak);
            tookBreak = true;
        }

        if(tookBreak){
            info.breaks.allBreaks.get(i).tookBreak = true;
        }
        return exitTime;
    }

    private void adjustArrivalToLunchBreak() {
        if(info.userInfo.arrivalTime.isBetween(Defaults.getLunchStart(), Defaults.getLunchEnd())){
            info.userInfo.arrivalTime = Defaults.getLunchEnd();
        }
    }

    public void clear() {
        info.clear();
    }
}
