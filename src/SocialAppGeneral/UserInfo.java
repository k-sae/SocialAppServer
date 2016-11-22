package SocialAppGeneral;


/**
 * Created by kemo on 24/10/2016.
 */
//created it for debugging
public class UserInfo implements Shareable {
  private final String FULL_NAME = "fullName";
  private final String BIRTH_DATE = "birthDate";
  private final String GENDER = "gender";
  private String fullName;
  private String birthDate;
  private String gender;
  public UserInfo() {
    this.fullName = "";
    this.birthDate = "";
    this.gender = "";
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String convertToJsonString() {
    //TODO #hazem
    return null;
  }
}