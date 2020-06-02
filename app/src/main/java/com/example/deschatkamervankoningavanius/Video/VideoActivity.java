package com.example.deschatkamervankoningavanius.Video;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.deschatkamervankoningavanius.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends AppCompatActivity implements YouTubePlayerListener {

    public static final String EXTRA_VIDEO_REF = "videoRef";

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        String ref = getIntent().getExtras().getString(EXTRA_VIDEO_REF);

        youTubePlayerView = findViewById(R.id.youtubePlayerView);
        youTubePlayerView.getYouTubePlayerWhenReady( e -> {
            e.addListener(this);
            e.cueVideo(ref, 1f);
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }

    @Override
    public void onApiChange(YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onCurrentSecond(YouTubePlayer youTubePlayer, float v) {

    }

    @Override
    public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError playerError) {

    }

    @Override
    public void onPlaybackQualityChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackQuality playbackQuality) {

    }

    @Override
    public void onPlaybackRateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlaybackRate playbackRate) {

    }

    @Override
    public void onReady(YouTubePlayer youTubePlayer) {

    }

    @Override
    public void onStateChange(YouTubePlayer youTubePlayer, PlayerConstants.PlayerState playerState) {
        if(playerState.equals(PlayerConstants.PlayerState.ENDED)){
            this.finish();
        }
    }

    @Override
    public void onVideoDuration(YouTubePlayer youTubePlayer, float v) {

    }

    @Override
    public void onVideoId(YouTubePlayer youTubePlayer, String s) {

    }

    @Override
    public void onVideoLoadedFraction(YouTubePlayer youTubePlayer, float v) {

    }
}