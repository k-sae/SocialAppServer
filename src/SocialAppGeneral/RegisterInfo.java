package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 30/10/2016.
 */
public class RegisterInfo implements Serializable {
    private LoginInfo loginInfo;
   private SocialAppGeneral.UserInfo UserInfo;

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
}
