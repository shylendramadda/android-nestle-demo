package com.example.nestledemo.ui.components.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nestledemo.R;
import com.example.nestledemo.model.DashboardItem;
import com.example.nestledemo.ui.components.barcode.SimpleScannerActivity;
import com.example.nestledemo.ui.components.login.LoginActivity;
import com.example.nestledemo.ui.components.profile.ProfileActivity;
import com.example.nestledemo.ui.components.rewards.RewardsActivity;
import com.example.nestledemo.ui.components.social.SocialActivity;
import com.example.nestledemo.ui.components.stores.StoresActivity;
import com.example.nestledemo.utils.PrefUtils;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nestledemo.utils.PrefUtils.IS_LOGIN;
import static com.example.nestledemo.utils.PrefUtils.MOBILE_NUM;

public class HomeActivity extends AppCompatActivity implements HomeAdapter.HomeListener {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;
    @BindView(R.id.rvDashboard)
    RecyclerView rvDashboard;

    private final int requestCode = 1;
    private Toolbar toolbar;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_REWARDS = "rewards";
    private static final String TAG_STORES = "stores";
    private static final String TAG_SOCIAL = "social";
    public static String CURRENT_TAG = TAG_HOME;

    private HomeAdapter homeAdapter;
    private ArrayList<DashboardItem> dashboardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        PrefUtils.init(this);

        // Save user login value in shared pref
        PrefUtils.write(IS_LOGIN, true);
        View headerView = navigationView.getHeaderView(0);
        TextView profileNameTV = headerView.findViewById(R.id.profileName);
        String name = "Welcome " + PrefUtils.read(MOBILE_NUM, "Nestle");
        profileNameTV.setText(name);

        setUpNavigationView();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)  {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.ACCESS_COARSE_LOCATION}, requestCode);
        }

        initAdapter();
    }

    private void initAdapter() {
        dashboardList = new ArrayList<>();
        homeAdapter = new HomeAdapter(dashboardList, this);
        rvDashboard.setAdapter(homeAdapter);
        loadDashboardItems();
    }

    private void loadDashboardItems() {
        DashboardItem item1 = new DashboardItem("New look but same taste!", R.drawable.nescafe);
        DashboardItem item2 = new DashboardItem("Improve your health and increase Fitness.", R.drawable.fitnesscereal);
        DashboardItem item3 = new DashboardItem("Perfect start to Power up your day!", R.drawable.milo);
        DashboardItem item4 = new DashboardItem("Grab a snack during a break!", R.drawable.neschoc);
        DashboardItem item5 = new DashboardItem("Wanna get Ice with some great flavours? Grab one now!", R.drawable.nesicreams);
        dashboardList.add(item1);
        dashboardList.add(item2);
        dashboardList.add(item3);
        dashboardList.add(item4);
        dashboardList.add(item5);
        //to refresh adapter
        homeAdapter.notifyDataSetChanged();
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PROFILE;
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;
                    // TODO
                    case R.id.nav_rewards:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_REWARDS;
                        startActivity(new Intent(HomeActivity.this, RewardsActivity.class));
                        break;
                    case R.id.nav_stores:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_STORES;
                        startActivity(new Intent(HomeActivity.this, StoresActivity.class));
                        break;
                    case R.id.nav_social:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SOCIAL;
                        startActivity(new Intent(HomeActivity.this, SocialActivity.class));
                        break;
                    case R.id.nav_about_us:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.nestle.com/")));
                        return true;
                    case R.id.nav_privacy_policy:
                        drawerLayout.closeDrawers();
                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.nestle.com/aboutus/businessprinciples/privacy")));
                        return true;
                    case R.id.nav_logout:
                        drawerLayout.closeDrawers();
                        PrefUtils.write(IS_LOGIN, false);
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        finish();
                        break;
                    default:
                        navItemIndex = 0;
                }
                drawerLayout.closeDrawers();
                //Checking if the item is in checked state or not, if not make it in checked state
                menuItem.setChecked(!menuItem.isChecked());
                menuItem.setChecked(true);
//                loadHomeFragment();
                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_app_bar_open_drawer_description, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };
        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @OnClick(R.id.cameraIcon)
    public void onCameraClick() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
        } else {
            startActivity(new Intent(this, SimpleScannerActivity.class));
        }
    }

    @Override
    public void onItemSelected(int position) {
        // Handle clicks here TODO
    }
}