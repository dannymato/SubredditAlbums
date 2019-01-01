package com.danny.subredditalbums;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Subreddit sub = new Subreddit("prequelmemes");

        CardAdapter cardAdapter = new CardAdapter(sub, this);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
        recyclerView.setAdapter(cardAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        //final Button button = (Button)findViewById(R.id.button);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask downloadJSON = new DownloadJSON(sub,view.getContext()).execute(sub.getURL());

                toolbar.setTitle(sub.toString());

              /*  ImageView imageView = (ImageView)findViewById(R.id.imageView);

                try{
                    downloadJSON.get();
                }catch (Exception e){Log.w("SubredditAlbums", e.toString());}
                Glide.with(view).load(sub.getPosts()[4].getImgUrl()).into(imageView);
*/
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sub:


                return true;
            default:

                return super.onOptionsItemSelected(item);
        }

    }

    private void changeSubreddit(){



        Subreddit subreddit = new Subreddit("");

    }
}
