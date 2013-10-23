package com.codepath.apps.twitterclient.models;

import org.json.JSONObject;

public class User extends BaseModel {
    public String getName() {
        return getString("name");
    }

    public long getId() {
        return getLong("id");
    }

    public String getScreenName() {
        return getString("screen_name");
    }
    
    public String getProfileImageUrl() {
        return getString("profile_image_url");
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
