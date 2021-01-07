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

import com.example.nestledemo.ui.components.profile.ProfileActivity;
import com.example.nestledemo.R;
import com.example.nestledemo.ui.components.barcode.SimpleScannerActivity;
import com.example.nestledemo.ui.components.login.LoginActivity;
import com.example.nestledemo.utils.PrefUtils;
import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nestledemo.utils.PrefUtils.IS_LOGIN;
import static com.example.nestledemo.utils.PrefUtils.MOBILE_NUM;

public class HomeActivity extends AppCompatActivity {


    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;

    private final int requestCode = 1;
    private Toolbar toolbar;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    private static final String TAG_HOME = "home";
    private static final String TAG_PROFILE = "profile";
    public static String CURRENT_TAG = TAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Save user login value in shared pref
        PrefUtils.write(IS_LOGIN, true);
        View headerView = navigationView.getHeaderView(0);
        TextView profileNameTV = headerView.findViewById(R.id.profileName);
        String name = "Welcome " + PrefUtils.read(MOBILE_NUM, "Nestle");
        profileNameTV.setText(name);

        setUpNavigationView();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
        }
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
}