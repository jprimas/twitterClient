package com.codepath.apps.twitterclient;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterclient.fragments.TweetsListFragment.OnShowTimelineInterface;
import com.codepath.apps.twitterclient.models.User;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ProfileActivity extends FragmentActivity implements OnShowTimelineInterface{
	private String name;
	private String screenName;
	private String tagline;
	private String followers;
	private String following;
	private String profileImageUrl;
	private String userId;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Intent i = getIntent();
		if(i.getStringExtra("name") == null || i.getStringExtra("name").equals("null")){
			System.out.println("loading");
			userId = i.getStringExtra("userId");
			loadProfileInfo();
		} else {
			name = i.getStringExtra("name");
			screenName = i.getStringExtra("screenName");
			tagline = i.getStringExtra("tagline");
			followers = i.getStringExtra("followers");
			following = i.getStringExtra("following");
			profileImageUrl = i.getStringExtra("profileImageUrl");
			userId = i.getStringExtra("userId");
			getActionBar().setTitle("@" + screenName);
			populateProfileHeader();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}
	
	private synchronized void loadProfileInfo(){
		TwitterApp.getRestClient().getMyInfo(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject json) {
				User u = User.fromJson(json);
				getActionBar().setTitle("@" + u.getScreenName());
				name = u.getName();
				screenName = u.getScreenName();
				tagline = u.getTagline();
				followers = String.valueOf(u.getNumOfFollowers());
				following = String.valueOf(u.getNumOfFollowing());
				profileImageUrl = u.getProfileImageUrl();
				System.out.println("finished loading");
				populateProfileHeader();
			}
		});
	}

	protected void populateProfileHeader() {
		TextView tvName = (TextView) findViewById(R.id.tvName);
		TextView tvTagline  = (TextView) findViewById(R.id.tvTagline);
		TextView tvFollowers = (TextView) findViewById(R.id.tvFollowers);
		TextView tvFollowing = (TextView) findViewById(R.id.tvFollowing);
		ImageView ivProfileView = (ImageView) findViewById(R.id.ivProfileImage);
		tvName.setText(name);
		tvTagline.setText(tagline);
		tvFollowers.setText(followers + " Followers");
		tvFollowing.setText(following + " Following");
		ImageLoader.getInstance().displayImage(profileImageUrl, ivProfileView);
		
		
	}

	@Override
	public String getUserId() {
		return userId;
	}
	
	

}
