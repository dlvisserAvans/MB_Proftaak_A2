package com.example.deschatkamervankoningavanius.Data;

public class MultipleChoiceQuest extends Quest {

    private String buttonOptionA;
    private String buttonOptionB;
    private String buttonOptionC;
    private String buttonOptionD;

    public MultipleChoiceQuest(int questImage, int title, int desc,  String solution, String buttonOptionA, String buttonOptionB, String buttonOptionC, String buttonOptionD) {
        super(questImage, title, desc, QuestionType.MULTIPLECHOICE, solution);

        this.buttonOptionA = buttonOptionA;
        this.buttonOptionB = buttonOptionB;
        this.buttonOptionC = buttonOptionC;
        this.buttonOptionD = buttonOptionD;
    }

    public String getButtonOption(String option){
        String buttonText = "";
        switch (option){
            case "A":
              buttonText = this.buttonOptionA;
              break;
            case "B":
                buttonText = this.buttonOptionB;
                break;
            case "C":
                buttonText = this.buttonOptionC;
                break;
            case "D":
                buttonText = this.buttonOptionD;
                break;
        }
        return buttonText;
    }
}
