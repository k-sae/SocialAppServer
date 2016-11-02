package SocialAppServer;

/**
 * Created by begad on 10/31/2016.
 */
public class UserInfo {
    private String fullName;
    private String BirthDate;
    private String Gender;

    public UserInfo() {
        this.fullName = "";
        this.BirthDate = "";
        this.Gender = "";
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
