package com.example.deschatkamervankoningavanius.Fragments.Quests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.Fragments.HomeFragment;
import com.example.deschatkamervankoningavanius.R;

public class OpenQuestionFragment extends Fragment {

    private Button buttonSubmit;
    private EditText answer;
    private String solution = "";
    private int listValue;
    private TextView titleview;
    private TextView descview;
    private boolean questionFinished = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quest_openquestionfragment, container, false);

        this.buttonSubmit = (Button)rootView.findViewById(R.id.button7);
        this.answer = (EditText)rootView.findViewById(R.id.editTextTextPersonName);
        this.titleview = rootView.findViewById(R.id.tvOpentitle);
        this.descview = rootView.findViewById(R.id.tvOpendesc);

        if (!questionFinished){
        Bundle bundle = this.getArguments();
        titleview.setText(bundle.getInt("title"));
        descview.setText(bundle.getInt("desc"));
        this.solution = bundle.getString("solution");
        listValue = bundle.getInt("listValue");
        System.out.println("OpenQuestion --- Solution: " + solution);}

        if (!questionFinished){
        this.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(answer.getText().toString());
            }
        });}else {
            Toast.makeText(getActivity().getApplicationContext(),"Question Finished",Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    public void checkAnswer(String string){
        if (string.equals(solution)){
            Toast.makeText(getActivity().getApplicationContext(),"CORRECT",Toast.LENGTH_SHORT).show();
            HomeFragment.setQuestState(listValue, true);
            HomeFragment.setProgressBar();
            finishedQuestion();
            questionFinished = true;
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
            HomeFragment.setQuestState(listValue, false);
            questionFinished = false;
        }
    }

    public void finishedQuestion(){
        this.buttonSubmit.setClickable(false);
        this.buttonSubmit.setBackgroundResource(R.color.color_btn_locked);
    }
}
