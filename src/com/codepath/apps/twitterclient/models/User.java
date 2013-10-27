package com.codepath.apps.twitterclient.models;

import org.json.JSONObject;

public class User extends BaseModel {
    public String getName() {
        return getString("name");
    }

    public String getId() {
        return getString("id_str");
    }

    public String getScreenName() {
        return getString("screen_name");
    }
    
    public String getProfileImageUrl() {
        return getString("profile_image_url");
    }
    
    public String getTagline(){
    	return getString("description");
    }
    
    public int getNumOfFollowers(){
    	return getInt("followers_count");
    }
    
    public int getNumOfFollowing(){
    	return getInt("friends_count");
    }


    public static User fromJson(JSONObject json) {
        User u = new User();

        try {
            u.jsonObject = json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }


}
