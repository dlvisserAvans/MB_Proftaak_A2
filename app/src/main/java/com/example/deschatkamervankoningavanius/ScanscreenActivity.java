package com.example.deschatkamervankoningavanius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ScanscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanscreen);

    }

    public void onIconQuestionClicked(View v) {
        Intent intent = new Intent(ScanscreenActivity.this, Pop.class);
        ScanscreenActivity.this.startActivity(intent);
    }

    public void onButtonNFCPressed(View view){
        Intent intent = new Intent(ScanscreenActivity.this, NavFragmentBaseActivity.class);
        startActivity(intent);
    }


    public void onButtonQRPressed(View v){
        Intent intent = new Intent(ScanscreenActivity.this, QR_code_scanner.class);
        startActivity(intent);
    }
}
