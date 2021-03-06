package com.example.deschatkamervankoningavanius.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.HelpMenuActivity;
import com.example.deschatkamervankoningavanius.NavFragmentBaseActivity;
import com.example.deschatkamervankoningavanius.PopUpConfirmReset;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.ScanscreenActivity;
import com.example.deschatkamervankoningavanius.SettingsMenuActivity;

public class MenuFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        Button buttonHelp = view.findViewById(R.id.btn_menu_help);
        Button buttonSettings = view.findViewById(R.id.btn_menu_settings);
        Button buttonChooseDifficulty = view.findViewById(R.id.btn_menu_choose_difficulty);

        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonHelpClicked(v);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonSettingsClicked(v);
            }
        });

        buttonChooseDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonChooseDifficultyClicked(v);
            }
        });


        return view;
    }

    public void onButtonSettingsClicked(View view) {
        //TODO: Integrate function when settingsmenu is finished
        Intent intent = new Intent(getActivity(), SettingsMenuActivity.class);
        startActivity(intent);

//        Toast.makeText(getActivity(), "Settings",
//                Toast.LENGTH_SHORT).show();
    }

    public void onButtonHelpClicked(View view) {
        //TODO: Integrate function when helpmenu is finished
        Intent intent = new Intent(getActivity(), HelpMenuActivity.class);
        startActivity(intent);

//        Toast.makeText(getActivity(), "Help",
//                Toast.LENGTH_SHORT).show();
    }

    public void onButtonChooseDifficultyClicked(View view) {
        Intent intent = new Intent(getActivity(), PopUpConfirmReset.class);
        startActivity(intent);
    }
}
