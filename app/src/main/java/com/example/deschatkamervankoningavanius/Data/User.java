package com.example.deschatkamervankoningavanius.Data;

import com.example.deschatkamervankoningavanius.Difficulty;
import com.example.deschatkamervankoningavanius.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//TODO add save function
public class User implements Serializable {

    //TODO tasks en videos een goede identifier geven (nu tijdelijk strings)
    private List<Quest> quests; //opdrachten die je moet doen
    private List<Character>  answers; //letters van het woord wat je kan krijgen
    private List<Character> progress; //letters van het woord wat je hebt
    private List<String> videos; //videos die voor de user beschikbaar zijn
    private int progressTracker;
    private String password = "";

    public User(){
        this.progress = new ArrayList<>();
        progressTracker = 0;
        this.quests = new ArrayList<>();
    }

    public void getQuests(Difficulty difficulty, JSONParser jsonParser){
//        List<Quest> allQuestsList = jsonParser.JsonParse();
        List<Quest> allQuestsList = initQuest();
        Collections.shuffle(allQuestsList);
        ArrayList<Character> letters = new ArrayList<>();

        //Change amount of quests based on difficulty
        int questAmount = 1;
        switch (difficulty){
            case Easy:
                questAmount = 3;
                this.password = "aaa";
                break;
            case Medium:
                questAmount = 5;
                this.password = "woord";
                break;
            case Hard:
                questAmount = 7;
                this.password = "letters";
                break;
        }

        //Add specified amount of quests to the user's quest List
        for (int i = 0; (quests.size() < questAmount); i++){
            int newQuestTitle = allQuestsList.get(i).getTitle();
            boolean duplicate = false;

            //Makes sure the user doesn't get multiple quests for the same fairy tale
            for (Quest quest : quests){
                if (quest.getTitle() == newQuestTitle){
                    duplicate = true;
                }
            }
            if (!duplicate){
                quests.add(allQuestsList.get(i));
            }
        }
        for(int i = 0; i < this.password.length(); i++){
            letters.add(this.password.charAt(i));
        }

        Collections.shuffle(letters);
        int i =0;
        for (Character character : letters){
            if (i == this.quests.size()){
                i=0;
            }
            //Get letters already assigned to quest and add a new one
            ArrayList<Character> existingLetters = quests.get(i).getLetters();
            existingLetters.add(character);
            quests.get(i).setLetters(existingLetters);
            i++;
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

    public String getPassword() {
        return password;
    }

    public ArrayList<Quest> initQuest(){
        ArrayList<Quest> quests = new ArrayList<>();

        quests.add(new OpenQuestionQuest(R.drawable.draak,R.string.draak,R.string.draakQuest1,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.draak,R.string.draak,R.string.draakQuest2,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.draak,R.string.draak,R.string.draakQuest3,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.repelsteeltje,R.string.repelsteeltje,R.string.repelsteeltjeQuest2,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.repelsteeltje,R.string.repelsteeltje,R.string.repelsteeltjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.langnek,R.string.zesDienaren,R.string.zesDienarenQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.doornroosje,R.string.doornroosje,R.string.doornroosjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.doornroosje,R.string.doornroosje,R.string.doornroosjeQuest2,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.roodkapje,R.string.roodkapje,R.string.roodkapjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.roodkapje,R.string.roodkapje,R.string.roodkapjeQuest2,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.pinokkio,R.string.pinokkio,R.string.pinokkioQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.pinokkio,R.string.pinokkio,R.string.pinokkioQuest2,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.pinokkio,R.string.pinokkio,R.string.pinokkioQuest3,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.raponsje,R.string.raponsje,R.string.raponsjeQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.raponsje,R.string.raponsje,R.string.raponsjeQuest2,"A", "A", "B", "C", "D"));

        return quests;
    }
}
