package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView textView;

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
//        textView = rootView.findViewById(R.id.tvQuestTitle);
//        textView.setText(questList.get(0).getTitle());

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Fragment fragment = currentFragment;
        fragmentTransaction.add(R.id.fragment_layout_quest,fragment);
        fragmentTransaction.commit();



        vpAdapter = new VPAdapter(questList,getActivity());
        viewPager.setAdapter(vpAdapter);

//        Integer[] colors_temp = {getResources().getColor(R.color.color1),getResources().getColor(R.color.color2)};

//        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position < (vpAdapter.getCount() -1) && position < (colors.length-1)){
//                    rootView.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));
////                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position+1]));
//                }
//                else {
////                    viewPager.setBackgroundColor(colors[colors.length-1]);
//                    rootView.setBackgroundColor(colors[colors.length-1]);
//                }

//                textView.setText(questList.get(position).getTitle());

            }

            @Override
            public void onPageSelected(final int position) {
//               navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//                   @Override
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                       switch (questList.get(position).getQuestionType()){
                           case OPENQUESTION:
                               currentFragment = new OpenQuestionFragment();
                               break;
                           case MULTIPLECHOICE:
                               currentFragment = new MultipleChoiceFragment();
                               break;
                       }

                        getChildFragmentManager().beginTransaction().replace(R.id.fragment_layout_quest, currentFragment).commit();
//                        return true;
//                    }
//                };
//                Toast.makeText(getActivity(), questList.get(position).getTitle(),
//                        Toast.LENGTH_SHORT).show();
//
//                textView.setText(questList.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }
}
