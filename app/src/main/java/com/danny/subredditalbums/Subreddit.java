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
        //DownloadJSON task = new DownloadJSON();
        //AsyncTask<String,String,String> obj = task.execute(url);
  //      int i = 0;
    //    while (task.isRunning){
    //        i++;
     //   }

//        String s = task.finalJSONString;



//        json = new JSON(s);
 //       Log.w("Subreddit Albums", task.finalJSONString + "JSON");
   //     populateArray();
       // printArray();
    }

   /* public void startEvents(){
        String url = "https://api.reddit.com/r/" + subreddit;

        AsyncTask<String,String,String> obj = task.execute(url);

     //   json = new JSON(s);
    }*/

    /*public void populateArray(){
        raw_json = task.finalJSONString;
        json = new JSON(raw_json);
        JSONArray posts = json.getJSONArray();

        int j = 0;

        for(int i = 0; i < this.posts.length; i++){
            try {
                JSONObject data = posts.getJSONObject(i).getJSONObject("data");
               // if (!data.getString("thumbnail").contains("self")) {
                    String user = data.getString("author");
                    String title = data.getString("title");
                    String imgURL = data.getString("url");
                    String votes = data.getString("score");
                    String nsfw = data.getString("over_18");
                    String spoiler = data.getString("spoiler");
                    String time = data.getString("created");


                    Log.d("Subreddit Votes", votes);
                    this.posts[i] = new Post();
                    this.posts[i].setUser(user);
                    this.posts[i].setTime_created(time);
                    this.posts[i].setTitle(title);
                    this.posts[i].setImgUrl(imgURL);
                    this.posts[i].setVotes(votes);
                    this.posts[i].setNsfw(nsfw);
                    this.posts[i].setSpoiler(spoiler);
               // }
                //else{
                 //   i--;
                //}
            }catch (Exception e){
                Log.w("SubredditAlbums",e.toString());
            }
         //   j++;
        }

    }*/

    public void printArray(){
        for(int i = 0; i < posts.size(); i++){
            Log.d("Subreddit Album", "Post #" + i + " User: " + posts.get(i).getUser() + " Title: " + posts.get(i).getTitle() + " IMG URL: " + posts.get(i).getImgUrl());
        }
    }

    public ArrayList<Post> getPosts(){return posts;}

    public String getURL(){return "https://api.reddit.com/r/" + subreddit + "/hot";}

    public String toString(){return "/r/" + subreddit;}

}
