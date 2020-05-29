package com.example.deschatkamervankoningavanius.Video;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deschatkamervankoningavanius.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import java.util.List;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.YoutubeVideoViewHolder> {
    private static final String LOGTAG = YoutubeVideoAdapter.class.getName();

    private LayoutInflater inflater;
    private List<YoutubeVideo> youtubeVideos;
    private OnItemClickListener clickListener;

    public class YoutubeVideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private YouTubePlayerView youtubeVideoView;
        private TextView videoTextView;

        public YoutubeVideoViewHolder(View itemView, YoutubeVideoAdapter adapter){
            super(itemView);
            youtubeVideoView = itemView.findViewById(R.id.youtubePlayerView);
            videoTextView = itemView.findViewById(R.id.videoTitle);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            Log.i(LOGTAG, "Item " + clickedPosition + " clicked");
            clickListener.onItemClick(clickedPosition);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    public YoutubeVideoAdapter(Context context, List<YoutubeVideo> videos, OnItemClickListener listener){
        inflater = LayoutInflater.from(context);
        youtubeVideos = videos;
        clickListener = listener;
    }

    @NonNull
    @Override
    public YoutubeVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        View itemView = inflater.inflate(R.layout.video_item, parent, false);
        YoutubeVideoViewHolder viewHolder = new YoutubeVideoViewHolder(itemView, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeVideoViewHolder holder, int position) {
        Log.d(LOGTAG, "onBindViewHolder() called for position " + position);
        YoutubeVideo youtubeVideo = youtubeVideos.get(position);
        Log.d(LOGTAG, "Video = " + youtubeVideo.getRef());
        holder.videoTextView.setText(youtubeVideos.get(position).getVideoTitle());
        holder.youtubeVideoView.getYouTubePlayerWhenReady(youTubePlayer -> {
            youTubePlayer.cueVideo("25ZuKkbHdqM", 1f);
        });

        //TODO
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, "getItemCount() called");
        return youtubeVideos.size();
    }

    public void setFullScreen(YoutubeVideoViewHolder holder){
        holder.youtubeVideoView.enterFullScreen();
    }

}
