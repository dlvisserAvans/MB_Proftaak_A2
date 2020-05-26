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


    //TODO: REMOVE after QR and NFC works.
    public void onButtonQRPressed(View v){
        Intent intent = new Intent(ScanscreenActivity.this,NavActivityFragmentBase.class);
        startActivity(intent);
    }
}
