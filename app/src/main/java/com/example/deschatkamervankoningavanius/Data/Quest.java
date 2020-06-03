package com.example.deschatkamervankoningavanius.Data;

public abstract class Quest {

    private int QuestImage;
    private String title;
    private String desc;
    private boolean finished;
    private String solution;
    private QuestionType questionType;

    public Quest(int questImage, String title, String desc, QuestionType questionType, String solution) {
        QuestImage = questImage;
        this.title = title;
        this.desc = desc;
        this.questionType = questionType;
        this.solution = solution;
        this.finished = false;
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

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
}
