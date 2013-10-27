package com.codepath.apps.twitterclient;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;

import android.content.Context;

import com.codepath.oauth.OAuthBaseClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 * 
 * This is the object responsible for communicating with a REST API. 
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes: 
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 * 
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 * 
 */
public class TwitterClient extends OAuthBaseClient {
    public static final Class<? extends Api> REST_API_CLASS = TwitterApi.class; // Change this
    public static final String REST_URL = "https://api.twitter.com/1.1/"; // Change this, base API URL
    public static final String REST_CONSUMER_KEY = "dn41AgyilPBCgrRIZ0qiA";       // Change this
    public static final String REST_CONSUMER_SECRET = "D2hld1eCJJhO6kjMlxmoTxch57yLOWOzkGZZpsTIy0"; // Change this
    public static final String REST_CALLBACK_URL = "oauth://twitterclient"; // Change this (here and in manifest)
    
    public TwitterClient(Context context) {
        super(context, REST_API_CLASS, REST_URL, REST_CONSUMER_KEY, REST_CONSUMER_SECRET, REST_CALLBACK_URL);
    }
    
    public void getHomeTimeline(AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/home_timeline.json");
    	client.get(url, null, handler);
    }
    
    public void getNextTweets(String pageNum, AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/home_timeline.json");
    	RequestParams params = new RequestParams();
        params.put("page", pageNum); 
    	client.get(url, params, handler);
    }
    
    public void getNextMentionsTweets(String pageNum, AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/mentions_timeline.json");
    	RequestParams params = new RequestParams();
        params.put("page", pageNum); 
    	client.get(url, params, handler);
    }
    
    public void postTweet(String tweet, AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/update.json");
    	RequestParams params = new RequestParams();
        params.put("status", tweet); 
        client.post(url, params, handler);
    }
    
    public void getMentions(AsyncHttpResponseHandler handler){
    	String url = getApiUrl("statuses/mentions_timeline.json");
    	client.get(url, null, handler);
    }
    
    public void getMyInfo(AsyncHttpResponseHandler handler){
    	String apiUrl = getApiUrl("account/verify_credentials.json");
    	client.get(apiUrl, null, handler);
    }
    
    public void getUserTimeline(String userId, AsyncHttpResponseHandler handler){
    	System.out.println(userId);
    	String apiUrl = getApiUrl("statuses/user_timeline.json");
    	RequestParams params = new RequestParams();
        params.put("user_id", userId); 
    	client.get(apiUrl, params, handler);
    }
    
}