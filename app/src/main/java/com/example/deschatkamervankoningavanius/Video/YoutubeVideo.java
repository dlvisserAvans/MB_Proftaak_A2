package com.example.deschatkamervankoningavanius.Video;

import android.content.Context;
import android.media.Image;

import com.example.deschatkamervankoningavanius.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;

public class YoutubeVideo {

    private String ref; //referentie naar de video
    private String videoTitle; //titel van de video
    private boolean available; //boolean om te kijken of de video beschikbaar is
    private int videoImageResourceId; //resource id van de koning afbeeldingen


    public YoutubeVideo(String ref, String videoTitle, boolean available, int videoImageResourceId){
        this.ref = ref;
        this.videoTitle = videoTitle;
        this.available = available;
        this.videoImageResourceId = videoImageResourceId;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getVideoImageResourceId() {
        return videoImageResourceId;
    }

    public void setVideoImageResourceId(int videoImageResourceId) {
        this.videoImageResourceId = videoImageResourceId;
    }

    private static final YoutubeVideo[] youtubeVideos = {
            new YoutubeVideo("W0wQ8WkFikg", "Intro video", true, R.drawable.koning1),
            new YoutubeVideo("s1WQTUg_2vo", "Bedankt video", true, R.drawable.koning2),
            new YoutubeVideo("bAyrhRPNYdE", "Repelsteeltje", true, R.drawable.koning3),
            new YoutubeVideo("8BFJ1gvfF8Q", "Raponsje", true, R.drawable.koning4),
            new YoutubeVideo("ERCdML3tnCQ", "Langneus", true, R.drawable.koning5),
            new YoutubeVideo("rXt_FZczmvg", "LangeJan", true, R.drawable.koning1),
            new YoutubeVideo("8qZCYw78BSM", "Groenkapje", true, R.drawable.koning2),
            new YoutubeVideo("q9274LaGP-0", "Draak", true, R.drawable.koning3),
            new YoutubeVideo("KMKyAj6wUB4", "Doornroosje", true, R.drawable.koning4)
    };
    public static YoutubeVideo[] getYoutubeVideos(){
        return youtubeVideos;
    }

    public static YoutubeVideo getYoutubeVideo(int position){
        return youtubeVideos[position];
    }

}
