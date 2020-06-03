package com.example.deschatkamervankoningavanius.Data;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import org.json.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {
    ArrayList<Quest> questList = new ArrayList<>();
    String JSONString;
    JSONObject reader;
    Context appContext;

    public JSONParser(Context context) {
        this.appContext = context;
    }

    public List<Quest> JsonParse() {
        try {
            InputStream is = appContext.getAssets().open("quests.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            this.JSONString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (this.JSONString != null) {
            try {
                reader = new JSONObject(JSONString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        try {
            JSONArray quests = reader.getJSONArray("quests");

            Resources resources = appContext.getResources();
            for (int i = 0; i < quests.length(); i++){
                JSONObject quest = quests.getJSONObject(i);
                String imageName = quest.getString("image");
                int imageID = resources.getIdentifier(imageName, "drawable",appContext.getPackageName());
                System.out.println("ROUND: " + i + " ,imageID: " + imageID + " ,imageName: " + imageName);

                questList.add(new Quest(resources.getIdentifier(imageName, "drawable", appContext.getPackageName()), quest.getString("title"), quest.getString("description")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(questList);
        return this.questList;
    }

}
