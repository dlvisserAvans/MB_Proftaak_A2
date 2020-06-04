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

    public HomeFragment(User user) {
        this.user = user;
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

//        for (int i = 0; questList.size() < questAmount; i++){
//            for (Quest quest : questList){
//                if (!allQuestsList.get(i).getTitle().equals(quest.getTitle())){
//                    questList.add(allQuestsList.get(i));
//                }
//            }
//        }

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
