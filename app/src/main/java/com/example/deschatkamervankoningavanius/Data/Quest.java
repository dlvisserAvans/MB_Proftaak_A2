package com.example.deschatkamervankoningavanius.Data;
public abstract class Quest {
    private int QuestImage;
    private int title;
    private int desc;
    private boolean finished;
    private String solution;
    private QuestionType questionType;
    private String recievedLetter;

    public Quest(int questImage, int title, int desc, QuestionType questionType, String solution) {
        QuestImage = questImage;
        this.title = title;
        this.desc = desc;
        this.questionType = questionType;
        this.solution = solution;
        this.finished = false;
        recievedLetter = "";
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
    public int getTitle() {
        return title;
    }
    public void setTitle(int title) {
        this.title = title;
    }
    public int getDesc() {
        return desc;
    }
    public void setDesc(int desc) {
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