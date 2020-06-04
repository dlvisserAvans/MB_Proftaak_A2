package com.example.deschatkamervankoningavanius;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.deschatkamervankoningavanius.Data.JSONParser;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Fragments.HomeFragment;
import com.example.deschatkamervankoningavanius.Fragments.MenuFragment;
import com.example.deschatkamervankoningavanius.Fragments.TreasuryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NavFragmentBaseActivity extends AppCompatActivity {

    Difficulty difficulty;
    User user;

    //TODO: Research to make sure the new home fragment changes when the orientation changes.
    private Fragment currentFragment = new HomeFragment(user);
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            currentFragment = new HomeFragment(user);
                            break;
                        case R.id.navigation_treasury:
                            currentFragment = new TreasuryFragment();
                            break;

                        case R.id.navigation_menu:
                            currentFragment = new MenuFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, currentFragment).commit();
                    return true;
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navactivityfragmentbase);

        this.difficulty = Difficulty.valueOf(getIntent().getExtras().get("Difficulty").toString());
        createUser();
        this.currentFragment = new HomeFragment(this.user);

        //Make the navbar menu clickable and handle the links to different fragments. Set navigationItemListener.
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();
        BottomNavigationView navigationView = findViewById(R.id.navbar);
        navigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    /**
     * with this override we disable the user to go back to the choose difficulty menu by accident
     */
    @Override
    public void onBackPressed() {

    }

    public void createUser(){
        JSONParser jsonParser = new JSONParser(getApplicationContext());

//        String password = "password";   //TODO implement actual passwords
//        for (int i = 0; i < password.length(); i++){
//            this.password.add(password.charAt(i));
//        }
//
//        Collections.shuffle(this.password);
//        List<String> videoList = new ArrayList<>();

        //Make a new user
        this.user = new User();
        this.user.getQuests(difficulty, jsonParser); //Tell user to get quests
    }
}
