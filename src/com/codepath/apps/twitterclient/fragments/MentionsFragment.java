package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.view.View;

public class MentionsFragment extends TweetsListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterApp.getRestClient().getMentions(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	
	public void onLoadMoreBtnClick(View v){
		TwitterApp.getRestClient().getNextMentionsTweets(Tweet.getMentionsPageNum(), new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				Tweet.increaseMentionsPageNum();
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	}
	
}
