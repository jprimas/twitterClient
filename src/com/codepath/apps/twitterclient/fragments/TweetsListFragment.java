package com.codepath.apps.twitterclient.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.codepath.apps.twitterclient.R;
import com.codepath.apps.twitterclient.TweetsAdapter;
import com.codepath.apps.twitterclient.models.Tweet;

public class TweetsListFragment extends Fragment {
	private TweetsAdapter adapter;
	private ArrayList<Tweet> tweets;
	private ListView lvTweets;
	private Button btnViewMore;
	
	OnShowTimelineInterface mCallback;
	
	public interface OnShowTimelineInterface {
        public String getUserId();
    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, parent, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Tweet.initPageNum();
		tweets = new ArrayList<Tweet>();
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
		
		btnViewMore = (Button) getActivity().findViewById(R.id.btnViewMore);
		btnViewMore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	onLoadMoreBtnClick(v);
            }
        });
	}
	
	public TweetsAdapter getAdapter(){
		return adapter;
	}
	
	public void onLoadMoreBtnClick(View v){
		return;
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnShowTimelineInterface) activity;
        } catch (ClassCastException e) {
            System.out.println("Only attach if trying to get usertimeline");
        }
    }
	
}
