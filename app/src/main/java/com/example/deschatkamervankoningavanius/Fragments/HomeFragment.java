package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
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
import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.QuestionType;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Fragments.Quests.MultipleChoiceFragment;
import com.example.deschatkamervankoningavanius.Fragments.Quests.OpenQuestionFragment;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.Video.YoutubeVideo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment(User user) {
        this.user = user;
    }


    List<Quest> allQuestList = new ArrayList<>();
    ArrayList<Character> password = new ArrayList<>();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
//    TextView titleView;
//    TextView descView;
    User user;
    private ViewPager viewPager;
    private VPAdapter vpAdapter;
    private static List<Quest> questList;
    private static ProgressBar progressBar;
    private static double progress = 0;
    private static double progress_step;
    private static TextView tvProgress;
    private static int correctAnswers = 0;
    private Bundle bundle;
    private Fragment currentFragment;
    private Quest currentQuest;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;
    private static ArrayList<Fragment> fragments = new ArrayList<>();
    private TextView solution;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);

        questList = user.getQuests();
//        ArrayList<Quest> allQuestsList = new ArrayList<>();     //TODO Load all quests into this List instead of questList
        this.bundle = new Bundle();


        for(Quest quest : questList){
            for(YoutubeVideo video : YoutubeVideo.getYoutubeVideos()){
                if(video.getVideoTitle().equals(getString(quest.getTitle()))){
                    video.setAvailable(true);
                }
            }
        }



        System.out.println("Index questionlist: " + questList.size());
        for (int i = 0; i < questList.size(); i++){
            System.out.println((i+1) + " Question state: " + questList.get(i).isFinished());
        }

        if (fragments.size() == 0){
            for (Quest quest : questList){
                if (quest.getQuestionType().equals(QuestionType.MULTIPLECHOICE)){
                    fragments.add(new MultipleChoiceFragment());
                } else {
                    fragments.add(new OpenQuestionFragment());
                }
            }
        }

        this.currentFragment = fragments.get(0);

        //set the fragment of the first question
        if (questList.get(0).getQuestionType().equals(QuestionType.MULTIPLECHOICE)) {
            bundle.putInt("title",questList.get(0).getTitle());
            bundle.putInt("desc",questList.get(0).getDesc());
            bundle.putString("solution", questList.get(0).getSolution());
            bundle.putString("optionA", questList.get(0).getButtonOption("A"));
            bundle.putString("optionB", questList.get(0).getButtonOption("B"));
            bundle.putString("optionC", questList.get(0).getButtonOption("C"));
            bundle.putString("optionD", questList.get(0).getButtonOption("D"));
            bundle.putInt("listValue", 0);
            this.currentFragment.setArguments(this.bundle);

        } else {
            bundle.putInt("title",questList.get(0).getTitle());
            bundle.putInt("desc",questList.get(0).getDesc());
            bundle.putString("solution", questList.get(0).getSolution());
            bundle.putInt("listValue", 0);
            this.currentFragment.setArguments(bundle);
        }

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        final Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout_quest,fragment);
        fragmentTransaction.commit();

        progress_step = 100/questList.size();
        progressBar = rootView.findViewById(R.id.progress_bar);
        tvProgress = rootView.findViewById(R.id.tvProgress);
        tvProgress.setText(progress + "%");
        progressBar.setProgress((int)progress);

        vpAdapter = new VPAdapter(questList,getActivity());
        viewPager.setAdapter(vpAdapter);

        setProgressBar();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
                System.out.println("Position: " + position);

                switch (questList.get(position).getQuestionType()) {
                    case OPENQUESTION:
                        OpenQuestionFragment openQuestionFragment = (OpenQuestionFragment) fragments.get(position);
                        currentFragment = openQuestionFragment;
                        bundle.putInt("title", questList.get(position).getTitle());
                        bundle.putInt("desc", questList.get(position).getDesc());
                        bundle.putString("solution", questList.get(position).getSolution());
                        bundle.putInt("listValue", position);
                        currentFragment.setArguments(bundle);
                        break;

                    case MULTIPLECHOICE:
                        MultipleChoiceFragment multipleChoiceFragment = (MultipleChoiceFragment) fragments.get(position);
                        currentFragment = multipleChoiceFragment;
                        bundle.putInt("title", questList.get(position).getTitle());
                        bundle.putInt("desc", questList.get(position).getDesc());
                        bundle.putString("solution", questList.get(position).getSolution());
                        bundle.putString("optionA", questList.get(position).getButtonOption("A"));
                        bundle.putString("optionB", questList.get(position).getButtonOption("B"));
                        bundle.putString("optionC", questList.get(position).getButtonOption("C"));
                        bundle.putString("optionD", questList.get(position).getButtonOption("D"));
                        bundle.putInt("listValue", position);
                        currentFragment.setArguments(bundle);
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
        if (correctAnswers == questList.size()){
            progress = 100;
        }else {
            progress = correctAnswers * progress_step;
        }
        progressBar.setProgress((int)progress);
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
                string += quest.getLetters().get(0) + " ";
            } else {
                string += ". ";
            }
        }
        return string;
    }
}
