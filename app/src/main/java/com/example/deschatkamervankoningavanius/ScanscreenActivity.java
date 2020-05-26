package com.example.deschatkamervankoningavanius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ScanscreenActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanscreen);

    }

    public void onIconQuestionClicked(View v) {
        Intent intent = new Intent(ScanscreenActivity.this, Pop.class);
        ScanscreenActivity.this.startActivity(intent);
    }
}
