package com.example.deschatkamervankoningavanius.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.deschatkamervankoningavanius.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class TreasuryFragment extends Fragment {
    final String tag = "JANKEESBROEKHUIZEN";

    MqttAndroidClient client;
    byte[] encodedPayload = new byte[0];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treasury,container,false);
        Button buttonCheck = view.findViewById(R.id.buttonVerify);
        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(getActivity().getApplicationContext(),"tcp://maxwell.bps-software.nl:1883", clientId);

        try {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("androidTI");
            options.setPassword("&FN+g$$Qhm7j".toCharArray());
            IMqttToken token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(tag,"Yes, verbonden!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(tag,"Fout bij verbinden!" + exception.getMessage());
                }
            });
        }
        catch(MqttException e){
            e.printStackTrace();
        }


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

        String payload = "bbbb";
        String topic = "group/A2/word";
        Toast.makeText(getActivity(), "Check", Toast.LENGTH_SHORT).show();
        System.out.println("onButtonCheckClicked()");
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }
}
