package com.example.hours.ui.settings;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.example.hours.Defaults;
import com.example.hours.R;
import com.example.hours.SharedPreferencesUtil;
import com.example.hours.Timestamp;
import com.example.hours.Utils;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends Fragment implements
        PreferenceFragmentCompat.OnPreferenceStartFragmentCallback{

    public static final String TAG = "SETTINGS_FRAGMENT";
    private SettingsViewModel mViewModel;
    private static final String TITLE_TAG = "settingsActivityTitle";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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
        getChildFragmentManager().addOnBackStackChangedListener(
                new FragmentManager.OnBackStackChangedListener() {
                    @Override
                    public void onBackStackChanged() {
                        if (getChildFragmentManager().getBackStackEntryCount() == 0) {
                            getActivity().setTitle(R.string.title_activity_settings);
                        }
                    }
                });

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
                .addToBackStack(fragment.TAG)
                .commit();
        getActivity().setTitle(pref.getTitle());
        return true;
    }
    public static class ParentSettingsFragment extends PreferenceFragmentCompat {
        public static final String TAG = "Parent Frag";
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey);
        }
    }

    public static class HeaderFragment extends ParentSettingsFragment {
        public static final String TAG = "Header Frag";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.header_preferences, rootKey);
        }
    }

    public static class MessagesFragment extends ParentSettingsFragment {
        public static final String TAG = "Message Frag";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.messages_preferences, rootKey);
        }
    }

    public static class SyncFragment extends ParentSettingsFragment {
        public static final String TAG = "Sync Frag";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.sync_preferences, rootKey);
        }
    }

    public static class GeneralFragment extends ParentSettingsFragment {
        public static final String TAG = "General Frag";

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
        }

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.general_preferences, rootKey);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return super.onCreateView(inflater, container, savedInstanceState);
        }

    }

    public static class NotificationsFragment extends ParentSettingsFragment {
        public static final String TAG = "Notifications Frag";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.notifiations_preferences, rootKey);
        }
    }

    public static class TimesFragment extends ParentSettingsFragment {
        public static final String TAG = "Times Frag";

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.times_preferences, rootKey);
        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            setDefaultTime(R.string.pref_default_arrival_time, Defaults.ARRIVAL_TIME.toString());
            setDefaultTime(R.string.pref_default_launch_break_time, Defaults.LAUNCH_BREAK_START.toString());
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        private void setDefaultTime(int idDefaultTime, String defaultValue) {
            String strDefaultTime = getString(idDefaultTime);
            findPreference(strDefaultTime)
                    .setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                        @Override
                        public boolean onPreferenceClick(Preference preference) {
                            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                    Timestamp viewTimestamp = new Timestamp(selectedHour, selectedMinute);
                                    preference.setDefaultValue(viewTimestamp.toString());
                                    preference.setSummary(viewTimestamp.toString());
                                }
                            };
                            Timestamp timestamp = new Timestamp();
                            String value = preference.getSummary().toString();
                            timestamp.setTime(value);
                            TimePickerDialog timePickerDialog =
                                    new TimePickerDialog(getContext(), AlertDialog.THEME_HOLO_DARK, onTimeSetListener,
                                            timestamp.getHour(), timestamp.getMinute(),
                                            true);
                            timePickerDialog.setTitle(R.string.enter_time);
                            timePickerDialog.show();
                            return true;
                        }
                    });
            Preference myPreference = findPreference(strDefaultTime);
            SharedPreferencesUtil.setDefaults(strDefaultTime, defaultValue, getActivity());
            //PreferenceManager.getDefaultSharedPreferences(getActivity()).getString(strDefaultTime, defaultValue);
            myPreference.setSummary(defaultValue);
            myPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    preference.setSummary(newValue.toString());
                    return false;
                }
            });
        }

        private void ResetGeneralSettings() {
            //TODO: ADD LOGIC TO RESET ALL GENERAL SETTINGS

        }
    }
}