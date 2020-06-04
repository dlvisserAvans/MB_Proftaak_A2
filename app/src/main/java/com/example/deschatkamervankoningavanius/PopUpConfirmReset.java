package com.example.deschatkamervankoningavanius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;

public class PopUpConfirmReset extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupconfirm);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        getParent().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;


        getWindow().setLayout((int) (widthPixels * .9), (int) (heightPixels * .7));
    }

    public void onButtonConfirmClicked(View view) {
        //todo reset the chosen difficulty and word
        Intent intent = new Intent(this, ScanscreenActivity.class);
        startActivity(intent);
    }
}
