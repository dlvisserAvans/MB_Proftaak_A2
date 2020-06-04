package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.deschatkamervankoningavanius.Adapters.VPAdapter;
import com.example.deschatkamervankoningavanius.Data.MultipleChoiceQuest;
import com.example.deschatkamervankoningavanius.Data.OpenQuestionQuest;
import com.example.deschatkamervankoningavanius.Data.JSONParser;
import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.QuestionType;
import com.example.deschatkamervankoningavanius.Fragments.Quests.MultipleChoiceFragment;
import com.example.deschatkamervankoningavanius.Fragments.Quests.OpenQuestionFragment;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Difficulty;
import com.example.deschatkamervankoningavanius.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment(User user) {
        this.user = user;
    }


    List<Quest> allQuestList = new ArrayList<>();
    ArrayList<Character> password = new ArrayList<>();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView titleView;
    TextView descView;
    User user;
    private ViewPager viewPager;
    private VPAdapter vpAdapter;
    private static List<Quest> questList;
    private static ProgressBar progressBar;
    private static int progress = 0;
    private static int progress_step;
    private static TextView tvProgress;
    private static int correctAnswers = 0;
    private Bundle bundle;
    private Fragment currentFragment;
    private Quest currentQuest;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;
    private static ArrayList<Fragment> fragments;
    private TextView solution;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);

//        questList = new ArrayList<>();
//        ArrayList<Quest> allQuestsList = new ArrayList<>();     //TODO Load all quests into this List instead of questList
        this.bundle = new Bundle();
        solution = rootView.findViewById(R.id.tvQuestTitle2);

        //add questions to the questList
        questList = new ArrayList<>();
        questList.add(new MultipleChoiceQuest(R.drawable.draak, "Draak", "", "1", "0", "1", "2", "3", false, "A"));
        questList.add(new OpenQuestionQuest(R.drawable.repelsteeltje,"Repelsteeltje","", "solution1", false, "B"));
        questList.add(new MultipleChoiceQuest(R.drawable.langnek,"Lange Jan","", "A", "A", "B", "C", "D", false, "C"));
        questList.add(new OpenQuestionQuest(R.drawable.doornroosje,"Doornroosje","", "solution2", false, "D"));
        questList.add(new MultipleChoiceQuest(R.drawable.roodkapje,"Roodkapje","", "F", "E", "F", "G", "H", false, "E"));

//        allQuestsList.add(new Quest(R.drawable.brochure,"Test1",""));
//        allQuestsList.add(new Quest(R.drawable.sticker,"Test2",""));
//        allQuestsList.add(new Quest(R.drawable.poster,"Test3",""));
//        allQuestsList.add(new Quest(R.drawable.namecard,"Test4",""));
//        allQuestsList.add(new Quest(R.drawable.brochure,"Test5",""));
//        allQuestsList.add(new Quest(R.drawable.sticker,"Test6",""));
//        allQuestsList.add(new Quest(R.drawable.poster,"Test7",""));
//        allQuestsList.add(new Quest(R.drawable.namecard,"Test8",""));

//        Collections.shuffle(allQuestsList);
//        String difficulty = difficultyIntent.getExtras().get("Difficulty");    //TODO Use this line to get difficulty enum instead of string
//        String difficulty = "easy"; //PLACEHOLDER, REMOVE LATER
//        int questAmount = 1;
//        //TODO change quest amounts?
//        switch (difficulty){
//            case "easy":
//                questAmount = 4;
//                break;
//            case "medium":
//                questAmount = 6;
//                break;
//            case "hard":
//                questAmount = 8;
//                break;
//        }
//
//        for (int i = 0; i < questAmount; i++){
//            questList.add(allQuestsList.get(i));
//        }
//
//        String password = "password";   //TODO implement actual passwords
//        for (int i = 0; i < password.length(); i++){
//            this.password.add(password.charAt(i));
//        }
//
//        Collections.shuffle(this.password);
//
//        List<String> videoList = new ArrayList<>();

