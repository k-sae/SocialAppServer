package SocialAppGeneral;

import com.google.gson.Gson;

/**
 * Created by kemo on 30/10/2016.
 */
public class RegisterInfo implements Shareable {
    private LoginInfo loginInfo;
   private SocialAppGeneral.UserInfo UserInfo;
    public static  final String KEYWORD="new_register";

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public SocialAppGeneral.UserInfo getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(SocialAppGeneral.UserInfo userInfo) {
        UserInfo = userInfo;
    }


    public static RegisterInfo fromJsonString(String jsonStr) {
        //TODO #prototype GSON
        //Read from JSON
        Gson gson = new Gson();
        return  gson.fromJson(jsonStr,RegisterInfo.class);
    }

    @Override
    public String convertToJsonString() {
        //TODO #prototype GSON
        //Write JSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
