package com.example.deschatkamervankoningavanius.Data;

import android.content.Context;
import android.content.res.Resources;

import com.example.deschatkamervankoningavanius.Difficulty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONParser {
    ArrayList<Quest> questList = new ArrayList<>();
    String JSONString;
    JSONObject reader;
    Context appContext;

    public JSONParser(Context context) {
        this.appContext = context;
    }

    /**
     * Reads the quests from a JSON file
     * @return returns a list of all quests read from the JSON files
     */
    public List<Quest> ParseQuests() {
        try {
            if (appContext != null){
                InputStream is = appContext.getAssets().open("quests.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                this.JSONString = new String(buffer, StandardCharsets.UTF_8);
            }else {
                System.out.println("CONTEXT = NULL!");
            }

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

                //Get IDs for image and text for quests.
                int imageID = resources.getIdentifier(quest.getString("image"), "drawable",appContext.getPackageName());
                int titleID = resources.getIdentifier(quest.getString("title"), "string", appContext.getPackageName());
                int descriptionID = resources.getIdentifier(quest.getString("description"), "string", appContext.getPackageName());
                int solutionID = resources.getIdentifier(quest.getString("solution"), "string", appContext.getPackageName());

                if(quest.getString("type").equals("open")){

                    this.questList.add(new OpenQuestionQuest(imageID, titleID, descriptionID, resources.getString(solutionID)));
                }else if (quest.get("type").equals("multipleChoice")){
                    int optionAID = resources.getIdentifier(quest.getString("optionA"), "string", appContext.getPackageName());
                    int optionBID= resources.getIdentifier(quest.getString("optionB"), "string", appContext.getPackageName());
                    int optionCID = resources.getIdentifier(quest.getString("optionC"), "string", appContext.getPackageName());
                    int optionDID = resources.getIdentifier(quest.getString("optionD"), "string", appContext.getPackageName());

                    this.questList.add(new MultipleChoiceQuest(imageID, titleID, descriptionID, resources.getString(solutionID), resources.getString(optionAID), resources.getString(optionBID), resources.getString(optionCID), resources.getString(optionDID)));
                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this.questList;
    }

    public String parsePassword(Difficulty difficulty){
        String password =  "";
        List<String> passwords = new ArrayList<>();

        switch (difficulty){
            //get all easy passwords from JSon
            case Easy:
                try {
                    JSONArray easyPasswords = reader.getJSONArray("passwordsEasy");
                    for (int i = 0; i < easyPasswords.length(); i++){
                        JSONObject easyPassword = easyPasswords.getJSONObject(i);
                        passwords.add(easyPassword.getString("password"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            //get all medium passwords from JSon
            case Medium:
                try {
                    JSONArray mediumPasswords = reader.getJSONArray("passwordsMedium");
                    for (int i = 0; i < mediumPasswords.length(); i++){
                        JSONObject mediumPassword = mediumPasswords.getJSONObject(i);
                        passwords.add(mediumPassword.getString("password"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            //get all hard passwords from JSon
            case Hard:
                try {
                    JSONArray hardPasswords = reader.getJSONArray("passwordsHard");
                    for (int i = 0; i < hardPasswords.length(); i++){
                        JSONObject hardPassword = hardPasswords.getJSONObject(i);
                        passwords.add(hardPassword.getString("password"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        Collections.shuffle(passwords); //shuffle the passwords
        password = passwords.get(0);


        return password;
    }

}