//        User user = new User(this.questList, this.password, videoList);


        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);
        titleView = rootView.findViewById(R.id.tvQuestTitle);
        descView = rootView.findViewById(R.id.tvQuestDesc);
        titleView.setText(user.getQuests().get(0).getTitle());
        descView.setText(user.getQuests().get(0).getDesc());
        //add fragments of the questions to the fragmentList
        fragments = new ArrayList<>();
        for (Quest quest : questList){
            if (quest.getQuestionType().equals(QuestionType.MULTIPLECHOICE)){
                fragments.add(new MultipleChoiceFragment());
            } else {
                fragments.add(new OpenQuestionFragment());
            }
        }

        //set the fragment of the first question
        this.bundle.putString("solution", questList.get(0).getSolution());
        bundle.putString("optionA", questList.get(0).getButtonOption("A"));
        bundle.putString("optionB", questList.get(0).getButtonOption("B"));
        bundle.putString("optionC", questList.get(0).getButtonOption("C"));
        bundle.putString("optionD", questList.get(0).getButtonOption("D"));
        bundle.putInt("listValue", 0);
        this.currentFragment = fragments.get(0);
        this.currentFragment.setArguments(this.bundle);
        this.currentQuest = questList.get(0);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout_quest,fragment);
        fragmentTransaction.commit();

        progress_step = 100/questList.size();
        progressBar = rootView.findViewById(R.id.progress_bar);
        tvProgress = rootView.findViewById(R.id.tvProgress);
        tvProgress.setText(progress + "%");
        progressBar.setProgress(progress);

        vpAdapter = new VPAdapter(questList,getActivity());
        viewPager.setAdapter(vpAdapter);

        setProgressBar();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(), user.getQuests().get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            public void onPageSelected(final int position) {
//                currentQuest = questList.get(position);
                System.out.println("Position: " + position);
                switch (questList.get(position).getQuestionType()) {
                    case OPENQUESTION:
                        OpenQuestionFragment openQuestionFragment = (OpenQuestionFragment) fragments.get(position);
                        currentFragment = openQuestionFragment;
                        if (questList.get(position).isFinished()){
                            openQuestionFragment.finishedQuestion();
                        } else {
                            bundle.putString("solution", questList.get(position).getSolution());
                            bundle.putInt("listValue", position);
                            currentFragment.setArguments(bundle);
                        }
                        break;

                titleView.setText(user.getQuests().get(position).getTitle());
                descView.setText(user.getQuests().get(position).getDesc());
                    case MULTIPLECHOICE:
                        MultipleChoiceFragment multipleChoiceFragment = (MultipleChoiceFragment) fragments.get(position);
                        currentFragment = multipleChoiceFragment;
                        if (questList.get(position).isFinished()) {
                            multipleChoiceFragment.finishedQuestion();
                        } else {
                            bundle.putString("solution", questList.get(position).getSolution());
                            bundle.putString("optionA", questList.get(position).getButtonOption("A"));
                            bundle.putString("optionB", questList.get(position).getButtonOption("B"));
                            bundle.putString("optionC", questList.get(position).getButtonOption("C"));
                            bundle.putString("optionD", questList.get(position).getButtonOption("D"));
                            bundle.putInt("listValue", position);
                            currentFragment.setArguments(bundle);
                        }
                        break;
                }

                getChildFragmentManager().beginTransaction().replace(R.id.fragment_layout_quest, currentFragment).commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        return rootView;
    }

    public static void setProgressBar(){
        correctAnswers = 0;
        for (Quest quest : questList){
            if (quest.isFinished()){
                correctAnswers++;
            } else {
                correctAnswers += 0;
            }
        }
        progress = correctAnswers * progress_step;
        progressBar.setProgress(progress);
        tvProgress.setText(progress + "%");
    }

    public static void setQuestState(int position, boolean state){
        System.out.println("Position: " + position + "--- State: " + state);
        questList.get(position).setFinished(state);
    }

    public static String setTextView(){
        String string = "";
        for (Quest quest : questList){
            if (quest.isFinished() == true){
                string += quest.getRecievedLetter() + " ";
            } else {
                string += ". ";
            }
        }
        return string;
    }
}
