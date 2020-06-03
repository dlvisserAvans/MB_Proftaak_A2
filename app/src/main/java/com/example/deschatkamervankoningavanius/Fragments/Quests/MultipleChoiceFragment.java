package com.example.deschatkamervankoningavanius.Fragments.Quests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.R;

public class MultipleChoiceFragment extends Fragment implements View.OnClickListener{

    private Button buttonOptionA;
    private Button buttonOptionB;
    private Button buttonOptionC;
    private Button buttonOptionD;

    private String solution = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quest_multiplechoice,container,false);

        this.buttonOptionA = rootView.findViewById(R.id.btnMCAwnserA);
        this.buttonOptionB = rootView.findViewById(R.id.btnMCAwnserB);
        this.buttonOptionC = rootView.findViewById(R.id.btnMCAwnserC);
        this.buttonOptionD = rootView.findViewById(R.id.btnMCAwnserD);

        this.buttonOptionA.setOnClickListener(this);
        this.buttonOptionB.setOnClickListener(this);
        this.buttonOptionC.setOnClickListener(this);
        this.buttonOptionD.setOnClickListener(this);

        System.out.println(getActivity().getIntent().getStringExtra("solution"));
        this.solution = getActivity().getIntent().getStringExtra("solution");

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMCAwnserA:
                checkAnswer(this.buttonOptionA.getText().toString());
                break;
            case R.id.btnMCAwnserB:
                checkAnswer(this.buttonOptionB.getText().toString());
                break;
            case R.id.btnMCAwnserC:
                checkAnswer(this.buttonOptionC.getText().toString());
                break;
            case R.id.btnMCAwnserD:
                checkAnswer(this.buttonOptionD.getText().toString());
                break;
        }
    }

    public void checkAnswer(String string){
        if (string.equals(solution)){
            Toast.makeText(getActivity().getApplicationContext(),"CORRECT",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
        }
    }

    public void getButtonText(){

    }
}
