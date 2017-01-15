package SocialAppGeneral;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by kemo on 30/10/2016.
 */
public class AppUser implements Serializable, Shareable {
    private UserInfo userInfo;
    private String ID;
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String convertToJsonString() {
        return new Gson().toJson(this);
    }
    public static AppUser fromJsonString(String jsonStr)
    {
        return new Gson().fromJson(jsonStr,AppUser.class);
    }
}
