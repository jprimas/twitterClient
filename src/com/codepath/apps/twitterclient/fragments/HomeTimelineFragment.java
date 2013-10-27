package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class HomeTimelineFragment extends TweetsListFragment {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterApp.getRestClient().getHomeTimeline(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	
	public void onLoadMoreBtnClick(View v){
		TwitterApp.getRestClient().getNextTweets(Tweet.getPageNum(), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Tweet.increasePageNum();
				System.out.println("Got Json: " + jsonTweets);
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
				System.out.println("Added Tweets");
			}
		});
	}

	
	
	
	
}
