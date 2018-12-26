package com.danny.subredditalbums;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON {

    private String json;
    private JSONObject page;

    public JSON(String s){
        json = s;
        try {
            page = new JSONObject(json);
        }
        catch (Exception e){
            Log.w("SubredditAlbums", "Invalid JSON" + e);
        }
    }

    public JSONArray getJSONArray(){

        try {
            JSONObject data = page.getJSONObject("data");
            return data.getJSONArray("children");
        }catch (Exception e){
            Log.w("SubredditAlbums", e.toString());
        }
        return null;
    }



}
