package com.example.deschatkamervankoningavanius.Fragments;

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

    private ViewPager viewPager;
    private VPAdapter vpAdapter;
    private List<Quest> questList;
    private ProgressBar progressBar;
    private int progress = 0;
    private int progress_step;
    private TextView tvProgress;
    private int correctAnswers = 0;
    private Bundle bundle;
    private Fragment currentFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        questList = new ArrayList<>();
        questList.add(new MultipleChoiceQuest(R.drawable.namecard, "Test0", "", "1", "0", "1", "2", "3", false));
        questList.add(new OpenQuestionQuest(R.drawable.brochure,"Test1","", "solution1", false));
        questList.add(new MultipleChoiceQuest(R.drawable.sticker,"Test2","", "A", "A", "B", "C", "D", false));
        questList.add(new OpenQuestionQuest(R.drawable.poster,"Test3","", "solution2", false));
        questList.add(new MultipleChoiceQuest(R.drawable.namecard,"Test4","", "F", "E", "F", "G", "H", false));

        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);

        this.bundle = new Bundle();
        this.bundle.putString("solution", questList.get(0).getSolution());
        bundle.putString("optionA", questList.get(0).getButtonOption("A"));
        bundle.putString("optionB", questList.get(0).getButtonOption("B"));
        bundle.putString("optionC", questList.get(0).getButtonOption("C"));
        bundle.putString("optionD", questList.get(0).getButtonOption("D"));
        this.currentFragment = new MultipleChoiceFragment();
        this.currentFragment.setArguments(this.bundle);

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
                System.out.println("Position: " + position);
                switch (questList.get(position).getQuestionType()) {
                    case OPENQUESTION:
                        bundle.putString("solution", questList.get(position).getSolution());
                        currentFragment = new OpenQuestionFragment();
                        currentFragment.setArguments(bundle);
                        break;
                    case MULTIPLECHOICE:
                        bundle.putString("solution", questList.get(position).getSolution());
                        bundle.putString("optionA", questList.get(position).getButtonOption("A"));
                        bundle.putString("optionB", questList.get(position).getButtonOption("B"));
                        bundle.putString("optionC", questList.get(position).getButtonOption("C"));
                        bundle.putString("optionD", questList.get(position).getButtonOption("D"));
                        currentFragment = new MultipleChoiceFragment();
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
