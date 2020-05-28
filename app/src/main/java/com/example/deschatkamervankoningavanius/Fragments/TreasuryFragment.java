package com.example.deschatkamervankoningavanius.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.Data.Quest;
import com.example.deschatkamervankoningavanius.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TreasuryFragment extends Fragment {
    final String TAG = TreasuryFragment.class.getName();

    private MqttAndroidClient client;
    private String topic = "group/A2/word";
    private String payload = "aaaa";
    private byte[] encodedPayload = new byte[0];
    private List<Quest> questions;
    private TextView textView;
    private Button buttonCheck;
    private List<String> collectedLetters;
    private String letters = "";
    private int clickamount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treasury, container,false);
        this.clickamount = 0;
        this.questions = new ArrayList<>();
        this.collectedLetters = new ArrayList();
        for (int i = 0; i < 6; i++){
            this.collectedLetters.add(".");
            letters += ". ";
        }

        this.textView = (TextView) view.findViewById(R.id.tvCollectedLetters);
        this.textView.setText(letters);


        String clientId = MqttClient.generateClientId();
        this.client = new MqttAndroidClient(getActivity().getApplicationContext(), "tcp://maxwell.bps-software.nl:1883", clientId);

        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("androidTI");
            options.setPassword("&FN+g$$Qhm7j".toCharArray());
            IMqttToken token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "connected!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "connection failed! " + exception.getMessage());
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        this.buttonCheck = (Button) view.findViewById(R.id.buttonVerify);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onButtonCheckClicked(v);
            }
        });

        return view;
    }

    public void onButtonCheckClicked(View view){
        //TODO: Integrate function when Checkmenu is finished
//        Intent intent = new Intent(getActivity(),SettingsMenuActivity.class);
//        startActivity(intent);
        Toast.makeText(getActivity(), "Check", Toast.LENGTH_SHORT).show();
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }

//        if (clickamount > 5){
//            clickamount = 0;
//        } else {
//            clickamount++;
//        }
//        changeLetterCollection("a", clickamount-1);
    }

    public void changeLetterCollection(String letter, int position){
        this.letters = "";
        this.collectedLetters.set(position, letter);
        for (String string : collectedLetters){
            this.letters += string + " ";
        }
        this.textView.setText(this.letters);
    }
}

