package com.example.deschatkamervankoningavanius;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.deschatkamervankoningavanius.Video.VideoActivity;
import static com.example.deschatkamervankoningavanius.Video.VideoActivity.EXTRA_VIDEO_REF;
import com.example.deschatkamervankoningavanius.Fragments.MenuFragment;

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
        TextView textView = findViewById(R.id.password_edit_text);
        textView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onButtonCheckClicked(null);
                    return true;
                }
                return false;
            }
        });
    }

    public void onButtonCheckClicked(View view) {
        TextView textView = findViewById(R.id.password_edit_text);
        String password = textView.getText().toString();
        Intent intent = new Intent(this, NavFragmentBaseActivity.class);
        switch (password) {
            case easy:
                Toast.makeText(this, "Easy selected", Toast.LENGTH_SHORT).show();
                //add the value from the barcode to the intent
                intent.putExtra("Difficulty", Difficulty.Easy);
                break;
            case medium:
                Toast.makeText(this, "medium selected", Toast.LENGTH_SHORT).show();
                intent.putExtra("Difficulty", Difficulty.Medium);
                break;
            case hard:
                Toast.makeText(this, "hard selected", Toast.LENGTH_SHORT).show();
                intent.putExtra("Difficulty", Difficulty.Hard);
                break;
            default:
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
                return;
        }
        startActivity(intent);

        //create intent to play intro  video
        Intent videoIntent = new Intent(this, VideoActivity.class);
        videoIntent.putExtra(EXTRA_VIDEO_REF, "W0wQ8WkFikg");

        startActivity(videoIntent);
    }
}
