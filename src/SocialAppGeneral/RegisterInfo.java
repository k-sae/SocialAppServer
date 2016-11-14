package SocialAppGeneral;

/**
 * Created by kemo on 30/10/2016.
 */
public class RegisterInfo implements Shareable {
    private LoginInfo loginInfo;
   private UserInfo UserInfo;

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

    @Override
    public void setAttributes(String s) {

    }

    @Override
    public String convertToString() {
        FormedLine regFormedLined = new FormedLine();
        regFormedLined.AddPartition("userInfo", UserInfo.convertToString());
        regFormedLined.AddPartition("loginInfo", loginInfo.convertToString());
        return regFormedLined.getLine();

    }
}
