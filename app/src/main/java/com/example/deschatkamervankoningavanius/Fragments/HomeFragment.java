package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.QuestionType;
import com.example.deschatkamervankoningavanius.Fragments.Quests.MultipleChoiceFragment;
import com.example.deschatkamervankoningavanius.Fragments.Quests.OpenQuestionFragment;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.Adapters.VPAdapter;
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


    private Fragment currentFragment = new OpenQuestionFragment();
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        questList = new ArrayList<>();
        questList.add(new Quest(R.drawable.brochure,"Test1","", QuestionType.OPENQUESTION));
        questList.add(new Quest(R.drawable.sticker,"Test2","",QuestionType.MULTIPLECHOICE));
        questList.add(new Quest(R.drawable.poster,"Test3","",QuestionType.OPENQUESTION));
        questList.add(new Quest(R.drawable.namecard,"Test4","",QuestionType.MULTIPLECHOICE));

        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout_quest,fragment);
        fragmentTransaction.commit();

        progress_step = 100/questList.size();
        progress = progress_step * 2;
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


                       switch (questList.get(position).getQuestionType()){
                           case OPENQUESTION:
                               currentFragment = new OpenQuestionFragment();
                               break;
                           case MULTIPLECHOICE:
                               currentFragment = new MultipleChoiceFragment();
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
}
