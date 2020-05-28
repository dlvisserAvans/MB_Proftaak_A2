package com.example.deschatkamervankoningavanius;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deschatkamervankoningavanius.Adapters.ExpandableTextViewAdapter;

public class HelpMenuActivity extends AppCompatActivity {

    ExpandableListView expandableTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpscreen);

        expandableTextView=findViewById(R.id.ExtvHelp);
        ExpandableTextViewAdapter adapter= new ExpandableTextViewAdapter(HelpMenuActivity.this);
        expandableTextView.setAdapter(adapter);
    }

    public void onButtonBackClicked(View view){
        finish();
    }
}
