package com.example.deschatkamervankoningavanius;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingsscreen);

        SharedPreferences appSettingPrefs = this.getSharedPreferences("AppSettingPrefs", 0);
        final SharedPreferences.Editor sharedPrefsEdit = appSettingPrefs.edit();
        Boolean isNightModeOn = appSettingPrefs.getBoolean("NightMode", false);

        Switch switchButton = findViewById(R.id.switch1);

        if(isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switchButton.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            switchButton.setChecked(false);
        }

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    sharedPrefsEdit.putBoolean("NightMode", true);
                    sharedPrefsEdit.apply();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    sharedPrefsEdit.putBoolean("NightMode", false);
                    sharedPrefsEdit.apply();
                }
            }
        });
    }

    public void onButtonBackClicked(View view){
        finish();
    }
}
