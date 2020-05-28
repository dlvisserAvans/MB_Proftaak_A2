package com.example.deschatkamervankoningavanius.Fragments;

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

import com.example.deschatkamervankoningavanius.R;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class TreasuryFragment extends Fragment {
    final String tag = "JANKEESBROEKHUIZEN";

    MqttAndroidClient client;
    byte[] encodedPayload = new byte[0];
    TextView textView;
    private String topic = "group/A2/state";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treasury,container,false);
        Button buttonCheck = view.findViewById(R.id.buttonVerify);
        textView = (TextView) view.findViewById(R.id.tvCollectedLetters);
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
                subscribe();
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

    private void subscribe(){
        int qos = 2;

        try {
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), "onSuccess()" , Toast.LENGTH_SHORT);
                    toast.show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    exception.printStackTrace();
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), "onFailure()" , Toast.LENGTH_SHORT);
                    toast.show();
                }
            });

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), "connectionLost()" , Toast.LENGTH_SHORT);
                    toast.show();
                    getActivity().finish();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), "messageArrived()" , Toast.LENGTH_SHORT);
                    toast.show();
                    System.out.println("Topic: " + topic + " Message: " + message);
                    textView.setText(new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {

                }
            });

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
