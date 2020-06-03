package com.example.deschatkamervankoningavanius.Data;

import java.util.ArrayList;

public class MultipleChoiceQuest extends Quest {

    private String buttonOptionA;
    private String buttonOptionB;
    private String buttonOptionC;
    private String buttonOptionD;
    private ArrayList options;

    public MultipleChoiceQuest(int questImage, String title, String desc,  String solution, String buttonOptionA, String buttonOptionB, String buttonOptionC, String buttonOptionD, boolean finished) {
        super(questImage, title, desc, QuestionType.MULTIPLECHOICE, solution, finished);

        this.buttonOptionA = buttonOptionA;
        this.buttonOptionB = buttonOptionB;
        this.buttonOptionC = buttonOptionC;
        this.buttonOptionD = buttonOptionD;

        this.options = new ArrayList<>();
        this.options.add(buttonOptionA);
        this.options.add(buttonOptionB);
        this.options.add(buttonOptionC);
        this.options.add(buttonOptionD);
    }

    public String getButtonOption(String option){
        if (option.equals("A")){
            return this.buttonOptionA;
        } else if (option.equals("B")){
            return this.buttonOptionB;
        } else if (option.equals("C")){
            return this.buttonOptionC;
        } else if (option.equals("D")) {
            return this.buttonOptionD;
        } else {
            return "NULL";
        }
    }
}
