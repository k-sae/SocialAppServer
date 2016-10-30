package SocialAppGeneral;

import java.io.Serializable;

/**
 * Created by kemo on 24/10/2016.
 */
//created it for debugging
public class UserInfo implements Serializable {
  private String fullName;
  private String BirthDate;
  private String Gender;
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


  //TODO #team
  //Rest of info
}
