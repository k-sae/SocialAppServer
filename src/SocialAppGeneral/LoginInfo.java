package SocialAppGeneral;

/**
 * Created by kemo on 30/10/2016.
 */
public class LoginInfo implements Shareable {
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

    @Override
    public Shareable generateObjectFromString() {
        return null;
    }

    @Override
    public String generateStringFromObject() {
        return null;
    }
}
