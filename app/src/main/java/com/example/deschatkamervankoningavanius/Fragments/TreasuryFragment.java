package com.example.deschatkamervankoningavanius.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deschatkamervankoningavanius.Data.User;
import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.FairytaleAvanius;
import com.example.deschatkamervankoningavanius.Video.VideoActivity;
import com.example.deschatkamervankoningavanius.Video.YoutubeVideo;
import com.example.deschatkamervankoningavanius.Video.YoutubeVideoAdapter;
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
import java.util.ArrayList;
import java.util.List;

import static com.example.deschatkamervankoningavanius.Video.VideoActivity.EXTRA_VIDEO_REF;

public class TreasuryFragment extends Fragment implements AdapterView.OnItemClickListener, YoutubeVideoAdapter.OnItemClickListener {
    final String tag = "JANKEESBROEKHUIZEN";
    private static final String LOGTAG = TreasuryFragment.class.getName();
    private RecyclerView youtubeVideoRecyclerView;
    private List<YoutubeVideo> youtubeVideos;
    private YoutubeVideoAdapter youtubeVideoAdapter;
    MqttAndroidClient client;
    byte[] encodedPayload = new byte[0];
    TextView textView;
    private String topic = "group/A2/state";
    String response = "";
    User user;

    public TreasuryFragment(User user){
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_treasury,container,false);
        textView = view.findViewById(R.id.tvCollectedLetters);
        textView.setText(HomeFragment.setTextView());

        Button buttonCheck = view.findViewById(R.id.btn_treasury_check);
        ImageView imageView = view.findViewById(R.id.treasury_book);
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonCheckClicked(v);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                onIconBookClicked(v);
            }
        });
        youtubeVideos = new ArrayList<>(); //de videos die in het recylerview komen
        //Als de youtubeVideo beschikbaar is voeg hem toe aan de list voor het recyclerview
        for(YoutubeVideo youtubeVideo : YoutubeVideo.getYoutubeVideos()){
            if(youtubeVideo.isAvailable()){
                youtubeVideos.add(youtubeVideo);
            }
        }
        youtubeVideoRecyclerView = view.findViewById(R.id.rvTreasury);
        youtubeVideoAdapter = new YoutubeVideoAdapter(view.getContext(), youtubeVideos, this);
        youtubeVideoRecyclerView.setAdapter(youtubeVideoAdapter);
        youtubeVideoRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(LOGTAG, "onItemClicked called with position = " + position);
        navigateToVideoActivity(position);
    }
    @Override
    public void onItemClick(int clickedPosition) {
        Log.d(LOGTAG, "onItemClick() called with position = " + clickedPosition);
        navigateToVideoActivity(clickedPosition);
    }
    private void navigateToVideoActivity(int position){
        Intent intent = new Intent(this.getActivity(), VideoActivity.class);
        intent.putExtra(VideoActivity.EXTRA_VIDEO_REF, youtubeVideos.get(position).getRef());
        startActivity(intent);
    }
    public void onIconBookClicked(View v) {
        Intent intent = new Intent(getContext(), FairytaleAvanius.class);
        startActivity(intent);
    }
    public void onButtonCheckClicked(View view){
        //TODO: Integrate function when Checkmenu is finished
        String payload = user.getPassword();
        System.out.println(user.getPassword());
        String topic = "group/A2/word";
//        Toast.makeText(getActivity(), "Check", Toast.LENGTH_SHORT).show();
        System.out.println("onButtonCheckClicked()");
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
            System.out.println("Message: " + message);
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
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), "Send: " + HomeFragment.setTextView(), Toast.LENGTH_SHORT);
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

                    System.out.println("Topic: " + topic + " Message: " + message);
                    response = new String(message.getPayload());
                    System.out.println("Response: " + response);
                    Toast toast = Toast. makeText(getActivity().getApplicationContext(), response , Toast.LENGTH_SHORT);
                    toast.show();
                    if (response.equals("true")){

                        Intent videoIntent = new Intent(getActivity(), VideoActivity.class);
                        YoutubeVideo.getYoutubeVideo(1).setAvailable(true);
                        videoIntent.putExtra(EXTRA_VIDEO_REF, "s1WQTUg_2vo");
                        startActivity(videoIntent);
                        textView.setText(user.getPassword());
                    }

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