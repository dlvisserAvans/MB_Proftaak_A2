package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.Adapters.VPAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    Intent difficultyIntent = new Intent();

    public HomeFragment(Intent intent) {
        this.difficultyIntent = intent;
    }

    ViewPager viewPager;
    VPAdapter vpAdapter;
    List<Quest> questList;
    List<Quest> allQuestList = new ArrayList<>();
    ArrayList<Character> password = new ArrayList<>();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        questList = new ArrayList<>();
//        ArrayList<Quest> allQuestsList = new ArrayList<>();     //TODO Load all quests into this List instead of questList

        if (allQuestList.size()==0){
            allQuestList.add(new Quest(R.drawable.brochure,"Test1",""));
            allQuestList.add(new Quest(R.drawable.sticker,"Test2",""));
            allQuestList.add(new Quest(R.drawable.poster,"Test3",""));
            allQuestList.add(new Quest(R.drawable.namecard,"Test4",""));
            allQuestList.add(new Quest(R.drawable.brochure,"Test5",""));
            allQuestList.add(new Quest(R.drawable.sticker,"Test6",""));
            allQuestList.add(new Quest(R.drawable.poster,"Test7",""));
            allQuestList.add(new Quest(R.drawable.namecard,"Test8",""));
            Collections.shuffle(allQuestList);
        }


//        String difficulty = difficultyIntent.getExtras().get("Difficulty");    //TODO Use this line to get difficulty enum instead of string
        String difficulty = "easy"; //PLACEHOLDER, REMOVE LATER
        int questAmount = 1;
        //TODO change quest amounts?
        switch (difficulty){
            case "easy":
                questAmount = 4;
                break;
            case "medium":
                questAmount = 6;
                break;
            case "hard":
                questAmount = 8;
                break;
        }

        for (int i = 0; i < questAmount; i++){
            questList.add(allQuestList.get(i));
        }

        String password = "password";   //TODO implement actual passwords
        for (int i = 0; i < password.length(); i++){
            this.password.add(password.charAt(i));
        }

        Collections.shuffle(this.password);

        List<String> videoList = new ArrayList<>();

        User user = new User(this.questList, this.password, videoList);


        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager = rootView.findViewById(R.id.vpQuest);
        textView = rootView.findViewById(R.id.tvQuestTitle);
        textView.setText(user.getQuests().get(0).getTitle());



        vpAdapter = new VPAdapter(user.getQuests(),getActivity());
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
            public void onPageSelected(int position) {
                Toast.makeText(getActivity(), questList.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();

                textView.setText(questList.get(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }
}
