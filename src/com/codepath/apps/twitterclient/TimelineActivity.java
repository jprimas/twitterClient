package com.codepath.apps.twitterclient;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends Activity {
	private TweetsAdapter adapter;
	private ArrayList<Tweet> tweets;
	private ListView lvTweets;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		System.out.println("Started view");
		initTimeline();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) { 
        case R.id.tweetParams:
            startActivityForResult(new Intent(this, PostTweetActivity.class), 1);
            break;
        default:
            break;
        }
        return true;
    }
	
	private void initTimeline(){
		TwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Tweet.initPageNum();
				tweets = Tweet.fromJson(jsonTweets);
				lvTweets = (ListView)findViewById(R.id.lvtweets);
				adapter = new TweetsAdapter(getBaseContext(), tweets);
				lvTweets.setAdapter(adapter);
				System.out.println(tweets.toString());
			}
		});
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
            	System.out.println("Tweet should have been posted");
            	initTimeline();
            }
        }
    }
	
	public void onLoadMoreBtnClick(View v){
		System.out.print("Btn pressed    ");
		TwitterApp.getRestClient().getNextTweets(Tweet.getPageNum(), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Tweet.increasePageNum();
				System.out.println("Got Json: " + jsonTweets);
				adapter.addAll(Tweet.fromJson(jsonTweets));
				System.out.println("Added Tweets");
			}
		});
	}

}
