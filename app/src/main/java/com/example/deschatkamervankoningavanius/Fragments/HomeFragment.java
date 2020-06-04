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
import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.Difficulty;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.Adapters.VPAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {
    Intent difficultyIntent = new Intent();
    Difficulty difficulty;

    public HomeFragment(Intent intent, Object o) {
        this.difficultyIntent = intent;
        if (o.equals(Difficulty.Easy)){
            difficulty = (Difficulty) o;
        }
        System.out.println(o);
    }


    ViewPager viewPager;
    VPAdapter vpAdapter;
    List<Quest> questList;
    ArrayList<Character> password = new ArrayList<>();
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    TextView titleView;
    TextView descView;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        JSONParser jsonParser = new JSONParser(getContext());

        questList = new ArrayList<>();
        List<Quest> allQuestsList = jsonParser.JsonParse();

//        allQuestsList.add(new Quest(R.drawable.brochure,"Test1",""));
//        allQuestsList.add(new Quest(R.drawable.sticker,"Test2",""));
//        allQuestsList.add(new Quest(R.drawable.poster,"Test3",""));
//        allQuestsList.add(new Quest(R.drawable.namecard,"Test4",""));
//        allQuestsList.add(new Quest(R.drawable.brochure,"Test5",""));
//        allQuestsList.add(new Quest(R.drawable.sticker,"Test6",""));
//        allQuestsList.add(new Quest(R.drawable.poster,"Test7",""));
//        allQuestsList.add(new Quest(R.drawable.namecard,"Test8",""));

        Collections.shuffle(allQuestsList);
//        String difficulty = difficultyIntent.getExtras().get("Difficulty");    //TODO Use this line to get difficulty enum instead of string
        Difficulty difficulty;
        difficulty = (Difficulty) difficultyIntent.getExtras().get("Difficulty");

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

        for (int i = 0; (questList.size() < questAmount); i++){
            String newQuestTitle = allQuestsList.get(i).getTitle();
            boolean duplicate = false;
            
                for (Quest quest : questList){
                    if (quest.getTitle().equals(newQuestTitle)){
                        System.out.println("SAME TITLE");
                        duplicate = true;
                    }
                }
                if (!duplicate){
                    questList.add(allQuestsList.get(i));
                }
        }

//        for (int i = 0; questList.size() < questAmount; i++){
//            for (Quest quest : questList){
//                if (!allQuestsList.get(i).getTitle().equals(quest.getTitle())){
//                    questList.add(allQuestsList.get(i));
//                }
//            }
//        }

        String password = "password";   //TODO implement actual passwords
        for (int i = 0; i < password.length(); i++){
            this.password.add(password.charAt(i));
        }

        Collections.shuffle(this.password);

        List<String> videoList = new ArrayList<>();

        this.user = new User(this.questList, this.password, videoList);


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
                Toast.makeText(getActivity(), questList.get(position).getTitle(),
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
