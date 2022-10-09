package com.example.hours.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.AnticipateInterpolator;

import com.example.hours.calcUtils.HoursManager;
import com.example.hours.utils.ListenerManager;
import com.example.hours.models.MainActivityViewModel;
import com.example.hours.interfaces.OnUpdateListener;
import com.example.hours.R;
import com.example.hours.utils.SharedPreferencesUtil;
import com.example.hours.utils.Utils;
import com.example.hours.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity implements OnUpdateListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout mDrawer;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private Toolbar mToolbar;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the whole application right-to-left
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        ViewModelProvider provider = new ViewModelProvider(
                getViewModelStore(),
                (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        mViewModel = provider.get(MainActivityViewModel.class);

        setupSplashScreen();
        SharedPreferencesUtil.setDefaults("existing_user", "true");
        SharedPreferencesUtil.loadDefaults();
        Utils.setupDarkMode(getApplicationContext());
        HoursManager.getInstance().info.userInfo.isStudent = SharedPreferencesUtil.getBoolean(getString(R.string.pref_student_mode));
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        mActionBar = getSupportActionBar();
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        // Initialize ActionBarDrawerToggle, which will control toggle of hamburger.
        // You set the values of R.string.open and R.string.close accordingly.
        // Also, you can implement drawer toggle listener if you want.

        mDrawer = binding.drawerLayout;
        mToolbar = findViewById(R.id.toolbar);
        mDrawerToggle = new ActionBarDrawerToggle (
                this,
                mDrawer,
                mToolbar,
                R.string.open,
                R.string.close);
        // Setting the actionbarToggle to drawer layout
        mDrawer.addDrawerListener(mDrawerToggle);
        // Calling sync state is necessary to show your hamburger icon...
        // or so I hear. Doesn't hurt including it even if you find it works
        // without it on your test device(s)
        mDrawerToggle.syncState();

        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_calc_day,R.id.nav_gallery, R.id.nav_settings)
                .setOpenableLayout(mDrawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //navigationView.setNavigationItemSelectedListener(this);
        //navigationView.getMenu().findItem(R.id.nav_calc_day_no_exit).setChecked(true);

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                switch(menuItem.getItemId()){
//                    case R.id.nav_calc_day:
//                    case R.id.nav_gallery:
//                    case R.id.nav_settings:
//                        openFragment(menuItem);
//                        break;
//                    //case R.id.nav_settings:
////                        Intent newIntent = new Intent(MainActivity.this, SettingsActivity.class);
////                        startActivity(newIntent);
//                        //break;
//                }
//                return true;
//            }
//        });

        //PreferenceManager.setDefaultValues(this, R.xml.header_preferences, false);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
                if(s == getString(R.string.pref_system_mode) ||
                        s == getString(R.string.pref_dark_mode)){
                    Utils.setupDarkMode(getApplicationContext());
                }
                else if(s == getString(R.string.pref_student_mode)){
                    HoursManager.getInstance().info.userInfo.isStudent = sharedPreferences.getBoolean(s, false);
                }
            }
        });
        ListenerManager.addListener(this, ListenerManager.ListenerType.ACTION_BAR_TITLE);
        ListenerManager.NotifyListeners(ListenerManager.ListenerType.ACTION_BAR_TITLE, mViewModel.ActionBarTitle);
    }

    private void sendEmail() {
        String[] addresses = new String[1];
        addresses[0] = "xreutx197@gmail.com";
        String subject = "Issue regarding the App app";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.S)
    private void setupSplashScreen() {
        // Add a callback that's called when the splash screen is animating to
        // the app content.
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.S){
            return;
        }
        getSplashScreen().setOnExitAnimationListener(splashScreenView -> {
            final ObjectAnimator slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.getHeight()
            );
            slideUp.setInterpolator(new AnticipateInterpolator());
            slideUp.setDuration(1000L);

            // Call SplashScreenView.remove at the end of your custom animation.
            slideUp.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    splashScreenView.remove();
                }
            });

            // Run your animation.
            slideUp.start();
        });
    }

//    private void openFragment(MenuItem menuItem) {
//        Fragment fragment = null;
//        Class fragmentClass = null;
//        String tag = "";
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        if(menuItem.getItemId() == R.id.nav_calc_day){
//            fragmentClass = CalcDayFragment.class;
//            tag = CalcDayFragment.TAG;
//        }else if(menuItem.getItemId() == R.id.nav_gallery){
//            fragmentClass = GalleryFragment.class;
//            tag = GalleryFragment.TAG;
//        }else if(menuItem.getItemId() == R.id.nav_settings){
//            fragmentClass = SettingsFragment.class;
//            tag = SettingsFragment.TAG;
//        }
//
//        try {
////            if(fragmentManager.popBackStackImmediate(tag, 0))
////                return;
//            fragment = (Fragment) fragmentClass.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // set MyFragment Arguments
//        if(fragment != null) {
//
//            // Insert the fragment by replacing any existing fragment
//            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_content_main, fragment, tag).commit();
//
//            // Highlight the selected item has been done by NavigationView
//            menuItem.setChecked(true);
//            // Set action bar title
//            setTitle(menuItem.getTitle());
//        }
//
//        // Close the navigation drawer
//        mDrawer.closeDrawers();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        ListenerManager.removeListener(this, ListenerManager.ListenerType.ACTION_BAR_TITLE);
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUpdate(OnUpdateListener listener, Object obj) {
        if(listener == this){
            ListenerManager.Data data = (ListenerManager.Data)obj;
            switch (data.type){
                case ACTION_BAR_TITLE:{

                    mViewModel.ActionBarTitle = data.obj.toString();
                    if(mViewModel.ActionBarTitle.toString().equals("")){
                        setActionBarIconToBackArrow(false);
                    }
                    else {
                        mActionBar.setTitle(mViewModel.ActionBarTitle.toString());
                        setActionBarIconToBackArrow(true);
        //            mDrawer.setDrawerIndicatorEnabled(false);
        //            mActionBar.setDisplayHomeAsUpEnabled(true);
        //            mActionBar.setDisplayShowHomeEnabled(true);
                    }
                    break;
                }
            }
        }
    }

    private void setActionBarIconToBackArrow(Boolean backArrow) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if(backArrow) {
            //You may not want to open the drawer on swipe from the left in this case
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            // Remove hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            // Show back button
            mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if(!mToolBarNavigationListenerIsRegistered) {
                mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            //You must regain the power of swipe for the drawer.
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

            // Remove back button
            //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

        // So, one may think "Hmm why not simplify to:
        // .....
        // getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        // mDrawer.setDrawerIndicatorEnabled(!enable);
        // ......
        // To re-iterate, the order in which you enable and disable views IS important #dontSimplify.
    }
}