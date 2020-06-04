package com.example.deschatkamervankoningavanius.Data;

import com.example.deschatkamervankoningavanius.Difficulty;
import com.example.deschatkamervankoningavanius.R;

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

    public User(){
        this.progress = new ArrayList<>();
        progressTracker = 0;
        this.quests = new ArrayList<>();
    }

    public void getQuests(Difficulty difficulty, JSONParser jsonParser){
//        List<Quest> allQuestsList = jsonParser.JsonParse();
        List<Quest> allQuestsList = initQuest();
        Collections.shuffle(allQuestsList);

        //Change amount of quests based on difficulty
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

    public ArrayList<Quest> initQuest(){
        ArrayList<Quest> quests = new ArrayList<>();

        quests.add(new OpenQuestionQuest(R.drawable.brochure,R.string.draak,R.string.draakQuest1,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.brochure,R.string.draak,R.string.draakQuest2,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.brochure,R.string.draak,R.string.draakQuest3,"A"));
        quests.add(new OpenQuestionQuest(R.drawable.namecard,R.string.repelsteeltje,R.string.repelsteeltjeQuest2,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.namecard,R.string.repelsteeltje,R.string.repelsteeltjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.brochure,R.string.zesDienaren,R.string.zesDienarenQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.doornroosje,R.string.doornroosjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.doornroosje,R.string.doornroosjeQuest2,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.roodkapje,R.string.roodkapjeQuest1,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.roodkapje,R.string.roodkapjeQuest2,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.sticker,R.string.pinokkio,R.string.pinokkioQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.pinokkio,R.string.pinokkioQuest2,"A", "A", "B", "C", "D"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.pinokkio,R.string.pinokkioQuest3,"A", "A", "B", "C", "D"));
        quests.add(new OpenQuestionQuest(R.drawable.sticker,R.string.raponsje,R.string.raponsjeQuest1,"A"));
        quests.add(new MultipleChoiceQuest(R.drawable.sticker,R.string.raponsje,R.string.raponsjeQuest2,"A", "A", "B", "C", "D"));

        return quests;
    }
}
