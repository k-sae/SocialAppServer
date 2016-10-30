package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 30/10/2016.
 */
public class LoginInfo implements Serializable {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
