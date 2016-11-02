package SocialAppServer;

/**
 * Created by begad on 10/30/2016.
 */
public class LoginInfo {
    private String email;
    private String password;

    public LoginInfo() {
        this.email = "";
        this.password = "";
    }

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
