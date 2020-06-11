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

        adapter.setItems(new String[]{
                getResources().getString(R.string.help_howtoplay),
                getResources().getString(R.string.help_rules),
                getResources().getString(R.string.help_created_by)});

        adapter.setItemanwsers(new String[][]{
                {getResources().getString(R.string.help_howtoplay_answer)},
                {getResources().getString(R.string.help_rules_answer)},
                {getResources().getString(R.string.help_created_by_answer)}});

        expandableTextView.setAdapter(adapter);


    }

    public void onButtonBackClicked(View view){
        finish();
    }
}
