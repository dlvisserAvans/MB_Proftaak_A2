package com.example.deschatkamervankoningavanius.Data;

public class OpenQuestionQuest extends Quest {

    public OpenQuestionQuest(int questImage, String title, String desc, String solution, boolean finished) {
        super(questImage, title, desc, QuestionType.OPENQUESTION, solution, finished);
    }

    @Override
    public String getButtonOption(String option)  {return null; }
}
