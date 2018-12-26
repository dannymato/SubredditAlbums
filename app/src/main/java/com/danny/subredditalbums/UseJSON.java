package com.danny.subredditalbums;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

public class UseJSON extends AsyncTask<Subreddit,String,String> {

    private String raw_json;
    private Subreddit sub;
    private Context mContext;

    public UseJSON(Subreddit sub, String raw_json, Context mContext){
        this.sub = sub;
        this.raw_json = raw_json;
        this.mContext = mContext;
    }

    @Override
    protected String doInBackground(Subreddit... subreddits) {

        JSON json = new JSON(raw_json);
        JSONArray posts = json.getJSONArray();

        int j = 0;

        for(int i = 0; i < posts.length(); i++){
            try {
                JSONObject data = posts.getJSONObject(i).getJSONObject("data");

                if(!data.getString("thumbnail").contains("self")){

                    String user = data.getString("author");
                    String title = data.getString("title");
                    String imgURL = data.getString("url");
                    String votes = data.getString("score");
                    String nsfw = data.getString("over_18");
                    String spoiler = data.getString("spoiler");
                    String time = data.getString("created");


                    Log.d("Subreddit Votes", votes);

                    sub.getPosts().add(new Post());

                    sub.getPosts().get(j).setUser(user);
                    sub.getPosts().get(j).setTitle(title);
                    sub.getPosts().get(j).setImgUrl(imgURL);
                    sub.getPosts().get(j).setVotes(votes);
                    sub.getPosts().get(j).setNsfw(nsfw);
                    sub.getPosts().get(j).setSpoiler(spoiler);
                    sub.getPosts().get(j).setTime_created(time);
                    j++;
                }
                else{}
            }catch (Exception e){
                Log.w("Subreddit Albums", "Invalid JSONObjects " + e.toString());

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        sub.printArray();

        CardAdapter cardAdapter = new CardAdapter(sub,mContext);

        cardAdapter.updateList(sub);

        RecyclerView recyclerView = (RecyclerView)((Activity)mContext).findViewById(R.id.cardList);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();

        //recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        Log.d("SubredditAlbums", "After notify");

        /*

        TextView title = (TextView)((Activity)mContext).findViewById(R.id.titleView);
        title.setText(sub.getPosts()[0].getTitle());

        TextView user = (TextView)((Activity)mContext).findViewById(R.id.userView);
        user.setText("/u/" + sub.getPosts()[0].getUser());

        TextView votes = (TextView)((Activity)mContext).findViewById(R.id.voteView);
        votes.setText(sub.getPosts()[0].getVotes() + " points");

        ImageView imageView = (ImageView)((Activity)mContext).findViewById(R.id.imageView);
        Glide.with(mContext).load(sub.getPosts()[0].getImgUrl()).into(imageView);
        */
    }
}
