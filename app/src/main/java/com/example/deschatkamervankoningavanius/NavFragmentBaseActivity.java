package com.example.deschatkamervankoningavanius;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.deschatkamervankoningavanius.Data.JSONParser;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Fragments.HomeFragment;
import com.example.deschatkamervankoningavanius.Fragments.MenuFragment;
import com.example.deschatkamervankoningavanius.Fragments.TreasuryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class NavFragmentBaseActivity extends AppCompatActivity {

    private List<Fragment> fragmentList = new ArrayList<>();
    private Fragment currentFragment = null;
    private User user = null;
    private Difficulty difficulty = null;
    //TODO: Research to make sure the new home fragment changes when the orientation changes.
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.navigation_home:
                            currentFragment = fragmentList.get(0);
                            Log.d("FRAGMENT ID", fragmentList.get(0).toString());
                            break;
                        case R.id.navigation_treasury:
                            currentFragment = fragmentList.get(1);
                            break;

                        case R.id.navigation_menu:
                            currentFragment = fragmentList.get(2);
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, currentFragment).commit();
                    return true;
                }
            };

    //TODO: Research to make sure the new home fragment changes when the orientation changes.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.difficulty = Difficulty.valueOf(getIntent().getExtras().get("Difficulty").toString());
        createUser();
        fragmentList.add(new HomeFragment(this.user));
        Log.d("FRAGMENT ID", fragmentList.get(0).toString());
        fragmentList.add(new TreasuryFragment(this.user));
        fragmentList.add(new MenuFragment());
        currentFragment = fragmentList.get(0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navactivityfragmentbase);


//        this.currentFragment = new HomeFragment(this.user);

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
