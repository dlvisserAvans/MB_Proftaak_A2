package com.example.deschatkamervankoningavanius.Fragments;

import android.animation.ArgbEvaluator;
import android.content.Intent;
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

import com.example.deschatkamervankoningavanius.Data.JSONParser;
import com.example.deschatkamervankoningavanius.Adapters.VPAdapter;
import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Difficulty;
import com.example.deschatkamervankoningavanius.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    public HomeFragment(User user) {
        this.user = user;
    }


    ViewPager viewPager;
    VPAdapter vpAdapter;
    List<Quest> questList;
    List<Quest> allQuestList = new ArrayList<>();
    ArrayList<Character> password = new ArrayList<>();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView titleView;
    TextView descView;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        questList = new ArrayList<>();
//        ArrayList<Quest> allQuestsList = new ArrayList<>();     //TODO Load all quests into this List instead of questList

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
                Toast.makeText(getActivity(), user.getQuests().get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();

                titleView.setText(user.getQuests().get(position).getTitle());
                descView.setText(user.getQuests().get(position).getDesc());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return rootView;
    }
}
