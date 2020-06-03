package com.example.deschatkamervankoningavanius.Fragments.Quests;

import android.graphics.Color;
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

        this.buttonOptionA = (Button)rootView.findViewById(R.id.btnMCAwnserA);
        this.buttonOptionB = (Button)rootView.findViewById(R.id.btnMCAwnserB);
        this.buttonOptionC = (Button)rootView.findViewById(R.id.btnMCAwnserC);
        this.buttonOptionD = (Button)rootView.findViewById(R.id.btnMCAwnserD);

        Bundle bundle = this.getArguments();
        this.solution = bundle.getString("solution");
        this.buttonOptionA.setText(bundle.getString("optionA"));
        this.buttonOptionB.setText(bundle.getString("optionB"));
        this.buttonOptionC.setText(bundle.getString("optionC"));
        this.buttonOptionD.setText(bundle.getString("optionD"));
        System.out.println("Solution: " + solution);

        this.buttonOptionA.setOnClickListener(this);
        this.buttonOptionB.setOnClickListener(this);
        this.buttonOptionC.setOnClickListener(this);
        this.buttonOptionD.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMCAwnserA:
                checkAnswer(this.buttonOptionA.getText().toString(), this.buttonOptionA);
                break;
            case R.id.btnMCAwnserB:
                checkAnswer(this.buttonOptionB.getText().toString(), this.buttonOptionB);
                break;
            case R.id.btnMCAwnserC:
                checkAnswer(this.buttonOptionC.getText().toString(), this.buttonOptionC);
                break;
            case R.id.btnMCAwnserD:
                checkAnswer(this.buttonOptionD.getText().toString(), this.buttonOptionD);
                break;
        }
    }

    public void checkAnswer(String string, Button button){
        if (string.equals(solution)){
            Toast.makeText(getActivity().getApplicationContext(),"CORRECT",Toast.LENGTH_SHORT).show();
            button.setBackgroundColor(Color.GREEN);
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
        }
    }
}
