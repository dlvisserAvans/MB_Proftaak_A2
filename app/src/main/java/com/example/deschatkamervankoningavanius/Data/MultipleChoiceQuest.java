package com.example.deschatkamervankoningavanius.Data;

public class MultipleChoiceQuest extends Quest {

    private String buttonOptionA;
    private String buttonOptionB;
    private String buttonOptionC;
    private String buttonOptionD;

    public MultipleChoiceQuest(int questImage, String title, String desc,  String solution) {
        super(questImage, title, desc, QuestionType.MULTIPLECHOICE, solution);

        this.buttonOptionA = "A";
        this.buttonOptionB = "B";
        this.buttonOptionC = "C";
        this.buttonOptionD = "D";
    }

    public String getButtonOptionA(){
        return this.buttonOptionA;
    }

    public void setButtonOptionA(){

    }
}
