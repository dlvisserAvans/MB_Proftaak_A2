package com.example.deschatkamervankoningavanius.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deschatkamervankoningavanius.R;
import com.example.deschatkamervankoningavanius.Video.VideoActivity;
import com.example.deschatkamervankoningavanius.Video.YoutubeVideo;
import com.example.deschatkamervankoningavanius.Video.YoutubeVideoAdapter;

import java.util.ArrayList;
import java.util.List;

public class TreasuryFragment extends Fragment
implements AdapterView.OnItemClickListener, YoutubeVideoAdapter.OnItemClickListener {
    private static final String LOGTAG = TreasuryFragment.class.getName();


    private RecyclerView youtubeVideoRecyclerView;
    private List<YoutubeVideo> youtubeVideos;
    private YoutubeVideoAdapter youtubeVideoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_treasury,container,false);
        Button buttonCheck = view.findViewById(R.id.btn_treasury_check);

        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonCheckClicked(v);
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

    public void onButtonCheckClicked(View view){
        //TODO: Integrate function when Checkmenu is finished
//        Intent intent = new Intent(getActivity(),SettingsMenuActivity.class);
//        startActivity(intent);

        Toast.makeText(getActivity(), "Check",
                Toast.LENGTH_SHORT).show();
    }
}
