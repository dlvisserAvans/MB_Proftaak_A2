package com.example.deschatkamervankoningavanius.Data;

import java.io.Serializable;
import java.util.ArrayList;
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

    public User(List<Quest> quests, List<Character> answers, List<String> videos){
        this.quests = quests;
        this.answers = answers;
        this.progress = new ArrayList<>();
        this.videos = videos;
        progressTracker = 0;
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
