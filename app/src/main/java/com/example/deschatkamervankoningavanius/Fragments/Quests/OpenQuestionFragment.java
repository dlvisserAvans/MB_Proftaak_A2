package com.example.deschatkamervankoningavanius.Fragments.Quests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.R;

public class OpenQuestionFragment extends Fragment {

    private Button buttonSubmit;
    private EditText answer;
    private String solution = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quest_openquestionfragment, container, false);

        this.buttonSubmit = (Button)rootView.findViewById(R.id.button7);
        this.answer = (EditText)rootView.findViewById(R.id.editTextTextPersonName);

        Bundle bundle = this.getArguments();
        this.solution = bundle.getString("solution");
        System.out.println("Solution: " + solution);

        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer.getText().toString());
            }
        });

        return rootView;
    }

    public void checkAnswer(String string){
        if (string.equals(solution)){
            Toast.makeText(getActivity().getApplicationContext(),"CORRECT",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
        }
    }
}
