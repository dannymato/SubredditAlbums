package com.danny.subredditalbums;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.PostViewHolder> {

    private Subreddit sub;
    private Context mContext;

    public CardAdapter(Subreddit sub, Context mContext){
        this.sub = sub;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        Log.d("SubredditAlbums","onCreateView");
        return new PostViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        Post post = sub.getPosts().get(position);

        holder.titleView.setText(post.getTitle());
        holder.userView.setText(post.getUser());
        holder.voteView.setText(post.getVotes());

        Log.d("SubredditAlbums",post.getTitle());

        final ProgressBar progressBar = holder.bar;
        /*CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(5f);
        circularProgressDrawable.setCenterRadius(30f);
        circularProgressDrawable.start();*/


        GlideApp.with(mContext)
                .load(post.getImgUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        Log.d("SubredditAlbums","Resource ready");
                        return false;
                    }
                })
                .dontAnimate()
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return sub.getPosts().size();
    }

    public void updateList(Subreddit posts){
        sub = posts;
        notifyDataSetChanged();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {

        protected TextView titleView;
        protected TextView voteView;
        protected TextView userView;
        protected ImageView imageView;
        protected ProgressBar bar;


        public PostViewHolder(View itemView) {
            super(itemView);

            titleView = (TextView)itemView.findViewById(R.id.titleView);
            voteView = (TextView)itemView.findViewById(R.id.voteView);
            userView = (TextView)itemView.findViewById(R.id.userView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            bar = (ProgressBar)itemView.findViewById(R.id.progress_circular);


        }
    }
}
