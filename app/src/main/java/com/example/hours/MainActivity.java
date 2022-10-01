package com.example.hours;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.animation.AnticipateInterpolator;

import com.example.hours.ui.calcDayNoExit.CalcDayNoExitFragment;
import com.example.hours.ui.gallery.GalleryFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

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
                R.id.nav_calc_day_no_exit, R.id.nav_gallery, R.id.nav_settings)
                .setOpenableLayout(mDrawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().findItem(R.id.nav_calc_day_no_exit).setChecked(true);
        //toggles the navigation view to the right - incomplete code (caused crash)
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, mDrawer, binding.appBarMain.toolbar,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close
//        );
//        toggle.setDrawerIndicatorEnabled(false);
//        toggle.setHomeAsUpIndicator(R.drawable.ic_email);
//        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mDrawer.isDrawerVisible(Gravity.RIGHT)) {
//                    mDrawer.closeDrawer(Gravity.RIGHT);
//                } else {
//                    mDrawer.openDrawer(Gravity.RIGHT);
//                }
//            }
//        });
//        mDrawer.addDrawerListener(toggle);
//        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_calc_day_no_exit:
                    case R.id.nav_gallery:
                        openFragment(menuItem);
                        break;
                    case R.id.nav_settings:
                        Intent newIntent = new Intent(MainActivity.this, SettingsActivity.class);
                        startActivity(newIntent);
                        break;
                }
                return true;
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
        if(menuItem.getItemId() == R.id.nav_calc_day_no_exit){
            fragmentClass = CalcDayNoExitFragment.class;
        } else if(menuItem.getItemId() == R.id.nav_gallery){
            fragmentClass = GalleryFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set MyFragment Arguments
        if(fragment != null) {

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main, fragment).commit();

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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}