package com.example.deschatkamervankoningavanius.Video;

public class YoutubeVideo {

    private String ref; //referentie naar de video
    private String videoTitle; //titel van de video
    private boolean available; //boolean om te kijken of de video beschikbaar is


    public YoutubeVideo(String ref, String videoTitle, boolean available ){
        this.ref = ref;
        this.videoTitle = videoTitle;
        this.available = available;
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

}
