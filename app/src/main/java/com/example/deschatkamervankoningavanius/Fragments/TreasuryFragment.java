package com.example.deschatkamervankoningavanius.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.R;

public class TreasuryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_treasury,container,false);
        TextView textView = view.findViewById(R.id.tvQuestTitle2);
        textView.setText(HomeFragment.setTextView());
        Button buttonCheck = view.findViewById(R.id.btn_treasury_check);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonCheckClicked(v);
            }
        });

        return view;
    }

    public void onButtonCheckClicked(View view){
        //TODO: Integrate function when Checkmenu is finished
//        Intent intent = new Intent(getActivity(),SettingsMenuActivity.class);
//        startActivity(intent);

        Toast.makeText(getActivity(), "Check",
                Toast.LENGTH_SHORT).show();
    }
}
