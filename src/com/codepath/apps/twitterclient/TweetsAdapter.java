package com.codepath.apps.twitterclient;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.twitterclient.models.Tweet;
import com.codepath.apps.twitterclient.models.User;
import com.nostra13.universalimageloader.core.ImageLoader;

public class TweetsAdapter extends ArrayAdapter<Tweet>{
	
	private Context context;
	
	public TweetsAdapter(Context context, List<Tweet> tweets){
		super(context, 0, tweets);
		this.context = context;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		View view = convertView;
		if(view == null){
			LayoutInflater inflator = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.tweet_item, null);
		}
		Tweet tweet = getItem(position);
		
		ImageView imageView = (ImageView) view.findViewById(R.id.ivProfile);
		ImageLoader.getInstance().displayImage(tweet.getUser().getProfileImageUrl(), imageView);
		
		TextView nameView = (TextView) view.findViewById(R.id.tvName);
		String formattedName = "<b>" + tweet.getUser().getName() + "</b>" + "<small>" +"   "+ tweet.getUser().getScreenName() + "		"+ tweet.getTimestamp() +"</small>";
		nameView.setText(Html.fromHtml(formattedName));
		
		TextView bodyView = (TextView) view.findViewById(R.id.tvBody);
		bodyView.setText(Html.fromHtml(tweet.getBody()));
		
		final User u = tweet.getUser();
		imageView = (ImageView) view.findViewById(R.id.ivProfile);
		imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent i = new Intent(v.getContext(), ProfileActivity.class);
            	i.putExtra("name", u.getName());
            	i.putExtra("screenName", u.getScreenName());
            	i.putExtra("description", u.getTagline());
            	i.putExtra("followers", String.valueOf(u.getNumOfFollowers()));
            	i.putExtra("following", String.valueOf(u.getNumOfFollowing()));
            	i.putExtra("profileImageUrl", u.getProfileImageUrl());
            	i.putExtra("userId", u.getId());
            	context.startActivity(i);
            }
        });
		
		return view;
	}

}
