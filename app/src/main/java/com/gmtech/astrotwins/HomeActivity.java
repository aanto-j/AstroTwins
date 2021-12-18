package com.gmtech.astrotwins;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    ImageButton notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeAsUpIndicator(R.drawable.sidebar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        notifications = findViewById(R.id.notificationsImage);
        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,notif).commit();
            }
        });

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.astrotwinsmenu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            switch(item.getItemId()){
                case R.id.notifications:
                    getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment,notif).commit();
                    return true;
                case R.id.privacy:
                    return true;
                case R.id.terms:
                    return true;
                case R.id.faq:
                    return true;
                case R.id.sign_out:
                    return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }

    AstroTwinsFragment firstFragment = new AstroTwinsFragment();
    MatchesFragment secondFragment = new MatchesFragment();
    AstrologersFragment thirdFragment = new AstrologersFragment();
    UserProfileFragment fourthFragment = new UserProfileFragment();
    NotificationsFragment notif = new NotificationsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.astrotwinsmenu:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment, firstFragment).commit();
                return true;

            case R.id.match:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment, secondFragment).commit();
                return true;

            case R.id.astrologers:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment, thirdFragment).commit();
                return true;

            case R.id.userprof:
                getSupportFragmentManager().beginTransaction().replace(R.id.f1fragment, fourthFragment).commit();
                return true;
        }
        return false;
    }
}