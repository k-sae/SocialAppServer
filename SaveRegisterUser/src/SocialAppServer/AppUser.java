package SocialAppServer;

/**
 * Created by begad on 10/31/2016.
 */
public class AppUser {
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
}
