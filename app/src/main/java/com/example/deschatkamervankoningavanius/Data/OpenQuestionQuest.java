package com.example.deschatkamervankoningavanius.Data;

public class OpenQuestionQuest extends Quest {

    public OpenQuestionQuest(int questImage, String title, String desc, String solution) {
        super(questImage, title, desc, QuestionType.OPENQUESTION, solution);
    }
}
