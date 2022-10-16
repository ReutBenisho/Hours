package com.example.hours.fragments;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.utils.App;
//import com.example.hours.utils.EditTimeDialog;
//import com.example.hours.utils.EditTimePreference;
import com.example.hours.utils.ListenerManager;
import com.example.hours.R;
import com.example.hours.models.SettingsViewModel;
//import com.example.hours.utils.TimeBindEditTextListener;
import com.example.hours.utils.SharedPreferencesUtil;
import com.example.hours.utils.TimestampTextWatcher;

import java.util.HashMap;
import java.util.Map;

public class SettingsFragment extends Fragment implements
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{
    public static Map<Integer, String> keyTypes = initMap();

    private static Map<Integer, String> initMap() {
        HashMap<Integer, String> map = new HashMap<>();
        //general
        map.put(R.string.pref_system_dark_mode, "boolean");
        map.put(R.string.pref_dark_mode, "boolean");
        map.put(R.string.pref_system_language, "boolean");
        map.put(R.string.pref_language, "String");
        map.put(R.string.pref_student_mode, "boolean");
        //notifications
        map.put(R.string.pref_all_notifications, "boolean");
        map.put(R.string.pref_notify_lunch_break, "boolean");
        map.put(R.string.pref_notify_evening_break, "boolean");
        map.put(R.string.pref_notify_night_break, "boolean");
        map.put(R.string.pref_notify_mothers_transportation, "boolean");
        map.put(R.string.pref_notify_afternoon_transportation, "boolean");
        map.put(R.string.pref_notify_evening_transportation, "boolean");
        map.put(R.string.pref_notify_night_transportation, "boolean");
        map.put(R.string.pref_reminder_time, "String");
        //times
        map.put(R.string.pref_default_system_time, "boolean");
        map.put(R.string.pref_default_arrival_time, "String");
        map.put(R.string.pref_default_exit_time, "String");
        map.put(R.string.pref_default_custom_breaks, "String");
        map.put(R.string.use_default_system_times, "boolean");
        map.put(R.string.pref_default_lunch_break_time, "String");
        map.put(R.string.pref_default_lunch_break_duration, "String");
        map.put(R.string.pref_default_evening_break_time, "String");
        map.put(R.string.pref_default_evening_break_duration, "String");
        map.put(R.string.pref_default_night_break_time, "String");
        map.put(R.string.pref_default_night_break_duration, "String");


        return map;
    }

    public static final String TAG = App.getStr(R.string.tag_settings_fragment);
    private SettingsViewModel mViewModel;
    private static final String TITLE_TAG = "settingsActivityTitle";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(container != null)
            container.removeAllViews(); // Inflate the layout for this fragment
// create ContextThemeWrapper from the original Activity Context with the custom theme
//        Context context = new ContextThemeWrapper(getActivity(), R.style.Theme_Hours_Settings);
//        // clone the inflater using the ContextThemeWrapper
//        LayoutInflater localInflater = inflater.cloneInContext(context);
//        // inflate using the cloned inflater, not the passed in default

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        if (savedInstanceState == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings_fragment_container, new SettingsFragment.HeaderFragment())
                    .commit();
        } else {
            getActivity().setTitle(savedInstanceState.getCharSequence(TITLE_TAG));
        }
//        getChildFragmentManager().addOnBackStackChangedListener(
//                new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        ParentSettingsFragment topFrag = (ParentSettingsFragment)getChildFragmentManager().findFragmentById(R.id.settings_fragment_container);
//                        if (topFrag != null) {
//                            Utils.NotifyListeners(Utils.ListenerType.ACTION_BAR_TITLE, topFrag.TAG);
//                        }
//                    }
//                });

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//
//        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_settings_fragment);
//        toolbar.setTitle("My Title");
//        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, R.string.menu_settings);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public boolean onPreferenceStartFragment(@NonNull PreferenceFragmentCompat caller, @NonNull Preference pref) {
        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final ParentSettingsFragment fragment = (ParentSettingsFragment)getChildFragmentManager().getFragmentFactory().instantiate(
                getActivity().getClassLoader(),
                pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getChildFragmentManager().beginTransaction()
                .replace(R.id.settings_fragment_container, fragment)
                .addToBackStack(getString(fragment.TAG))
                .commit();
        getActivity().setTitle(pref.getTitle());
        return true;
    }

    public static class ParentSettingsFragment extends PreferenceFragmentCompat {
        public int TAG = R.string.empty;
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey);
        }

        @NonNull
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, TAG);
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    public static class HeaderFragment extends ParentSettingsFragment {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            TAG = R.string.menu_settings;
            setPreferencesFromResource(R.xml.header_preferences, rootKey);
        }
    }

    public static class MessagesFragment extends ParentSettingsFragment {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            TAG = R.string.tag_messages_fragment;
            setPreferencesFromResource(R.xml.messages_preferences, rootKey);
        }
    }

    public static class SyncFragment extends ParentSettingsFragment {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            TAG = R.string.tag_sync_fragment;
            setPreferencesFromResource(R.xml.sync_preferences, rootKey);
        }
    }

    public static class GeneralFragment extends ParentSettingsFragment {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            TAG = R.string.general_header;
            super.onCreate(savedInstanceState);
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.general_preferences, rootKey);
        }

        @NonNull
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    public static class NotificationsFragment extends ParentSettingsFragment {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            TAG = R.string.notifications_header;
            setPreferencesFromResource(R.xml.notifiations_preferences, rootKey);
        }
    }

    public static class TimesFragment extends ParentSettingsFragment implements OnUpdateListener {
        View mView;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            TAG = R.string.times_header;
            setPreferencesFromResource(R.xml.times_preferences, rootKey);
        }

        @NonNull
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            mView =  super.onCreateView(inflater, container, savedInstanceState);

            setDefaultTime(R.string.pref_default_arrival_time);
            setDefaultTime(R.string.pref_default_exit_time);
            setDefaultTime(R.string.pref_default_lunch_break_time);
            setDefaultTime(R.string.pref_default_lunch_break_duration);
            setDefaultTime(R.string.pref_default_evening_break_time);
            setDefaultTime(R.string.pref_default_evening_break_duration);
            setDefaultTime(R.string.pref_default_night_break_time);
            setDefaultTime(R.string.pref_default_night_break_duration);
            ListenerManager.addListener(this, ListenerManager.ListenerType.PREFERENCE_CHANGE);
            return mView;
        }

        private void setDefaultTime(int prefId) {
            EditTextPreference pref = findPreference(getString(prefId));
            pref.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                    editText.addTextChangedListener(new TimestampTextWatcher(editText));
                }
            });
            String val = SharedPreferencesUtil.getString(getString(prefId));
            findPreference(getString(prefId)).setSummary(val);


        }

        private void ResetGeneralSettings() {
            //TODO: ADD LOGIC TO RESET ALL GENERAL SETTINGS

        }

        @Override
        public void onUpdateListener(OnUpdateListener listener, Object obj) {
            if(listener == this) {
                ListenerManager.Data data = (ListenerManager.Data) obj;
                switch (data.type) {
                    case PREFERENCE_CHANGE: {
                        String key = ((String)data.obj);
                        String val = SharedPreferencesUtil.getString(key);
                        findPreference(key).setSummary(val);
                    }
                }
            }
        }
    }
}