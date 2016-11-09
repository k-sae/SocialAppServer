package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 30/10/2016.
 */
public class AppUser implements Serializable {
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
