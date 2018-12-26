package com.danny.subredditalbums;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadJSON extends AsyncTask<String, String, String> {

    String finalJSONString = "";
    Subreddit sub;
    private Context mContext;

    ProgressDialog pr;

    boolean isRunning = true;

    public DownloadJSON(Subreddit sub, Context mContext){
        this.sub = sub;
        this.mContext = mContext;
    }

    protected void onPreExecute() {
        pr = new ProgressDialog(mContext);
        pr.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pr.setIndeterminate(true);
        pr.setCancelable(false);
        pr.setMessage("Loading Posts");
        pr.show();
        super.onPreExecute();
    }

    protected String doInBackground(String... url){

        isRunning = true;
        try {
            URL url1 = new URL(url[0]);
            Log.d("URL", url1.toString());
            HttpsURLConnection urlConnection = (HttpsURLConnection) url1.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return readStream(in, urlConnection);
        }catch (Exception e){
            Log.w("Subreddit Album", e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("Subreddit Albums", "Finished getting URL");
        UseJSON use = new UseJSON(sub, s, mContext);
        use.execute(sub);
        pr.dismiss();
    }

    private String readStream(InputStream inputStream, HttpURLConnection urlConnection) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = "";
        StringBuilder total = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                total.append(line);
                Log.d("Subreddit Albums", line);
            }

        }catch (Exception e){
            Log.d("SubredditAlbums",e.toString());
        }
        line = total.toString();
        urlConnection.disconnect();

        Log.d("Fuck",line.substring(line.length()-6));
        isRunning = false;
        return line;
    }


}
