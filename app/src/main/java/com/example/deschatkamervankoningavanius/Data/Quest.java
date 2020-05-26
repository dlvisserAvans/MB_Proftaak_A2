package com.example.deschatkamervankoningavanius.Data;

public class Quest {

    private int QuestImage;
    private String title;
    private String desc;

    public Quest(int questImage, String title, String desc) {
        QuestImage = questImage;
        this.title = title;
        this.desc = desc;
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
}
