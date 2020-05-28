package com.example.deschatkamervankoningavanius.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.deschatkamervankoningavanius.R;

public class ExpandableTextViewAdapter extends BaseExpandableListAdapter {

    private Context context;

    private String[] items = {"How to play", "What are the rules?"};
    private String[][] itemanwsers = {{"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque tristique libero et urna commodo, ac convallis magna dictum. Etiam faucibus consequat facilisis. Mauris tempus justo elit."},
    {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque tristique libero et urna commodo, ac convallis magna dictum. Etiam faucibus consequat facilisis. Mauris tempus justo elit."}};

    public ExpandableTextViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return items.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemanwsers[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return items[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return itemanwsers[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        String question = (String)getGroup(groupPosition);
        if (convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.helpmenu_item,null);
        }
        TextView questionFaq2=convertView.findViewById(R.id.tvHelpmenuItem);
        questionFaq2.setTypeface(null, Typeface.BOLD);
        questionFaq2.setText(question);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final String answerFaq = (String)getChild(groupPosition,childPosition);
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.helpmenu_item_anwser,null);
        }
        TextView answerFaq2 = convertView.findViewById(R.id.tvHelpmenuItemDescription);
        answerFaq2.setText(answerFaq);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
