package com.example.deschatkamervankoningavanius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Pop extends Activity {
    private final String easy = "EASY", medium = "MEDIUM", hard = "HARD";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupquestion);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;

        getWindow().setLayout((int) (widthPixels * .9), (int) (heightPixels * .7));
    }

    public void onButtonCheckClicked(View view) {
        TextView textView = findViewById(R.id.password_edit_text);
        String password = textView.getText().toString();
        switch (password) {
            case "easy":
                Toast.makeText(this, "Easy selected", Toast.LENGTH_SHORT).show();
                //we create a intent to go to the home page
                Intent intentEasy = new Intent(this, NavFragmentBaseActivity.class);
                //add the value from the barcode to the intent
                intentEasy.putExtra("Difficulty", Difficulty.Easy);
                //start the intent
                startActivity(intentEasy);
                break;
            case "medium":
                Toast.makeText(this, "medium selected", Toast.LENGTH_SHORT).show();
                //we create a intent to go to the home page
                Intent intentMedium = new Intent(this, NavFragmentBaseActivity.class);
                //add the value from the barcode to the intent
                intentMedium.putExtra("Difficulty", Difficulty.Medium);
                //start the intent
                startActivity(intentMedium);
                break;
            case "hard":
                Toast.makeText(this, "hard selected", Toast.LENGTH_SHORT).show();
                //we create a intent to go to the home page
                Intent intentHard = new Intent(this, NavFragmentBaseActivity.class);
                //add the value from the barcode to the intent
                intentHard.putExtra("Difficulty", Difficulty.Hard);
                //start the intent
                startActivity(intentHard);
                break;

            default:
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
