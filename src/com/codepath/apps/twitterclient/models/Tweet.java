package com.codepath.apps.twitterclient.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Tweet extends BaseModel {
    private User user;
    private static int page = 2;
    private static int mentionsPage = 2;
    
    public static void increasePageNum(){
    	page++;
    }
    
    public static void initPageNum(){
    	page = 2;
    }
    
    public static String getPageNum(){
    	return String.valueOf(page);
    }
    
    public static void increaseMentionsPageNum(){
    	mentionsPage++;
    }
    
    public static void initMentionsPageNum(){
    	mentionsPage = 2;
    }
    
    public static String getMentionsPageNum(){
    	return String.valueOf(mentionsPage);
    }
    
    public  String getTimestamp(){
    	return getString("created_at").substring(0,10);
    }
    
    public User getUser() {
        return user;
    }

    public String getBody() {
        return getString("text");
    }

    public long getId() {
    	long id = getLong("id");
        return id;
    }

    public static Tweet fromJson(JSONObject jsonObject) {
        Tweet tweet = new Tweet();
        try {
            tweet.jsonObject = jsonObject;
            tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
        ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

        for (int i=0; i < jsonArray.length(); i++) {
            JSONObject tweetJson = null;
            try {
                tweetJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Tweet tweet = Tweet.fromJson(tweetJson);
            if (tweet != null) {
                tweets.add(tweet);
            }
        }

        return tweets;
    }
}
