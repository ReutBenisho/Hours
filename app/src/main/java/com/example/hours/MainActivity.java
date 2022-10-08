package com.example.hours;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.AnticipateInterpolator;

import com.example.hours.databinding.ActivityMainBinding;
import com.example.hours.ui.calcDay.CalcDayFragment;
import com.example.hours.ui.gallery.GalleryFragment;
import com.example.hours.ui.settings.SettingsFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setting the whole application right-to-left
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setupSplashScreen();
        SharedPreferencesUtil.setDefaults("existing_user", "true", getApplicationContext());
        SharedPreferencesUtil.loadDefaults(getApplicationContext());
        Utils.setupDarkMode(getApplicationContext());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        mDrawer = binding.drawerLayout;
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
                    Snackbar.make(findViewById(R.id.nav_host_fragment_content_main), "Pressed on student mode", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmail() {
        String[] addresses = new String[1];
        addresses[0] = "xreutx197@gmail.com";
        String subject = "Issue regarding the Hours app";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        startActivity(intent);
    }

    private void setupSplashScreen() {
        // Add a callback that's called when the splash screen is animating to
        // the app content.
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

    private void openFragment(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass = null;
        String tag = "";

        FragmentManager fragmentManager = getSupportFragmentManager();
        if(menuItem.getItemId() == R.id.nav_calc_day){
            fragmentClass = CalcDayFragment.class;
            tag = CalcDayFragment.TAG;
        }else if(menuItem.getItemId() == R.id.nav_gallery){
            fragmentClass = GalleryFragment.class;
            tag = GalleryFragment.TAG;
        }else if(menuItem.getItemId() == R.id.nav_settings){
            fragmentClass = SettingsFragment.class;
            tag = SettingsFragment.TAG;
        }

        try {
//            if(fragmentManager.popBackStackImmediate(tag, 0))
//                return;
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set MyFragment Arguments
        if(fragment != null) {

            // Insert the fragment by replacing any existing fragment
            fragmentManager.beginTransaction().add(R.id.nav_host_fragment_content_main, fragment, tag).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            // Set action bar title
            setTitle(menuItem.getTitle());
        }

        // Close the navigation drawer
        mDrawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}