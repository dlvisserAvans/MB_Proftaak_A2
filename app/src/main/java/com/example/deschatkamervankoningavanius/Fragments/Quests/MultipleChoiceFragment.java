package com.example.deschatkamervankoningavanius.Fragments.Quests;

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

import com.example.deschatkamervankoningavanius.Fragments.HomeFragment;
import com.example.deschatkamervankoningavanius.R;

import java.util.ArrayList;

public class MultipleChoiceFragment extends Fragment implements View.OnClickListener{

    private Button buttonOptionA;
    private Button buttonOptionB;
    private Button buttonOptionC;
    private Button buttonOptionD;

    private TextView titleview;
    private TextView descview;

    private Bundle bundle;
    private String solution = "";
    private int clickAmount = 0;
    private int listValue;
    ArrayList<Button> buttons = new ArrayList<>();
    private boolean questionFinished = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_quest_multiplechoice,container,false);

        this.buttonOptionA = (Button)rootView.findViewById(R.id.btnMCAwnserA);
        this.buttonOptionB = (Button)rootView.findViewById(R.id.btnMCAwnserB);
        this.buttonOptionC = (Button)rootView.findViewById(R.id.btnMCAwnserC);
        this.buttonOptionD = (Button)rootView.findViewById(R.id.btnMCAwnserD);
        this.titleview = rootView.findViewById(R.id.tvMulttitle);
        this.descview = rootView.findViewById(R.id.tvMultdesc);


//        if (this.buttons.size() == 0){
//            this.buttons.add(buttonOptionA);
//            this.buttons.add(buttonOptionB);
//            this.buttons.add(buttonOptionC);
//            this.buttons.add(buttonOptionD);
//        }

        if (!this.questionFinished){
            bundle = this.getArguments();
            titleview.setText(bundle.getInt("title"));
            descview.setText(bundle.getInt("desc"));
            this.solution = bundle.getString("solution");
            this.buttonOptionA.setText(bundle.getString("optionA"));
            this.buttonOptionB.setText(bundle.getString("optionB"));
            this.buttonOptionC.setText(bundle.getString("optionC"));
            this.buttonOptionD.setText(bundle.getString("optionD"));
            listValue = bundle.getInt("listValue");
            System.out.println("MultipleChoiceQuestion --- Solution: " + solution);

            this.buttonOptionA.setOnClickListener(this);
            this.buttonOptionB.setOnClickListener(this);
            this.buttonOptionC.setOnClickListener(this);
            this.buttonOptionD.setOnClickListener(this);
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"Question Finished",Toast.LENGTH_SHORT).show();
            finishedQuestion();
        }
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (questionFinished == false){
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
        } else {
//            Toast.makeText(getActivity().getApplicationContext(),"Question Finished",Toast.LENGTH_SHORT).show();
            System.out.println("You can't press the buttons");
        }

    }

    public void checkAnswer(String string, Button button){
        if (string.equals(solution)){
            button.setBackgroundResource(R.color.color2);
            this.clickAmount++;
            questionFinished = true;
            System.out.println("Pogingen: " + this.clickAmount);
            HomeFragment.setQuestState(listValue, true);
            HomeFragment.setProgressBar();
            finishedQuestion();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
            this.clickAmount++;
        }
    }

    public void finishedQuestion(){
//        for (Button b : buttons){
//            b.setClickable(false);
//            b.setBackgroundResource(R.color.color_btn_locked);
//        }
        this.buttonOptionA.setBackgroundResource(R.drawable.rounded_button_locked);
        this.buttonOptionB.setBackgroundResource(R.drawable.rounded_button_locked);
        this.buttonOptionC.setBackgroundResource(R.drawable.rounded_button_locked);
        this.buttonOptionD.setBackgroundResource(R.drawable.rounded_button_locked);

        this.buttonOptionA.setText(bundle.getString("optionA"));
        this.buttonOptionB.setText(bundle.getString("optionB"));
        this.buttonOptionC.setText(bundle.getString("optionC"));
        this.buttonOptionD.setText(bundle.getString("optionD"));
    }
}
