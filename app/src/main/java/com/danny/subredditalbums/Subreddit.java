package com.danny.subredditalbums;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Subreddit {

    private ArrayList<Post> posts = new ArrayList<Post>();
    private String subreddit;
    private JSON json;
    private String raw_json;
   // DownloadJSON task = new DownloadJSON();

    public Subreddit(String sub){
        subreddit = sub;

        posts.add(new Post());
        posts.get(0).setTitle("Test title");
        posts.get(0).setVotes("1200");
        posts.get(0).setUser("macdows");
        posts.get(0).setImgUrl("https://i.imgur.com/L3rP4fH.jpg");
        
	for(int i = 0; i < posts.size(); i++){
            Log.d("Subreddit Album", "Post #" + i + " User: " + posts.get(i).getUser() + " Title: " + posts.get(i).getTitle() + " IMG URL: " + posts.get(i).getImgUrl());
        }
    }

    public ArrayList<Post> getPosts(){return posts;}

    public String getURL(){return "https://api.reddit.com/r/" + subreddit + "/hot";}

    public String toString(){return "/r/" + subreddit;}

}
