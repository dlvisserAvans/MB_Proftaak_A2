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
            new YoutubeVideo("s1WQTUg_2vo", "Bedankt video", false, R.drawable.koning2),
            new YoutubeVideo("bAyrhRPNYdE", "Repelstokje", false, R.drawable.koning3),
            new YoutubeVideo("8BFJ1gvfF8Q", "Langvlecht", false, R.drawable.koning4),
            new YoutubeVideo("ERCdML3tnCQ", "Langneus", false, R.drawable.koning5),
            new YoutubeVideo("rXt_FZczmvg", "De Zes Butlers", false, R.drawable.koning1),
            new YoutubeVideo("8qZCYw78BSM", "Groenkapje", false, R.drawable.koning2),
            new YoutubeVideo("q9274LaGP-0", "Draak", false, R.drawable.koning3),
            new YoutubeVideo("KMKyAj6wUB4", "Slaapzacht", false, R.drawable.koning4),

            new YoutubeVideo("bAyrhRPNYdE", "Rumpstick", false, R.drawable.koning3),
            new YoutubeVideo("8BFJ1gvfF8Q", "Long braid", false, R.drawable.koning4),
            new YoutubeVideo("ERCdML3tnCQ", "Long Nose", false, R.drawable.koning5),
            new YoutubeVideo("rXt_FZczmvg", "The Six Butlers", false, R.drawable.koning1),
            new YoutubeVideo("8qZCYw78BSM", "Little Green Riding Hood", false, R.drawable.koning2),
            new YoutubeVideo("q9274LaGP-0", "Dragon", false, R.drawable.koning3),
            new YoutubeVideo("KMKyAj6wUB4", "Sleep tight", false, R.drawable.koning4),

            new YoutubeVideo("bAyrhRPNYdE", "Rumpstick", false, R.drawable.koning3),
            new YoutubeVideo("8BFJ1gvfF8Q", "Langes Geflecht", false, R.drawable.koning4),
            new YoutubeVideo("ERCdML3tnCQ", "Lange Nase", false, R.drawable.koning5),
            new YoutubeVideo("rXt_FZczmvg", "Die sechs Butler", false, R.drawable.koning1),
            new YoutubeVideo("8qZCYw78BSM", "Grüne Kappe", false, R.drawable.koning2),
            new YoutubeVideo("q9274LaGP-0", "Drachen", false, R.drawable.koning3),
            new YoutubeVideo("KMKyAj6wUB4", "Schlaf gut", false, R.drawable.koning4)
    };
    public static YoutubeVideo[] getYoutubeVideos(){
        return youtubeVideos;
    }

    public static YoutubeVideo getYoutubeVideo(int position){
        return youtubeVideos[position];
    }

}
