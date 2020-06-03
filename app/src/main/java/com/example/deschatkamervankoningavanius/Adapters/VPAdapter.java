package com.example.deschatkamervankoningavanius.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.R;

import java.util.List;

public class VPAdapter extends PagerAdapter {

    private List<Quest> questList;
    private LayoutInflater layoutInflater;
    private Context context;

    public VPAdapter(List<Quest> questList, Context context) {
        this.questList = questList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return questList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.viewpager_item, container, false);

        ImageView imageView = null;
        TextView title = null;

        imageView = view.findViewById(R.id.vpImage);
//        title= view.findViewById(R.id.vpTitle);

        imageView.setImageResource(questList.get(position).getQuestImage());
//        title.setText(questList.get(position).getTitle());

        container.addView(view,0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
