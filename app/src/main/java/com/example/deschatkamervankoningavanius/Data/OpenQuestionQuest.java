package com.example.deschatkamervankoningavanius.Data;

public class OpenQuestionQuest extends Quest {

    public OpenQuestionQuest(int questImage, int title, int desc, String solution) {
        super(questImage, title, desc, QuestionType.OPENQUESTION, solution);
    }

    @Override
    public String getButtonOption(String option)  {return null; }
}
