package com.example.deschatkamervankoningavanius.Data;

import com.example.deschatkamervankoningavanius.Difficulty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


//TODO add save function
public class User implements Serializable {

    //TODO tasks en videos een goede identifier geven (nu tijdelijk strings)
    private List<Quest> quests; //opdrachten die je moet doen
    private List<Character>  answers; //letters van het woord wat je kan krijgen
    private List<Character> progress; //letters van het woord wat je hebt
    private List<String> videos; //videos die voor de user beschikbaar zijn
    private int progressTracker;

    public User(List<Character> answers, List<String> videos){
        this.answers = answers;
        this.progress = new ArrayList<>();
        this.videos = videos;
        progressTracker = 0;
        this.quests = new ArrayList<>();
    }

    public void getQuests(Difficulty difficulty, JSONParser jsonParser){
        List<Quest> allQuestsList = jsonParser.JsonParse();
        Collections.shuffle(allQuestsList);

        int questAmount = 1;
        switch (difficulty){
            case Easy:
                questAmount = 3;
                break;
            case Medium:
                questAmount = 5;
                break;
            case Hard:
                questAmount = 7;
                break;
        }

        for (int i = 0; (quests.size() < questAmount); i++){
            String newQuestTitle = allQuestsList.get(i).getTitle();
            boolean duplicate = false;

            for (Quest quest : quests){
                if (quest.getTitle().equals(newQuestTitle)){
                    System.out.println("SAME TITLE");
                    duplicate = true;
                }
            }
            if (!duplicate){
                quests.add(allQuestsList.get(i));
            }
        }
    }

    public void updateProgress(int amount){

        for(int i = 0; i < amount; i++){
            if(this.answers.size() < progressTracker) {
                this.progress.add(this.answers.get(progressTracker));
                progressTracker++;
            }
        }
    }

    //TODO deze methode goed maken zodat er een video beschikbaar kan worden gesteld
    public void updateVideos(String video){
        videos.add(video);
    }


    public List<Quest> getQuests() {
        return quests;
    }

    public void setQuests(List<Quest> quests) {
        this.quests = quests;
    }

    public List<Character> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Character> answers) {
        this.answers = answers;
    }

    public List<Character> getProgress() {
        return progress;
    }

    public void setProgress(List<Character> progress) {
        this.progress = progress;
    }
}
