package SocialAppGeneral;

/**
 * Created by kemo on 30/10/2016.
 */
public class LoginInfo implements Shareable {
    private final String EMAIL ="EMAIL";
    private final String PASSWORD ="password";
    private String email;
    private String password;
    public LoginInfo() {
        this.email = "";
        this.password = "";
    }
    public String getEMAIL() {
        return email;
    }

    public void setEMAIL(String EMAIL) {
        this.email = EMAIL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setAttributes(String s) {
        FormedLine formedLine = new FormedLine();
        formedLine.setLine(s);
        this.email= formedLine.ReadPartition(EMAIL).Value;
        this.password = formedLine.ReadPartition(PASSWORD).Value;
    }

    @Override
    public String convertToString() {

        FormedLine formedLine = new FormedLine();
        formedLine.AddPartition(EMAIL,email);
        formedLine.AddPartition(PASSWORD,password);
        return formedLine.getLine();
    }
}
