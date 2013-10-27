package com.codepath.apps.twitterclient.fragments;

import org.json.JSONArray;

import android.os.Bundle;

import com.codepath.apps.twitterclient.TwitterApp;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class UserTimelineFragment extends TweetsListFragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		String id = null;
		id = mCallback.getUserId();
		System.out.println("id: "+id);
		TwitterApp.getRestClient().getUserTimeline(id, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				getAdapter().addAll(Tweet.fromJson(jsonTweets));
			}
		});
	};
	
	
	
}
