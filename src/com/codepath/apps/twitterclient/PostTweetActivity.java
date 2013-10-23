package com.codepath.apps.twitterclient;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

public class PostTweetActivity extends Activity {
	private EditText postText;
	private TextView charCountText;
	private Button postBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_tweet);
		postText = (EditText) findViewById(R.id.postText);
		charCountText = (TextView) findViewById(R.id.charCountText);
		postBtn = (Button) findViewById(R.id.postBtn);
		charCountText.setText("Number of Chars left: 140");
		setupViewListner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_tweet, menu);
		return true;
	}
	
	private void setupViewListner(){
		postText.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				charCountText.setText("Number of Chars left: " + (140 - postText.length() ));
				if(postText.length() >= 140){
					postText.setText(postText.getText().toString().substring(0, 139));
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
            
        }); 
    }
	
	public void onPostPress(View view){
		String tweet = postText.getText().toString();
		TwitterApp.getRestClient().postTweet(tweet, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONObject result) {
				Intent i = new Intent(getApplicationContext(), TimelineActivity.class);
				setResult(RESULT_OK, i);
				finish();
			}
		});
	}

}
