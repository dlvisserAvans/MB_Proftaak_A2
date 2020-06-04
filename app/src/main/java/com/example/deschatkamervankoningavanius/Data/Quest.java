package com.example.deschatkamervankoningavanius.Data;

public class Quest {

    private int QuestImage;
    private String title;
    private String desc;
    private boolean solved;

    public Quest(int questImage, String title, String desc) {
        QuestImage = questImage;
        this.title = title;
        this.desc = desc;
        this.solved = false;
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

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }
}
