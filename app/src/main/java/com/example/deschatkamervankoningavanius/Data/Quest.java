package com.example.deschatkamervankoningavanius.Data;

public abstract class Quest {

    private int QuestImage;
    private String title;
    private String desc;
    private boolean finished;
    private String solution;
    private QuestionType questionType;
    private String recievedLetter;

    public Quest(int questImage, String title, String desc, QuestionType questionType, String solution, boolean finished, String recievedLetter) {
        QuestImage = questImage;
        this.title = title;
        this.desc = desc;
        this.questionType = questionType;
        this.solution = solution;
        this.finished = finished;
        this.recievedLetter = recievedLetter;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getQuestImage() {
        return QuestImage;
    }

    public void setQuestImage(int questImage) {
        QuestImage = questImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSolution() {
        return solution;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public abstract String getButtonOption(String option);

    public String getRecievedLetter() {
        return recievedLetter;
    }

    public void setRecievedLetter(String recievedLetter) {
        this.recievedLetter = recievedLetter;
    }
}
