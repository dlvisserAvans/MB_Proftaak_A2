package com.example.deschatkamervankoningavanius.Fragments;

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
import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Fragments.Quests.MultipleChoiceFragment;
import com.example.deschatkamervankoningavanius.Fragments.Quests.OpenQuestionFragment;
import com.example.deschatkamervankoningavanius.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    VPAdapter vpAdapter;
    List<Quest> questList;
    ProgressBar progressBar;
    int progress = 0;
    int progress_step;
    TextView tvProgress;
    int correctAnswers = 0;


    private Fragment currentFragment = new OpenQuestionFragment();
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        questList = new ArrayList<>();
        questList.add(new OpenQuestionQuest(R.drawable.brochure,"Test1","", "solution"));
        questList.add(new MultipleChoiceQuest(R.drawable.sticker,"Test2","", "A"));
        questList.add(new OpenQuestionQuest(R.drawable.poster,"Test3","", "solution"));
        questList.add(new MultipleChoiceQuest(R.drawable.namecard,"Test4","", "D"));

        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout_quest,fragment);
        fragmentTransaction.commit();

        progress_step = 100/questList.size();
        progressBar = rootView.findViewById(R.id.progress_bar);
        tvProgress = rootView.findViewById(R.id.tvProgress);
        tvProgress.setText(progress + "%");
        progressBar.setProgress(progress);

        vpAdapter = new VPAdapter(questList,getActivity());
        viewPager.setAdapter(vpAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(final int position) {
//                questList.get(position).setFinished(true);
                System.out.println(questList.get(position).isFinished());
                       switch (questList.get(position).getQuestionType()){
                           case OPENQUESTION:
                               currentFragment = new OpenQuestionFragment();
                               break;
                           case MULTIPLECHOICE:
                               currentFragment = new MultipleChoiceFragment();
                               Intent intent = new Intent();
                               intent.putExtra("solution", questList.get(position).getSolution());
                               break;
                       }
//                        setProgressBar();
                        getChildFragmentManager().beginTransaction().replace(R.id.fragment_layout_quest, currentFragment).commit();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        return rootView;
    }

    public void setProgressBar(){
        correctAnswers = 0;
        for (Quest quest : questList){
            if (quest.isFinished() == true){
                correctAnswers++;
            } else {
                correctAnswers += 0;
            }
        }
        progress = correctAnswers * progress_step;
        progressBar.setProgress(progress);
        tvProgress.setText(progress + "%");
    }
}
