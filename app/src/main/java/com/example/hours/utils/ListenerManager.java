package com.example.hours.utils;

import com.example.hours.interfaces.OnUpdateListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListenerManager {

    public enum ListenerType{
        INFO_LABELS,
        ACTION_BAR_TITLE,
        TIME_PREFERENCE_CHANGE,
        UPDATE_DAILY_REPORT_IN_DB,
        CHANGED_MONTH,
        CHANGED_MONTH_VIA_MONTH_VIEW,
        UPDATED_MONTH_CURSOR
    }
    public static class Data{
        public final ListenerType type;
        public final Object obj;

        public Data(ListenerType _type, Object _obj) {
            type = _type;
            obj = _obj;
        }
    }
    private static Map<ListenerType, ArrayList<OnUpdateListener>> mListeners;

    public static void addListener(OnUpdateListener listener, ListenerType type) {
        if(mListeners == null)
            mListeners = new HashMap<>();
        if(!mListeners.containsKey(type)){
            mListeners.put(type, new ArrayList<>());
        }
        if(!mListeners.get(type).contains(listener))
            mListeners.get(type).add(listener);
    }

    public static void removeListener(OnUpdateListener listener, ListenerType type) {
        if(mListeners != null
                && mListeners.get(type) != null)
            mListeners.get(type).remove(listener);
    }

    public static void NotifyListeners(ListenerType type, Object obj) {
        if(mListeners == null || !mListeners.containsKey(type))
            return;
        for(int i = 0; i < mListeners.get(type).size(); i++){
            OnUpdateListener listener =  mListeners.get(type).get(i);
            listener.onUpdateListener(listener,new Data(type, obj));
        }
    }

    public static void NotifyListeners(ListenerType type) {
        NotifyListeners(type, null);
    }
}
