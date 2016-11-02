package SocialAppServer;

/**
 * Created by begad on 10/30/2016.
 */
public class RegisterUser {
    private LoginInfo loginInfo;
    private UserInfo UserInfo;

    public RegisterUser() {
        this.loginInfo = new LoginInfo();
        this.UserInfo = new UserInfo();
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public UserInfo getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        UserInfo = userInfo;
    }
}
